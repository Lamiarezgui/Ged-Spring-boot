package com.example.demo.activiti;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskRepresentation {

    private String id;
    private String name;
    private String processInstanceId;
    private Date createTime;
    private String assignee;
    private Date dueDate;
    private String description;
    private String owner;



}

