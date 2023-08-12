package com.narcizo.elotech.controller;

import com.narcizo.elotech.entity.Pessoa;
import com.narcizo.elotech.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pessoa")
public class PessoaController {
    @Autowired
    PessoaService service;

    @GetMapping
    public List<Pessoa> getPessoasList () {
        return service.getPessoaList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> getPessoa(@PathVariable Long id){
        Pessoa pessoa = service.getPessoa(id);

        if(pessoa.getId()==null)
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(pessoa);

        return ResponseEntity.ok(pessoa);
    }

    @PostMapping
    public ResponseEntity<Pessoa> createPessoa (@RequestBody Pessoa pessoa) {
        Pessoa created = service.createPessoa(pessoa);

        if (created.getId() == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(created);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> updatePessoa(@PathVariable Long id, @RequestBody Pessoa updatedPessoa){
        Pessoa updated = service.updatePessoa(id, updatedPessoa);

        if (updated.getId() == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(updated);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pessoa> deletePessoa(@PathVariable Long id){
        Pessoa deleted = service.deletePessoa(id);

        if (deleted.getId() == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(deleted);
        return ResponseEntity.ok(deleted);
    }
}
