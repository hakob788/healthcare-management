package com.example.healthcaremanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date dateTime;
    @ManyToOne
    private Patient patient;
    @ManyToOne
    private Doctor doctor;
}
