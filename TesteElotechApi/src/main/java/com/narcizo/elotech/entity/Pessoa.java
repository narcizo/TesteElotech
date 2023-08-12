package com.narcizo.elotech.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.narcizo.elotech.Utils.CustomDateDeserializer;
import com.narcizo.elotech.Utils.MyUtils;
import jakarta.persistence.*;

import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;
    @NotNull
    private String cpf;
    @NotNull
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date dataNascimento;
    @NotNull
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contato> contatos = new ArrayList<>();

    public Pessoa(String nome, String cpf, Date dataNascimento, List<Contato> contatos) {
        this.nome = nome;
        this.cpf = MyUtils.validateCpf(cpf);
        this.dataNascimento = dataNascimento;
        this.contatos = contatos;
    }

    public Pessoa() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = MyUtils.validateCpf(cpf);
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

//    @JsonIgnore
    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }
}
