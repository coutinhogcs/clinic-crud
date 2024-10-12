package com.example.crud.controller;

import com.example.crud.domain.Patient;
import com.example.crud.domain.interfaces.PatientRepository;
import com.example.crud.domain.records.PatientRequest;
import com.example.crud.domain.records.PatientUpdate;
import com.example.crud.domain.records.ReadListPatien;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pacientes")
public class PatientController {

    @Autowired
    private PatientRepository repository;

    @PostMapping
    @Transactional
    public void request (@RequestBody PatientRequest data){
        repository.save(new Patient(data));
    }

    @GetMapping
    @Transactional
    public Page<ReadListPatien> list (Pageable pageable){
        return repository.findAll(pageable).map(ReadListPatien::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody PatientUpdate update){
        var patient = repository.getReferenceById(update.id());
        patient.updateInfo(update);
    }
}
