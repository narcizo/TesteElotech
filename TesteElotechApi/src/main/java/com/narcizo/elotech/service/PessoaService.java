package com.narcizo.elotech.service;

import com.narcizo.elotech.Utils.MyUtils;
import com.narcizo.elotech.entity.Contato;
import com.narcizo.elotech.entity.Pessoa;
import com.narcizo.elotech.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PessoaService {
    @Autowired
    PessoaRepository repository;

    @Autowired
    public PessoaService(PessoaRepository repository) {
        this.repository = repository;
    }

    public List<Pessoa> getPessoaList() {
        return repository.findAll();
    }

    public Page<Pessoa> getPaginatedPessoasList(int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(
                page,
                pageSize,
                Sort.Direction.ASC,
                "id");
        return repository.findAll(pageRequest);
    }

    public Pessoa getPessoa(Long id) {
        return repository.findById(id).orElseGet(Pessoa::new);
    }

    public Pessoa createPessoa(Pessoa pessoa) {
        if (!this.isPessoaObjectValid(pessoa))
            return pessoa;

        List<Contato> contatos = new ArrayList<>();

        for (Contato contato: pessoa.getContatos()) {
            Contato newContato = new Contato(
                    contato.getEmail(), contato.getTelefone()
            );
            if (!contato.getEmail().isEmpty() && !contato.getTelefone().isEmpty())
                contatos.add(newContato);
        }

        if (contatos.isEmpty())
            return pessoa;

        pessoa.getContatos().clear();
        pessoa.getContatos().addAll(contatos);

        return repository.save(pessoa);
    }

    public Pessoa updatePessoa(Long id, Pessoa updatedPessoa) {
        Pessoa existingPessoa = this.getPessoa(id);

        if(existingPessoa.getId()==null || !this.isPessoaObjectValid(updatedPessoa))
            return updatedPessoa;

        existingPessoa.setNome(updatedPessoa.getNome());
        existingPessoa.setCpf(updatedPessoa.getCpf());
        existingPessoa.setDataNascimento(updatedPessoa.getDataNascimento());

        return repository.save(existingPessoa);
    }

    public Pessoa deletePessoa(Long id) {
        Pessoa pessoaToBeDeleted = this.getPessoa(id);

        if(pessoaToBeDeleted.getId()==null)
            return pessoaToBeDeleted;

        repository.delete(pessoaToBeDeleted);

        return pessoaToBeDeleted;
    }

    public boolean isPessoaObjectValid(Pessoa pessoa){
        if(pessoa.getCpf().isEmpty() || pessoa.getDataNascimento().after(new Date()) || pessoa.getContatos().isEmpty())
            return false;
        return true;
    }
}
