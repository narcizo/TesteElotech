package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Aluno> alunos= new ArrayList<>();

        alunos.add(new Aluno(84));
        alunos.add(new Aluno(29));
        alunos.add(new Aluno(57));
        alunos.add(new Aluno(56));
        alunos.add(new Aluno(55));
        alunos.add(new Aluno(36));
        alunos.add(new Aluno(37));
        alunos.add(new Aluno(38));
        alunos.add(new Aluno(39));
        alunos.add(new Aluno(40));
        alunos.add(new Aluno(41));

        alunos.forEach(Aluno::calculoNota);
    }



    private static void printResultado(String s){

    }
}