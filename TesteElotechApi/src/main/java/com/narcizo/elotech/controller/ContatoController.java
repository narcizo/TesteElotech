package com.narcizo.elotech.controller;

import com.narcizo.elotech.entity.Contato;
import com.narcizo.elotech.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/pessoa")
public class ContatoController {
    @Autowired
    ContatoService service;

    @GetMapping("/{pessoaId}/contatos")
    public ResponseEntity<List<Contato>> getContatos(@PathVariable Long pessoaId){
        List<Contato> contatos = service.getContatos(pessoaId);

        return this.doesContatosExist(contatos);
    }

    @PostMapping("/{pessoaId}/add-contato")
    public ResponseEntity<List<Contato>>  addContato(@PathVariable Long pessoaId,
                                                     @RequestBody Contato contato){
        List<Contato> created = service.addContatos(pessoaId, contato);
        return this.doesContatosExist(created);
    }

    @PutMapping("/{pessoaId}/update-contato/{contatoId}")
    public ResponseEntity<List<Contato>>  updateContato(@PathVariable Long pessoaId,
                                                        @PathVariable Long contatoId,
                                                        @RequestBody Contato contato){
        List<Contato> contatos = service.updateContato(pessoaId, contatoId, contato);
        return this.doesContatosExist(contatos);
    }

    @DeleteMapping("/{pessoaId}/delete-contato/{contatoId}")
    public ResponseEntity<Contato> deleteContato(@PathVariable Long pessoaId, @PathVariable Long contatoId){
        Contato deleted = service.deleteContato(pessoaId, contatoId);

        return this.doesContatoExist(deleted);
    }

    private ResponseEntity<List<Contato>> doesContatosExist(List<Contato> contatos){
        if (contatos.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Contato ou Pessoa não encontrado."
            );
        return ResponseEntity.ok(contatos);
    }

    private ResponseEntity<Contato> doesContatoExist(Contato contato){
        if (contato.getId() == null)
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Contato ou Pessoa não encontrado."
            );
        return ResponseEntity.ok(contato);
    }
}
