package com.surya.firstjobapp.company;

import com.surya.firstjobapp.job.Job;
import com.surya.firstjobapp.review.Review;

import java.util.List;

public interface CompanyService {
     String createCompany(Company company);
     List<Company> getAllCompany();

     Company getCompanyById(Long id);
     boolean deleteCompanyById(Long id);
     boolean updateCompany(Long id,Company company);

     List<Job> getAllJobs(Long id);


}
