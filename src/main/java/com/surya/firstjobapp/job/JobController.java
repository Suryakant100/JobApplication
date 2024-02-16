package com.surya.firstjobapp.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobservice;
    @GetMapping
    public ResponseEntity<List<Job>> findAll(){
        return ResponseEntity.ok(jobservice.findAll());
    }

    @PostMapping
    public ResponseEntity<String> createJobs(@RequestBody Job job){
       jobservice.createJobs(job);
        return new ResponseEntity<>("New Job has been created successfully",HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id){

        Job job=jobservice.getJobById(id);
        if(job!=null){
            return new ResponseEntity<>(job, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id){
        boolean deleted = jobservice.deleteJobyId(id);
        if(deleted){
            return ResponseEntity.ok("Job deleted with id " + id + "is Successfully.");
        }
        return new ResponseEntity("Job Not Found With id " + id ,HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJobById(@PathVariable Long id, @RequestBody Job updatedJob){
        boolean updated = jobservice.updateJobById(id,updatedJob);
        if(updated){
            return new ResponseEntity<>("Job Updated is Successfully with Id " + id, HttpStatus.OK);
        }
        return  new ResponseEntity<>("Failed to updated Job with Id: " + id + " due to Given Id is not exist",HttpStatus.NOT_FOUND);
    }
}
