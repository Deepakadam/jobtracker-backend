package com.jobtracker.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobDTO {

    private Long id;

    private String companyName;

    private String jobRole;

    private String status;
}