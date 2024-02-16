package com.surya.firstjobapp.review;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.surya.firstjobapp.company.Company;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    private String description;

    private double rating;

    @ManyToOne
    @JsonIgnore
    private Company company;
}
