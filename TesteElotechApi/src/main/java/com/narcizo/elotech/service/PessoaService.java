package com.narcizo.elotech.service;

import com.narcizo.elotech.Utils.MyUtils;
import com.narcizo.elotech.entity.Contato;
import com.narcizo.elotech.entity.Pessoa;
import com.narcizo.elotech.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PessoaService {
    @Autowired
    PessoaRepository repository;

    @Autowired
    ContatoService contatoService;

    public List<Pessoa> getPessoaList() {
        return repository.findAll();
    }

    public Pessoa getPessoa(Long id) {
        return repository.findById(id).orElseGet(Pessoa::new);
    }

    public Pessoa createPessoa(Pessoa pessoa) {
        if (this.isPessoaObjectValid(pessoa) && !pessoa.getContatos().isEmpty())
            return new Pessoa();

        List<Contato> contatos = new ArrayList<>();

        pessoa.getContatos().forEach(contato -> {
            Contato newContato = new Contato(
                    contato.getEmail(), contato.getTelefone()
            );
            contatos.add(newContato);
        });

        //TODO Fazer Validação de contatos aqui

        pessoa.getContatos().clear();
        pessoa.getContatos().addAll(contatos);

        return repository.save(pessoa);
    }

    public Pessoa updatePessoa(Long id, Pessoa updatedPessoa) {
        Pessoa existingPessoa = this.getPessoa(id);

        if(existingPessoa.getId()==null || this.isPessoaObjectValid(existingPessoa))
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

    public boolean isPessoaObjectValid(Pessoa pessoa){
        if(MyUtils.validateCpf(pessoa.getCpf()).isEmpty() || pessoa.getDataNascimento().after(new Date()))
            return false;
        return true;
    }
}
