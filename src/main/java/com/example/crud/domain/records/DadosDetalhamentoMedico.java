package com.example.crud.domain.records;

import com.example.crud.cadastro.Endereco;
import com.example.crud.domain.Doctor;
import com.example.crud.domain.enums.Especialidade;

public record DadosDetalhamentoMedico(Long id, String nome, String crm, String telefone, Especialidade especialidade, Endereco endereco) {
    public DadosDetalhamentoMedico(Doctor doctor){
        this(doctor.getId(), doctor.getNome(), doctor.getCrm(), doctor.getTelefone(), doctor.getEspecialidade(), doctor.getEndereco());
    }
}
