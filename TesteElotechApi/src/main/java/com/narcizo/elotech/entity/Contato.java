package com.narcizo.elotech.entity;

import com.narcizo.elotech.Utils.MyUtils;
import jakarta.persistence.*;

import javax.validation.constraints.NotNull;

@Entity
public class Contato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String email;
    @NotNull
    private String telefone;

    public Contato(String email, String telefone) {
        this.email = MyUtils.validateEmail(email);
        this.telefone = MyUtils.validatePhoneNumber(telefone);
    }

    public Contato() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = MyUtils.validateEmail(email);
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = MyUtils.validatePhoneNumber(telefone);
    }
}
