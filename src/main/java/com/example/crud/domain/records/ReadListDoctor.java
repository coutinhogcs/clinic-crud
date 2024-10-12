package com.example.crud.domain.records;


import com.example.crud.domain.Doctor;
import com.example.crud.domain.enums.Especialidade;

public record ReadListDoctor(Long id, String nome, String email, String crm, Especialidade especialidade) {

    public ReadListDoctor(Doctor doctor){
        this(doctor.getId(), doctor.getNome(), doctor.getEmail(), doctor.getCrm(), doctor.getEspecialidade());
    }
}
