package com.example.demo.activiti;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.history.HistoricVariableInstanceQuery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskRepresentation {

    private String id;
    private String name;
    private String processInstanceId;
    private String assignee;
    private Map<String, Object> variables = new HashMap<>();
    private List<HistoricVariableInstance> var;
    public TaskRepresentation(String id, String name, String processInstanceId,Map<String, Object> variables){
        this.id=id;
        this.name=name;
        this.processInstanceId=processInstanceId;
        this.variables=variables;
    }


    public TaskRepresentation(String id, String name, String processInstanceId) {
        this.id=id;
        this.name=name;
        this.processInstanceId=processInstanceId;
    }

}

