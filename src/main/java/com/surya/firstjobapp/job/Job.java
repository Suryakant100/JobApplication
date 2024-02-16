package com.surya.firstjobapp.job;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.surya.firstjobapp.company.Company;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity

public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;
    @ManyToOne
    private Company company;
}
