package com.example.crud.controller;

import com.example.crud.domain.*;
import com.example.crud.domain.interfaces.DoctorRepository;
import com.example.crud.domain.records.DadosDetalhamentoMedico;
import com.example.crud.domain.records.UpdateDoctor;
import com.example.crud.domain.records.ReadListDoctor;
import com.example.crud.domain.records.RequestDoctor;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("alura")
public class DoctorController {

    @Autowired
    public DoctorRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid RequestDoctor dados, UriComponentsBuilder uriBuilder){

        var medico = new Doctor(dados);
        repository.save(new Doctor(dados));
        var uri = uriBuilder.path("/alura/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    @GetMapping
    public ResponseEntity<Page<ReadListDoctor>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        var page =  repository.findAll(paginacao).map(ReadListDoctor::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity editar(@RequestBody @Valid UpdateDoctor dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir (@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.excluir();
        return ResponseEntity.noContent().build();
    }

}
