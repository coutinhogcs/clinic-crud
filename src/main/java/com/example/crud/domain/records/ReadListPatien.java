package com.example.crud.domain.records;

import com.example.crud.domain.Patient;

public record ReadListPatien(Long id, String nome, String email, String telefone) {

    public ReadListPatien (Patient patient) {
        this(patient.getId(), patient.getNome(), patient.getEmail(), patient.getTelefone());
    }
}
