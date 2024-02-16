package com.surya.firstjobapp.job;

import java.util.List;

public interface JobService {
    public List<Job> findAll();
    public void createJobs(Job job);

    public Job getJobById(Long id);

    public boolean deleteJobyId(Long id);

    public boolean updateJobById(Long id,Job updatedJob);

}
