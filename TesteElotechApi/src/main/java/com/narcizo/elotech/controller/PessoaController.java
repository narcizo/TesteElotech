package com.narcizo.elotech.controller;

import com.narcizo.elotech.entity.Pessoa;
import com.narcizo.elotech.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/pessoa")
public class PessoaController {
    @Autowired
    PessoaService service;

    @GetMapping
    public List<Pessoa> getPessoasList () {
        return service.getPessoaList();
    }

    @GetMapping("/paginated")
    public Page<Pessoa> getPaginatedPessoasList(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize
    ) {
        return service.getPaginatedPessoasList(page, pageSize);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> getPessoa(@PathVariable Long id){
        Pessoa pessoa = service.getPessoa(id);

        return this.doesPessoaExist(pessoa);
    }

    @PostMapping
    public ResponseEntity<Pessoa> createPessoa (@RequestBody Pessoa pessoa) {
        Pessoa created = service.createPessoa(pessoa);

        return this.doesPessoaExist(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> updatePessoa(@PathVariable Long id, @RequestBody Pessoa updatedPessoa){
        Pessoa updated = service.updatePessoa(id, updatedPessoa);

        return this.doesPessoaExist(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pessoa> deletePessoa(@PathVariable Long id){
        Pessoa deleted = service.deletePessoa(id);

        return this.doesPessoaExist(deleted);
    }

    private ResponseEntity<Pessoa> doesPessoaExist(Pessoa pessoa){
        if (pessoa.getId() == null){
            if(pessoa.getCpf() == null || pessoa.getDataNascimento() == null)
                throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Pessoa não encontrada"
            );
            if(!service.isPessoaObjectValid(pessoa))
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Payload incorreto"
                );
        }
        return ResponseEntity.ok(pessoa);
    }
}
