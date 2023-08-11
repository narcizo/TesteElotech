package com.narcizo.elotech.controller;

import com.narcizo.elotech.entity.Pessoa;
import com.narcizo.elotech.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/pessoa")
public class PessoaController {
    @Autowired
    PessoaService service;

    @GetMapping
    public List<Pessoa> getPessoasList () {
        return service.getClientList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> GetPessoa(@PathVariable Long id){
        Optional<Pessoa> pessoa = service.getPessoa(id);

        return pessoa.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Pessoa()));
    }

    @PostMapping
    public ResponseEntity<Pessoa> createPessoa (@RequestBody Pessoa pessoa) {
        Pessoa created = service.createPessoa(pessoa);

        if (created.getId() == 0)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(created);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> updatePessoa(@PathVariable Long id, @RequestBody Pessoa updatedPessoa){
        Pessoa updated = service.updatePessoa(id, updatedPessoa);

        if (updated.getId() == 0)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(updated);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public void deletePessoa(@PathVariable Long id){
        service.deletePessoa(id);
    }
}
