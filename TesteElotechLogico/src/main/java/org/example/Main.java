package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<Aluno> alunos = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] iputNotas;

        // Exemplo de input: '84 29 57 56 55 36 37 38 39 40 41'
        System.out.print("Digite uma série de notas (int) separados por espaços: ");
        String input = scanner.nextLine();

        iputNotas = input.split(" ");

        for (String numberString : iputNotas) {
            try{
                alunos.add(new Aluno(Integer.parseInt(numberString)));
            }
            catch (NumberFormatException nfe){
                System.out.printf("Input '%s' inválido.\n\n", numberString);
            }
        }

        alunos.forEach(Aluno::setNota);
        alunos.forEach(Main::printResultado);
    }

    private static void printResultado(Aluno aluno){
        int notaFinal = aluno.getNotaFinal(),
                notaInicial = aluno.getNotaInicial(),
                multiploDeCinco = aluno.getProximoMultiploCinco(notaFinal);
        boolean isAprovado = aluno.getIsAprovado(),
                isArredondado = aluno.isArredondado();

        System.out.print(
                isAprovado
                        ? "Aprovado. "
                        : "Reprovado. "
        );

        System.out.print("Nota inicial: " + notaInicial + ", Nota final: " + notaFinal + ". ");

        if (notaFinal < 40)
            System.out.println("Não arredonda (resultado é menor que 40)\n");

        else if (isArredondado)
            System.out.println("Arredondado para " + multiploDeCinco
                    + " ("+multiploDeCinco+" - " + notaInicial + " é menor que 3)\n");

        else if (notaInicial == notaFinal && notaInicial == multiploDeCinco)
            System.out.println("Não arredonda (" + notaInicial + " já é multiplo de 5)\n");

        else System.out.println("Não arredonda "
                        + "("+multiploDeCinco+" - " + notaInicial
                        + (multiploDeCinco - notaInicial == 3
                        ?" é igual a"
                        :" é maior que")
                        + " 3)\n");
    }
}