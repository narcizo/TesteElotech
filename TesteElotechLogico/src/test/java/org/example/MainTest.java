package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {
    private static final Map<Aluno, Integer> alunosTest = new HashMap<>();

    @BeforeAll
    static void setup() {
        // Exemplos pdf
        alunosTest.put(new Aluno(84), 85);
        alunosTest.put(new Aluno(29), 29);
        alunosTest.put(new Aluno(57), 57);

        // Casos normais
        alunosTest.put(new Aluno(56), 56);
        alunosTest.put(new Aluno(55), 55);
        alunosTest.put(new Aluno(96), 96);
        alunosTest.put(new Aluno(36), 36);
        alunosTest.put(new Aluno(44), 45);

        // Casos de borda
        alunosTest.put(new Aluno(37), 37);
        alunosTest.put(new Aluno(38), 40);
        alunosTest.put(new Aluno(39), 40);
        alunosTest.put(new Aluno(40), 40);
        alunosTest.put(new Aluno(41), 41);
        alunosTest.put(new Aluno(98), 100);
        alunosTest.put(new Aluno(99), 100);
        alunosTest.put(new Aluno(100), 100);
        alunosTest.put(new Aluno(101), 100);
        alunosTest.put(new Aluno(104), 100);
    }

    private static Stream<Aluno> provideAlunos() {
        return alunosTest.keySet().stream();
    }

    @ParameterizedTest
    @MethodSource("provideAlunos")
    void testCalculoNota(Aluno aluno) {
        int expected = alunosTest.get(aluno);
        int actual = aluno.calculoNota();
        assertEquals(expected, actual, "Aluno: " + aluno);
    }
}