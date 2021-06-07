package com.example.demo.activiti;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class TaskRepresentation {

    private String id;
    private String name;
    private String processInstanceId;
    private String assignee;
    private Map<String, Object> variables = new HashMap<>();
    public TaskRepresentation(String id, String name, String processInstanceId,Map<String, Object> variables){
        this.id=id;
        this.name=name;
        this.processInstanceId=processInstanceId;
        this.variables=variables;
    }
    public TaskRepresentation(String id, String name, String processInstanceId, String assignee, Map<String, Object> variables){
        this.id=id;
        this.name=name;
        this.processInstanceId=processInstanceId;
        this.assignee=assignee;
        this.variables=variables;
    }


}

