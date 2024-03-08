package com.example.yonetim360.dto;

import lombok.Data;

@Data
public class TaskAssignDTO {

    private Long id;

    private boolean isDeleted;

    private String jobDescription;

}
