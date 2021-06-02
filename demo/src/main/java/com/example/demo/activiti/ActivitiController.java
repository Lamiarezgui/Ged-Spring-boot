package com.example.demo.activiti;

import org.activiti.engine.impl.util.json.JSONArray;
import org.activiti.engine.impl.util.json.JSONObject;
import org.activiti.engine.task.Task;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ActivitiController {
    private static final Logger logger = LoggerFactory.getLogger(ActivitiController.class);

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @GetMapping("/start-process")
    public String startProcess() {
        runtimeService.startProcessInstanceByKey("TaskManager");
        return "Process started. Number of currently running process instances = " + runtimeService.createProcessInstanceQuery()
                .count();
    }
    @GetMapping("/getIds")
    public String test() {


        JSONObject allTasks = new JSONObject();

        try {
            List<Task> wft=taskService.createTaskQuery()
                    .list();

            JSONArray ja = new JSONArray();
            System.out.println("WF sizes = "+wft.size());
            for (Task  temp : wft) {
                JSONObject userWFDetails = new JSONObject();

                userWFDetails.put("taskId ", temp.getId());
                userWFDetails.put("task name ", temp.getName());
                userWFDetails.put("task Due Date ", temp.getDueDate());
                userWFDetails.put("task Create Time ", temp.getCreateTime());
                userWFDetails.put("taskDesc ", temp.getDescription());
                userWFDetails.put("InstanceId ", temp.getProcessInstanceId());

                System.out.println("tasks- "+temp);
                System.out.println("task id- "+temp.getId());
                System.out.println("instance id- "+temp.getProcessInstanceId());
                ja.put(userWFDetails);
            }
            allTasks.put("userTasksDetails", ja);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allTasks.toString();
    }
    @GetMapping("/get-tasks/{processInstanceId}")
    public List<TaskRepresentation> getTasks(@PathVariable String processInstanceId) {
        List<Task> usertasks = taskService.createTaskQuery()
                .processInstanceId(processInstanceId)
                .list();

        return usertasks.stream()
                .map(task -> new TaskRepresentation(task.getId(), task.getName(), task.getProcessInstanceId(),task.getCreateTime(),task.getAssignee(),task.getDueDate(),task.getDescription(),task.getOwner()))
                .collect(Collectors.toList());
    }

    @GetMapping("/complete-task/{processInstanceId}")
    public void completeTaskA(@PathVariable String processInstanceId) {
        Task task = taskService.createTaskQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
        taskService.complete(task.getId());
        logger.info("Task completed");
    }
}