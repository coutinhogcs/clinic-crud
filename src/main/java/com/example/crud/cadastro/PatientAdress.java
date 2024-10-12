package com.example.crud.cadastro;

import com.example.crud.domain.records.AddressPatient;
import jakarta.persistence.Embeddable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientAdress {
    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    public PatientAdress (@NotNull @Valid AddressPatient addressPatient){
        this.logradouro  =   addressPatient.logradouro();
        this.bairro      =   addressPatient.bairro();
        this.cep         =   addressPatient.cep();
        this.numero      =   addressPatient.numero();
        this.complemento =   addressPatient.complemento();
        this.cidade      =   addressPatient.cidade();
        this.uf          =   addressPatient.uf();

    }
}
