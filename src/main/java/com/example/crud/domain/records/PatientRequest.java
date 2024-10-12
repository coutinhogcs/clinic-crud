package com.example.crud.domain.records;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record PatientRequest(Long id, String nome, String telefone, String email , @NotNull @Valid AddressPatient addressPatient) {
}
