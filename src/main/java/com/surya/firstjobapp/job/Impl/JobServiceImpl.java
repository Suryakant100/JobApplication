package com.surya.firstjobapp.job.Impl;

import com.surya.firstjobapp.job.Job;
import com.surya.firstjobapp.job.JobRepository;
import com.surya.firstjobapp.job.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    /*
     *Before having Persist Database we use Temporary storing data using ArrayList

    List<Job> jobs = new ArrayList<>();
    private Long nextId=1L;
     */

    @Autowired
    private JobRepository jobRepository;
    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJobs(Job job) {
        jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
       return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobyId(Long id) {
        Optional<Job> jobs = jobRepository.findById(id);
        if (jobs.isPresent()){
            jobRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateJobById(Long id, Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()){
            Job job = jobOptional.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());
            jobRepository.save(job);
            return true;
        }
        return false;
    }
}
