package com.example.crud.domain;

import com.example.crud.cadastro.PatientAdress;
import com.example.crud.domain.records.PatientRequest;
import com.example.crud.domain.records.PatientUpdate;
import com.example.crud.domain.records.UpdateDoctor;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "paciente")
@Entity(name = "Patient")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String telefone;
    private String email;
    private int ativo;

    @Embedded
    private PatientAdress adress;

    public Patient (PatientRequest data){
        this.nome = data.nome();
        this.telefone = data.telefone();
        this.email = data.email();
        this.ativo = 1;
        this.adress = new PatientAdress(data.addressPatient());
    }

    public void updateInfo (PatientUpdate data) {
        if (this.email != null) {
            this.email = data.email();
        }
        if (this.telefone != null) {
            this.telefone = data.telefone();
        }
    }

    public void delete(){
        this.ativo = 0;
    }
}
