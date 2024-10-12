package com.example.crud.domain.records;

import jakarta.validation.constraints.NotNull;

public record UpdateDoctor(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {}
