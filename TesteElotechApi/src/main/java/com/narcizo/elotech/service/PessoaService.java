package com.narcizo.elotech.service;

import com.narcizo.elotech.entity.Contato;
import com.narcizo.elotech.entity.Pessoa;
import com.narcizo.elotech.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PessoaService {
    @Autowired
    PessoaRepository repository;

    public List<Pessoa> getClientList() {
        return repository.findAll();
    }

    public Pessoa getPessoa(Long id) {
        return repository.findById(id).orElseGet(Pessoa::new);
    }

    public Pessoa createPessoa(Pessoa pessoa) {
        List<Contato> contatos = new ArrayList<>();

        pessoa.getContatos().forEach(contato -> {
            Contato newContato = new Contato(
                    contato.getEmail(), contato.getTelefone(), pessoa
            );
            contatos.add(newContato);
        });

        pessoa.getContatos().clear();
        pessoa.getContatos().addAll(contatos);

        return repository.save(pessoa);
    }

    public Pessoa updatePessoa(Long id, Pessoa updatedPessoa) {
        Pessoa existingPessoa = this.getPessoa(id);

        if(existingPessoa.getId()==0)
            return new Pessoa();

        existingPessoa.setNome(updatedPessoa.getNome());
        existingPessoa.setCpf(updatedPessoa.getCpf());
        existingPessoa.setDataNascimento(updatedPessoa.getDataNascimento());

        return repository.save(existingPessoa);
    }

    public void deletePessoa(Long id) {
        Pessoa pessoa = this.getPessoa(id);

        repository.delete(pessoa);
    }
}
