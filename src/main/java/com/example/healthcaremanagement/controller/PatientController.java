package com.example.healthcaremanagement.controller;

import com.example.healthcaremanagement.entity.Patient;
import com.example.healthcaremanagement.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping
    public String patients(ModelMap modelMap) {
        List<Patient> all = patientRepository.findAll();
        modelMap.addAttribute("patients", all);
        return "patients";
    }

    @GetMapping("/remove")
    public String patientRemove(@RequestParam("id") int id) {
        patientRepository.deleteById(id);
        return "redirect:/patients";
    }

    @GetMapping("/create")
    public String createPatient() {
        return "addPatient";
    }

    @PostMapping("/create")
    public String createPatient(@ModelAttribute Patient patient,
                                @RequestParam("DateOfBirthday") Date DateOfBirthday) {
        patient.setDateOfBirthday(DateOfBirthday);
        patientRepository.save(patient);
        return "redirect:/patients";
    }
}