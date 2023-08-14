package com.narcizo.elotech.controller;

import com.narcizo.elotech.entity.Contato;
import com.narcizo.elotech.entity.Pessoa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PessoaControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private Pessoa mockedPessoa;
    String createJsonValido, createJsonInvalido1, createJsonInvalido2;

    String updateJsonValido, updateJsonInvalido1, updateJsonInvalido2, updateJsonInvalido3;

    //Infelizmente por questão de tempo não consegui mockar a base de dados

    @BeforeEach
    public void init() throws ParseException {
        Pessoa mockedPessoa = new Pessoa("Narcizo",
                "334.313.947-52",
                new SimpleDateFormat("dd/MM/yyyy").parse("08/10/1997"),
                new ArrayList<Contato>(){
                    {
                        add(new Contato("narcizo@gmail.com","(42)99732-3435"));
                    }
                }
        );

        createJsonValido = "{\n" +
                "    \"nome\": \"Narcizo\",\n" +
                "    \"cpf\": \"334.313.947-52\",\n" +
                "    \"dataNascimento\": \"12/08/2001\",\n" +
                "    \"contatos\": [\n" +
                "        {\n" +
                "            \"email\": \"narcizo@gmail.com\",\n" +
                "            \"telefone\": \"(44)99634-2332\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"email\": \"narcizo@elotech.com\",\n" +
                "            \"telefone\": \"999999999\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        createJsonInvalido1 = "{\n" +
                "    \"nome\": \"Narcizo\",\n" +
                "    \"cpf\": \"334.313.947-52\",\n" +
                "    \"dataNascimento\": \"12/08/2001\",\n" +
                "    \"contatos\": [ ]\n" +
                "}";

        createJsonInvalido2 = "{\n" +
                "    \"nome\": \"Narcizo\",\n" +
                "    \"cpf\": \"334.313.7-52\",\n" +
                "    \"dataNascimento\": \"12/08/2050\",\n" +
                "    \"contatos\": [\n" +
                "        {\n" +
                "            \"email\": \"narcizo@gmail.com\",\n" +
                "            \"telefone\": \"(44)99634-2332\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        updateJsonValido = "{\n" +
                "    \"nome\": \"Luís\",\n" +
                "    \"cpf\": \"352.433.147-57\",\n" +
                "    \"dataNascimento\": \"08/10/2001\"\n" +
                "}";

        updateJsonInvalido1 = "{\n" +
                "    \"nome\": \"Luís\",\n" +
                "    \"cpf\": \"111\",\n" +
                "    \"dataNascimento\": \"08/10/2001\"\n" +
                "}";
        updateJsonInvalido2 = "{\n" +
                "    \"nome\": \"Luís\",\n" +
                "    \"cpf\": \"352.433.147-57\",\n" +
                "    \"dataNascimento\": \"08/10/2050\"\n" +
                "}";
        updateJsonInvalido3 = "{\n" +
                "    \"nome\": \"Luís\",\n" +
                "    \"cpf\": \"\",\n" +
                "    \"dataNascimento\": \"\"\n" +
                "}";
    }

    @Test
    @DisplayName("Test getPessoasList")
    public void testGetPessoasList() throws Exception {
        mockMvc.perform(get("/pessoa"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @DisplayName("Test getPessoa")
    public void testGetPessoa() throws Exception {
        mockMvc.perform(get("/pessoa/{id}", 4))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(4))
                .andExpect(jsonPath("$.nome").value("Narcizo"))
                .andExpect(jsonPath("$.cpf").value("334.313.947-52"))
                .andExpect(jsonPath("$.contatos[0].email").value("narcizo@gmail.com"))
                .andExpect(jsonPath("$.contatos[0].telefone").value("(44)99634-2332"));

        mockMvc.perform(get("/pessoa/{id}", 1000000000))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Test createPessoa")
    public void testCreatePessoa() throws Exception {
        mockMvc.perform(post("/pessoa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createJsonValido))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());

        mockMvc.perform(post("/pessoa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createJsonInvalido1))
                .andExpect(status().isBadRequest());

        mockMvc.perform(post("/pessoa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createJsonInvalido2))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Test updatePessoa")
    void testUpdatePessoa() throws Exception {

        // Update válido
        mockMvc.perform(put("/pessoa/{id}", 3)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateJsonValido))
                .andExpect(status().isOk());

//        // Updates inválidos
        mockMvc.perform(put("/pessoa/{id}", 3)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateJsonInvalido1))
                .andExpect(status().isBadRequest());
        mockMvc.perform(put("/pessoa/{id}", 3)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateJsonInvalido2))
                .andExpect(status().isBadRequest());
        mockMvc.perform(put("/pessoa/{id}", 3)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateJsonInvalido3))
                .andExpect(status().isBadRequest());

        // Updates com id inexistente mas com corpo
        mockMvc.perform(put("/pessoa/{id}", 100000)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateJsonInvalido3))
                .andExpect(status().isBadRequest());
        mockMvc.perform(put("/pessoa/{id}", 100000)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateJsonValido))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Test deletePessoa")
    void deletePessoa() throws Exception {
        mockMvc.perform(delete("/pessoa/{id}", 4)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/pessoa/{id}", 10000000)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}