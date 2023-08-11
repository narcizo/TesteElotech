package com.narcizo.elotech.service;

import com.narcizo.elotech.entity.Pessoa;
import com.narcizo.elotech.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {
    @Autowired
    PessoaRepository repository;

    public List<Pessoa> getClientList() {
        return repository.findAll();
    }

    public Optional<Pessoa> getPessoa(Long id) {
        return repository.findById(id);
    }

    public Pessoa createPessoa(Pessoa pessoa) {
        return repository.save(pessoa);
    }

    public Pessoa updatePessoa(Long id, Pessoa updatedPessoa) {
        Optional<Pessoa> existingPessoa = this.getPessoa(id);

        if(existingPessoa.isPresent()){
            existingPessoa.get().setNome(updatedPessoa.getNome());
            existingPessoa.get().setCpf(updatedPessoa.getCpf());
            existingPessoa.get().setDataNascimento(updatedPessoa.getDataNascimento());

            return repository.save(existingPessoa.get());
        }

        return new Pessoa();
    }

    public void deletePessoa(Long id) {
        Optional<Pessoa> existingPessoa = this.getPessoa(id);

        existingPessoa.ifPresent(pessoa -> repository.delete(pessoa));
    }
}
