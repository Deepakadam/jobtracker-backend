package com.jobtracker.service;
import com.jobtracker.exception.ResourceNotFoundException;
import com.jobtracker.entity.Job;
import com.jobtracker.repository.JobRepository;
import org.springframework.stereotype.Service;
import com.jobtracker.dto.JobDTO;
import com.jobtracker.mapper.JobMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public JobDTO saveJob(JobDTO dto) {

    Job job = JobMapper.toEntity(dto);

    Job savedJob = jobRepository.save(job);

    return JobMapper.toDTO(savedJob);
}

    public List<JobDTO> getAllJobs() {

    return jobRepository.findAll()
            .stream()
            .map(JobMapper::toDTO)
            .toList();
}

public JobDTO getJobById(Long id) {

    Job job = jobRepository.findById(id)
            .orElseThrow(() ->
                    new ResourceNotFoundException("Job not found"));

    return JobMapper.toDTO(job);
}
    public Job updateJob(Long id, Job updatedJob) {

    Job existingJob = jobRepository.findById(id)
.orElseThrow(() -> new ResourceNotFoundException("Job not found"));
    existingJob.setCompanyName(updatedJob.getCompanyName());
    existingJob.setJobRole(updatedJob.getJobRole());
    existingJob.setStatus(updatedJob.getStatus());

    return jobRepository.save(existingJob);
}

public void deleteJob(Long id) {

    Job existingJob = jobRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Job not found"));

    jobRepository.delete(existingJob);
}

public Page<JobDTO> getJobsWithPagination(int page, int size) {

    Pageable pageable = PageRequest.of(page, size);

    return jobRepository.findAll(pageable)
            .map(JobMapper::toDTO);
}

public List<JobDTO> getJobsSorted(String field) {

    return jobRepository.findAll(Sort.by(Sort.Direction.ASC, field))
            .stream()
            .map(JobMapper::toDTO)
            .toList();
}

public List<JobDTO> getJobsByStatus(String status) {

    return jobRepository.findByStatus(status)
            .stream()
            .map(JobMapper::toDTO)
            .toList();
}
}