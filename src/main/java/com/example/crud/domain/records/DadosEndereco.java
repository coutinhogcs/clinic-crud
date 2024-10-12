package com.example.crud.domain.records;

import jakarta.validation.constraints.NotBlank;

public record DadosEndereco(@NotBlank
                            String logradouro,
                            @NotBlank
                            String bairro,
                            @NotBlank
                            String cep,
                            String numero,
                            String complemento,
                            @NotBlank
                            String cidade,
                            @NotBlank
                            String uf) {
}
