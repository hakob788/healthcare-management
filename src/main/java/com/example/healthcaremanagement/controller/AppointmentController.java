package com.example.healthcaremanagement.controller;

import com.example.healthcaremanagement.entity.Appointment;
import com.example.healthcaremanagement.entity.Doctor;
import com.example.healthcaremanagement.entity.Patient;
import com.example.healthcaremanagement.repository.AppointmentRepository;
import com.example.healthcaremanagement.repository.DoctorRepository;
import com.example.healthcaremanagement.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping
    public String appointments(ModelMap modelMap) {
        List<Appointment> all = appointmentRepository.findAll();
        modelMap.addAttribute("appointments", all);
        return "appointments";
    }

    @GetMapping("/remove")
    public String appRemove(@RequestParam("id") int id) {
        appointmentRepository.deleteById(id);
        return "redirect:/appointments";
    }

    @GetMapping("/create")
    public String createApp(ModelMap modelMap) {
        List<Patient> allPatients = patientRepository.findAll();
        List<Doctor> allDoctors = doctorRepository.findAll();
        modelMap.addAttribute("doctors", allDoctors);
        modelMap.addAttribute("patients", allPatients);
        return "createAppointment";
    }

    @PostMapping("/create")
    public String createApp(@ModelAttribute Appointment appointment,
                            @RequestParam("date.time") @DateTimeFormat(pattern = "yyyy-MM-dd 'T' HH:mm") Date date) {
        appointment.setDateTime(date);
        appointmentRepository.save(appointment);
        return "redirect:/appointments";
    }
}
