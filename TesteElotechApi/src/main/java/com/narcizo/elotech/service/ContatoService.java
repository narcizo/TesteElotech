package com.narcizo.elotech.service;

import com.narcizo.elotech.entity.Contato;
import com.narcizo.elotech.entity.Pessoa;
import com.narcizo.elotech.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ContatoService {
    @Autowired
    ContatoRepository repository;
    @Autowired
    PessoaService pessoaService;

    public List<Contato> getContatos(Long pessoaId) {
        Pessoa pessoa = pessoaService.getPessoa(pessoaId);

        if(pessoa.getId()==null)
            return Collections.emptyList();

        return pessoa.getContatos();
    }

    public Contato getContato(Long contatoId){
        return repository.findById(contatoId).orElseGet(Contato::new);
    }

    public List<Contato> addContatos(Long pessoaId, Contato contato) {
        Pessoa pessoa = pessoaService.getPessoa(pessoaId);

        if(pessoa.getId()==null && isContatoObjectValid(contato))
            return Collections.emptyList();

        pessoa.getContatos().add(contato);

        pessoaService.updatePessoa(pessoaId, pessoa);

        return pessoa.getContatos();
    }

    public List<Contato> updateContato(Long pessoaId, Long contatoId, Contato contato) {
        Pessoa pessoa = pessoaService.getPessoa(pessoaId);
        Contato existingContato = getContato(contatoId);

        if((pessoa.getId()==null
                || existingContato.getId()==null
                || pessoa.getContatos().stream().noneMatch(c -> Objects.equals(c.getId(), contatoId)))
                && isContatoObjectValid(contato))
            return Collections.emptyList();

        existingContato.setEmail(contato.getEmail());
        existingContato.setTelefone(contato.getTelefone());

        pessoaService.updatePessoa(pessoaId, pessoa);

        return pessoa.getContatos();
    }

    public Contato deleteContato(Long pessoaId, Long contatoId) {
        Pessoa pessoa = pessoaService.getPessoa(pessoaId);
        Contato contato = getContato(contatoId);

        if(pessoa.getId()==null
                || contato.getId()==null
                || pessoa.getContatos().size() == 1
                || pessoa.getContatos().stream().noneMatch(c -> Objects.equals(c.getId(), contatoId)))
            return contato;

        repository.delete(contato);
        pessoa.getContatos().remove(contato);
        pessoaService.updatePessoa(pessoaId, pessoa);

        return contato;
    }

    public boolean isContatoObjectValid(Contato contato){
        if(contato.getTelefone().isEmpty() || contato.getEmail().isEmpty())
            return false;
        return true;
    }
}
