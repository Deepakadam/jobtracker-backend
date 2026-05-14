package com.jobtracker.controller;

import jakarta.validation.Valid;

import com.jobtracker.entity.Job;
import com.jobtracker.service.JobService;

import org.springframework.web.bind.annotation.*;

import com.jobtracker.dto.JobDTO;
import com.jobtracker.dto.ApiResponse;

import org.springframework.data.domain.Page;

import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    public JobDTO createJob(@Valid @RequestBody JobDTO dto) {

        return jobService.saveJob(dto);
    }

    @GetMapping
    public List<JobDTO> getAllJobs() {

        return jobService.getAllJobs();
    }

    @PutMapping("/{id}")
    public Job updateJob(@PathVariable Long id,
                         @Valid @RequestBody Job job) {

        return jobService.updateJob(id, job);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ApiResponse deleteJob(@PathVariable Long id) {

        jobService.deleteJob(id);

        return new ApiResponse("Job deleted successfully");
    }

    @GetMapping("/{id}")
    public JobDTO getJobById(@PathVariable Long id) {

        return jobService.getJobById(id);
    }

    @GetMapping("/paginated")
    public Page<JobDTO> getJobsWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return jobService.getJobsWithPagination(page, size);
    }

    @GetMapping("/sorted")
    public List<JobDTO> getJobsSorted(
            @RequestParam String field) {

        return jobService.getJobsSorted(field);
    }

    @GetMapping("/status/{status}")
    public List<JobDTO> getJobsByStatus(
            @PathVariable String status) {

        return jobService.getJobsByStatus(status);
    }
}