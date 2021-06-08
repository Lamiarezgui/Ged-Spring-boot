package com.example.demo.activiti;

import com.example.demo.Model.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FormRepresentation {
    private String task;
    private Date de;
    private String description;
    private Date ddl;
    private long employe;
}
