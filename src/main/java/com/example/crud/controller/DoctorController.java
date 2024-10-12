package com.example.crud.controller;

import com.example.crud.domain.*;
import com.example.crud.domain.interfaces.DoctorRepository;
import com.example.crud.domain.records.UpdateDoctor;
import com.example.crud.domain.records.ReadListDoctor;
import com.example.crud.domain.records.RequestDoctor;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController //Indica que é o Controller da API
@RequestMapping("alura") // indica o END - POINT da aplicação, ou seja, nesse momento é o final da url do localhost
public class DoctorController {

    @Autowired
    public DoctorRepository repository;

    @PostMapping //anotação que indica que será um metodo Post
    @Transactional // anotação que indica ao spring que vão ser feitas mudanças ao banco
    public void cadastrar(@RequestBody @Valid RequestDoctor dados){ //anotações para indicar mudança no corpo do objeto a partir do record de request
        //a partir do repository é possivel acessar um medoto chamado save a partir dele é instanciado um novo objeto Doctor
        //obviamente sempre vai chamar já que é para fazer a inclusão de um novo objeto e dentro desse objeto Doctor vem os dados do record
        repository
                .save(new Doctor(dados));
    }
    //Metodo que faz requisição da leitura do crud, foi utilizado uma Lista do tipo Page com generics para o record de listagem
    //podendo usar uma notação para fazer a paginação default e um objeto Pageable para indicar no repository a paginação
    @GetMapping
    public Page<ReadListDoctor> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        return repository.findAll(paginacao).map(ReadListDoctor::new);
    }
    //Metodo de edição dos dados
    //metodo que tem como um atribudo um record criado apenas para os campos que podem ser editados
    //é criada uma variavel e atribuido o repository pegando a referencia pelo id
    //a variavel chama o medoto na entetidade que atribui os atributos editaveis
    @PutMapping
    @Transactional
    public void editar(@RequestBody @Valid UpdateDoctor dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
    }


    @DeleteMapping("/{id}")
    @Transactional
    public void excluir (@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.excluir();
    }

}
