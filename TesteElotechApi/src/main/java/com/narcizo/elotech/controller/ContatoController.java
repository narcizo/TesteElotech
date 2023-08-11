package com.narcizo.elotech.controller;

import com.narcizo.elotech.entity.Contato;
import com.narcizo.elotech.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return ResponseEntity.ok(service.getContatos(pessoaId));
    }

    @PostMapping("/{pessoaId}/add-contato")
    public ResponseEntity<List<Contato>>  addContato(@PathVariable Long pessoaId,
                                                     @RequestBody Contato contato){
        return ResponseEntity.ok(service.addContatos(pessoaId, contato));
    }

    @PutMapping("/{pessoaId}/update-contato/{contatoId}")
    public ResponseEntity<List<Contato>>  updateContato(@PathVariable Long pessoaId,
                                                        @PathVariable Long contatoId,
                                                        @RequestBody Contato contato){
        return ResponseEntity.ok(service.updateContato(pessoaId, contatoId, contato));
    }

    @DeleteMapping("/{pessoaId}/delete-contato/{contatoId}")
    public void deleteContato(@PathVariable Long pessoaId, @PathVariable Long contatoId){
        service.deleteContato(pessoaId, contatoId);
    }
}
