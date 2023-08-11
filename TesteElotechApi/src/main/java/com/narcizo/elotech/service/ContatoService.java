package com.narcizo.elotech.service;

import com.narcizo.elotech.entity.Contato;
import com.narcizo.elotech.entity.Pessoa;
import com.narcizo.elotech.repository.ContatoRepository;
import com.narcizo.elotech.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ContatoService {
    @Autowired
    ContatoRepository repository;
    @Autowired
    PessoaRepository pessoaRepository;
    @Autowired
    PessoaService pessoaService;

    public List<Contato> getContatos(Long pessoaId) {
        Pessoa pessoa = pessoaService.getPessoa(pessoaId);

        if(pessoa.getId()==0)
            return Collections.emptyList();

        return pessoa.getContatos();
    }

    public Contato getContato(Long contatoId){
        return repository.findById(contatoId).orElseGet(Contato::new);
    }

    public List<Contato> addContatos(Long pessoaId, Contato contato) {
        Pessoa pessoa = pessoaService.getPessoa(pessoaId);

        if(pessoa.getId()==0)
            return Collections.emptyList();

        pessoa.addContato(contato);

        return pessoaRepository.save(pessoa).getContatos();
    }

    public List<Contato> updateContato(Long pessoaId, Long contatoId, Contato contato) {
        Pessoa pessoa = pessoaService.getPessoa(pessoaId);
        Contato existingContato = getContato(contatoId);

        if(pessoa.getId()==0 || existingContato.getId()==0)
            return Collections.emptyList();

        existingContato.setEmail(contato.getEmail());
        existingContato.setTelefone(contato.getTelefone());

        return pessoaRepository.save(pessoa).getContatos();
    }

    public void deleteContato(Long pessoaId, Long contatoId) {
        Pessoa pessoa = pessoaService.getPessoa(pessoaId);
        Contato contato = getContato(contatoId);

        if(pessoa.getId()==0 || contato.getId()==0)
            return;

        repository.delete(contato);
    }
}
