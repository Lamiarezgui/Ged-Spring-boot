package com.example.demo.activiti;


import com.example.demo.Model.Users;
import com.example.demo.Repository.UsersRepository;
import com.example.demo.mail.EmailSender;
import lombok.AllArgsConstructor;
import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.util.json.JSONArray;
import org.activiti.engine.impl.util.json.JSONObject;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
public class ActivitiController {
    private static final Logger logger = LoggerFactory.getLogger(ActivitiController.class);

    @Autowired
    private RuntimeService runtimeService;
    UsersRepository usersRepository;
    @Autowired
    private TaskService taskService;
    private HistoryService historyService;
    private final EmailSender emailSender;

    //ajouter un nouveau process et les variables
    @PreAuthorize("hasAnyRole('ROLE_CONTROLEUR','ROLE_SUPERVISEUR','ROLE_INGENIEUR')")
    @PostMapping("/start-process")
    public String startProcess(@RequestBody FormRepresentation formRepresentation) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("task", formRepresentation.getTask());
        variables.put("de", formRepresentation.getDe());
        variables.put("description", formRepresentation.getDescription());
        variables.put("ddl", formRepresentation.getDdl());
        variables.put("employe", formRepresentation.getEmploye());
        runtimeService.startProcessInstanceByKey("gestiondestaches", variables);
        Users user = usersRepository.getOne(formRepresentation.getEmploye());
        Users email = usersRepository.findByEmail(user.getEmail());
        String link = "http://localhost:3000/ ";
        emailSender.send(
                email.getEmail(),
                buildEmail(email.getFirstName(), email.getLastName(), link), "vous avez une nouvelle tache");


        return "Process started. Number of currently running process instances = " + runtimeService.createProcessInstanceQuery()
                .count();
    }

    private String buildEmail(String firstName, String lastName, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "      <tbody><tr>\n" +

                "    <tr>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + firstName + " " + lastName + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> vous avez une nouvelle tache à faire </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\">" +
                "<p style=\\\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + " \">Voir vos taches </a> </p><p>Have a good Day</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }


    //get les variables de chaque process
    @GetMapping("/var/{processInstanceId}")
    public Map<String, Object> getVariables(@PathVariable String processInstanceId) {
        return taskService.getVariables(processInstanceId);
    }


    //afficher tous les tasks et leurs details
    @PreAuthorize("hasAnyRole('ROLE_CONTROLEUR','ROLE_SUPERVISEUR','ROLE_INGENIEUR')")
    @GetMapping("/getIds")
    public String test() {


        JSONObject allTasks = new JSONObject();

        try {
            List<Task> wft = taskService.createTaskQuery()
                    .list();


            JSONArray ja = new JSONArray();
            System.out.println("WF sizes = " + wft.size());
            for (Task temp : wft) {
                JSONObject userWFDetails = new JSONObject();
                userWFDetails.put("Task id: ", temp.getId());
                userWFDetails.put("Task name ", temp.getName());
                userWFDetails.put("Instance id: ", temp.getProcessInstanceId());
                userWFDetails.put("variables", taskService.getVariables(temp.getId()));
                ja.put(userWFDetails);
            }
            allTasks.put("userTasksDetails", ja);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allTasks.toString();
    }

    //afficher les tasks d'un user
    @PreAuthorize("hasAnyRole('ROLE_CONTROLEUR','ROLE_SUPERVISEUR','ROLE_INGENIEUR','ROLE_ADMINISTRATEUR')")
    @GetMapping("/get-task/{id}")
    public List<TaskRepresentation> getTasksbyUser(@PathVariable long id) {

        List<Task> usertasks = taskService.createTaskQuery()
                .taskCandidateOrAssigned(String.valueOf(id))
                .list();

        return usertasks.stream()
                .map(task -> new TaskRepresentation(task.getId(), task.getName(), task.getProcessInstanceId(), taskService.getVariables(task.getId())))
                .collect(Collectors.toList());
    }

    //afficher un task de process avec l'instance process id
    @GetMapping("/get-tasks/{processInstanceId}")
    public List<TaskRepresentation> getTasks(@PathVariable String processInstanceId) {
        List<Task> usertasks = taskService.createTaskQuery()
                .processInstanceId(processInstanceId)
                .list();

        return usertasks.stream()
                .map(task -> new TaskRepresentation(task.getId(), task.getName(), task.getProcessInstanceId(), task.getAssignee(), taskService.getVariables(task.getId())))
                .collect(Collectors.toList());
    }

    //get complete tasks for one user
    @PreAuthorize("hasAnyRole('ROLE_CONTROLEUR','ROLE_SUPERVISEUR','ROLE_INGENIEUR','ROLE_ADMINISTRATEUR')")
    @GetMapping("/completeTasks/{id}")
    public List<TaskRepresentation> getCompletedTaks(@PathVariable long id) {
        List<Task> usertasks = Collections.singletonList((Task) historyService.createHistoricTaskInstanceQuery().finished().taskAssignee(String.valueOf(id)).list());
        return usertasks.stream()
                .map(task -> new TaskRepresentation(task.getId(), task.getName(), task.getProcessInstanceId(), task.getAssignee(), taskService.getVariables(task.getId())))
                .collect(Collectors.toList());
    }

    //terminer le task
    @PreAuthorize("hasAnyRole('ROLE_CONTROLEUR','ROLE_SUPERVISEUR','ROLE_INGENIEUR','ROLE_ADMINISTRATEUR')")
    @GetMapping("/complete-task/{processInstanceId}")
    public void completeTaskA(@PathVariable String processInstanceId) {
        Task task = taskService.createTaskQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
        taskService.complete(task.getId());
        logger.info("Task completed");
    }

    //completed tasks
    /*@GetMapping("/listcompletedTaks")
    public List<Task> getTasksCom() {
        List<HistoricProcessInstance> historicProcessInstances = this.historyService.createHistoricProcessInstanceQuery().finished().list();

        for (int i = 0; i < historicProcessInstances.size(); i++) {

            List<Task> taskList = this.taskService.createTaskQuery().processInstanceId(historicProcessInstances.get(i).getId()).orderByTaskCreateTime().desc().list();
            for (Task task : taskList) {
                ProcessDefinition processDefinition = this.taskService.getVariables();
                TaskRepresentation taskResponse = new TaskRepresentation(task);

            }

        }}*/
    }
