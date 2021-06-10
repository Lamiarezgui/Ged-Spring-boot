package com.example.demo.activiti;


import org.activiti.engine.HistoryService;
import org.activiti.engine.impl.util.json.JSONArray;
import org.activiti.engine.impl.util.json.JSONObject;
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

@RestController
public class ActivitiController {
    private static final Logger logger = LoggerFactory.getLogger(ActivitiController.class);

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;
    private HistoryService historyService;

    //ajouter un nouveau process et les variables
    @PreAuthorize("hasRole('ROLE_CONTROLEUR')")
    @PostMapping("/start-process")
    public String startProcess(@RequestBody FormRepresentation formRepresentation) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("task", formRepresentation.getTask());
        variables.put("de", formRepresentation.getDe());
        variables.put("description", formRepresentation.getDescription());
        variables.put("ddl", formRepresentation.getDdl());
        variables.put("employe", formRepresentation.getEmploye());
        runtimeService.startProcessInstanceByKey("gestiondestaches", variables);

        return "Process started. Number of currently running process instances = " + runtimeService.createProcessInstanceQuery()
                .count();
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
                userWFDetails.put("Task id: ",temp.getId());
                userWFDetails.put("instance id: ",temp.getProcessInstanceId());
                userWFDetails.put("variables",taskService.getVariables(temp.getId()));
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
    @PreAuthorize("hasRole(ROLE_ADMINISTRATEUR')")
    @GetMapping("/complete-task/{processInstanceId}")
    public void completeTaskA(@PathVariable String processInstanceId) {
        Task task = taskService.createTaskQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
        taskService.complete(task.getId());
        logger.info("Task completed");
    }

}
