package com.example.crud.domain;

import com.example.crud.cadastro.Endereco;
import com.example.crud.domain.enums.Especialidade;
import com.example.crud.domain.records.UpdateDoctor;
import com.example.crud.domain.records.RequestDoctor;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "medicos") //Indica qual a tabela do banco que sera executado o crud
@Entity(name = "Doctor") // indica qual a classe java que será a entidade
@Getter // faz todos os metodos Getters de atributos da classe
@Setter // faz todos os metodos Setters de atributos da classe
@AllArgsConstructor  // faz o construtor com todos os argumentos na classe
@NoArgsConstructor // faz o construtor padrão exigido pelo hibernate
@EqualsAndHashCode(of = "id")
public class Doctor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // indica que este é o ID da tabela e que ele vai ser gerado sozinho
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING) // indica que é um ENUM do tipo String
    private Especialidade especialidade;

    @Embedded //pesquisar
    private Endereco endereco;

    private int ativo;

    // Construtor que olha para o Record RequestDoctor e faz a atribuição dos atributos da classe com os dados que serão cadastrados a partir do metodo @PostMapping no Controler
    public Doctor(RequestDoctor dados) {
        this.ativo          =   1;
        this.nome           =   dados.nome();
        this.email          =   dados.email();
        this.crm            =   dados.crm();
        this.telefone       =   dados.telefone();
        this.especialidade  =   dados.especialidade();
        this.endereco       =   new Endereco(dados.endereco());
    }

    // metodo que atribui os atribulhos da entidade aos atributos que podem serem mudados
    public void atualizarInformacoes(UpdateDoctor dados) {
        if(dados.nome()      != null) {this.nome     = dados.nome();}
        if (dados.telefone() != null) {this.telefone = dados.telefone();}
        if (dados.endereco() != null) {this.endereco.atualizarInformacoes(dados.endereco());}
    }

    public void excluir() {
        this.ativo = 0;
    }
}
