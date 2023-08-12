package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class MainTest {
    private static final List<Aluno> alunosTest = new ArrayList<>();

    @BeforeAll
    static void setup() {
        // Exemplos pdf
        alunosTest.add(new Aluno(84));
        alunosTest.add(new Aluno(29));
        alunosTest.add(new Aluno(57));

        // Casos normais
        alunosTest.add(new Aluno(56));
        alunosTest.add(new Aluno(55));
        alunosTest.add(new Aluno(96));
        alunosTest.add(new Aluno(36));
        alunosTest.add(new Aluno(44));

        // Casos de borda
        alunosTest.add(new Aluno(37));
        alunosTest.add(new Aluno(38));
        alunosTest.add(new Aluno(39));
        alunosTest.add(new Aluno(40));
        alunosTest.add(new Aluno(41));
        alunosTest.add(new Aluno(98));
        alunosTest.add(new Aluno(99));
        alunosTest.add(new Aluno(100));
        alunosTest.add(new Aluno(101));
        alunosTest.add(new Aluno(104));
    }

    @Test()
    void trueAssumption(){
        assumeTrue(alunosTest.get(0).calculoNota() == 85);
        assumeTrue(alunosTest.get(1).calculoNota() == 29);
        assumeTrue(alunosTest.get(2).calculoNota() == 57);

        assumeTrue(alunosTest.get(3).calculoNota() == 56);
        assumeTrue(alunosTest.get(4).calculoNota() == 55);
        assumeTrue(alunosTest.get(5).calculoNota() == 96);
        assumeTrue(alunosTest.get(6).calculoNota() == 36);
        assumeTrue(alunosTest.get(7).calculoNota() == 45);

        assumeTrue(alunosTest.get(8).calculoNota() == 37);
        assumeTrue(alunosTest.get(9).calculoNota() == 40);
        assumeTrue(alunosTest.get(10).calculoNota() == 40);
        assumeTrue(alunosTest.get(11).calculoNota() == 40);
        assumeTrue(alunosTest.get(12).calculoNota() == 41);
        assumeTrue(alunosTest.get(13).calculoNota() == 100);
        assumeTrue(alunosTest.get(14).calculoNota() == 100);
        assumeTrue(alunosTest.get(15).calculoNota() == 100);
        assumeTrue(alunosTest.get(16).calculoNota() == 100);
        assumeTrue(alunosTest.get(17).calculoNota() == 100);

    }

}