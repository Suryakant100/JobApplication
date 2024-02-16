package com.surya.firstjobapp.company.Impl;

import com.surya.firstjobapp.company.Company;
import com.surya.firstjobapp.company.CompanyRepository;
import com.surya.firstjobapp.company.CompanyService;
import com.surya.firstjobapp.job.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Override
    public String createCompany(Company company) {
        companyRepository.save(company);
        return "Company has been added Successfully.";
    }

    @Override
    public List<Company> getAllCompany() {
        return companyRepository.findAll();
    }

    @Override
    public Company getCompanyById(Long id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if(optionalCompany.isPresent()){
            Company comp = optionalCompany.get();
            return comp;
        }
        return null;
    }

    @Override
    public boolean deleteCompanyById(Long id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if(optionalCompany.isPresent()){
            Company comp = optionalCompany.get();
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateCompany(Long id, Company company) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if(optionalCompany.isPresent()){
            Company comp = optionalCompany.get();
            comp.setName(company.getName());
            comp.setDescription(company.getDescription());
//            comp.setJobs(company.getJobs());
            companyRepository.save(comp);
            return true;
        }
        return false;
    }

    @Override
    public List<Job> getAllJobs(Long id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if(optionalCompany.isPresent()){
            Company comp = optionalCompany.get();
            return comp.getJobs();
        }
        return null;
    }
}
