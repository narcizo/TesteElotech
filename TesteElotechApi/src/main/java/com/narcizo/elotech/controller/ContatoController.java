package com.narcizo.elotech.controller;

import com.narcizo.elotech.entity.Contato;
import com.narcizo.elotech.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pessoa")
public class ContatoController {
    @Autowired
    ContatoService service;

    @GetMapping("/{pessoaId}/contatos")
    public ResponseEntity<List<Contato>> getContatos(@PathVariable Long pessoaId){
        List<Contato> contatos = service.getContatos(pessoaId);
        if (contatos.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(contatos);
        return ResponseEntity.ok(contatos);
    }

    @PostMapping("/{pessoaId}/add-contato")
    public ResponseEntity<List<Contato>>  addContato(@PathVariable Long pessoaId,
                                                     @RequestBody Contato contato){
        List<Contato> created = service.addContatos(pessoaId, contato);
        if (created.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(created);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{pessoaId}/update-contato/{contatoId}")
    public ResponseEntity<List<Contato>>  updateContato(@PathVariable Long pessoaId,
                                                        @PathVariable Long contatoId,
                                                        @RequestBody Contato contato){
        List<Contato> contatos = service.updateContato(pessoaId, contatoId, contato);
        if (contatos.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(contatos);
        return ResponseEntity.ok(contatos);
    }

    @DeleteMapping("/{pessoaId}/delete-contato/{contatoId}")
    public ResponseEntity<Contato> deleteContato(@PathVariable Long pessoaId, @PathVariable Long contatoId){
        Contato deleted = service.deleteContato(pessoaId, contatoId);

        if (deleted.getId() == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(deleted);
        return ResponseEntity.ok(deleted);
    }
}
