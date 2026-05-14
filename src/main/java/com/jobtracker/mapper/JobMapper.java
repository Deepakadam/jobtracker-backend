package com.jobtracker.mapper;

import com.jobtracker.dto.JobDTO;
import com.jobtracker.entity.Job;

public class JobMapper {

    public static JobDTO toDTO(Job job) {

        JobDTO dto = new JobDTO();

        dto.setId(job.getId());
        dto.setCompanyName(job.getCompanyName());
        dto.setJobRole(job.getJobRole());
        dto.setStatus(job.getStatus());

        return dto;
    }

    public static Job toEntity(JobDTO dto) {

        Job job = new Job();

        job.setId(dto.getId());
        job.setCompanyName(dto.getCompanyName());
        job.setJobRole(dto.getJobRole());
        job.setStatus(dto.getStatus());

        return job;
    }
}