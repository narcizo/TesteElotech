package org.example;

public class Aluno {
    private int nota;
    private boolean isAprovado;

    public Aluno(int nota) {
        this.nota = nota;
        this.isAprovado = false;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public boolean getIsAprovado() {
        return isAprovado;
    }

    public void setIsAprovado(boolean aprovado) {
        isAprovado = aprovado;
    }

    public int calculoNota(){
        if (this.getNota() < 0) this.setNota(0);
        if (this.getNota() > 100) this.setNota(100);

        int nota = this.getNota();

        if(nota < 38){
            this.setIsAprovado(false);
            System.out.println("Nota " + nota + ". Não arredonda (resultado é menor que 40)\n");
        } else {
            int multiploDeCinco = getProximoMultiploCinco(nota);

            if(multiploDeCinco - nota < 3){
                this.setNota(multiploDeCinco);
                System.out.println("Nota " + nota + ". Arredonda para " + multiploDeCinco
                        + " ("+multiploDeCinco+" - " + nota + " é menor que 3)\n");
            }else {
                System.out.println("Nota " + nota + ". Não arredonda "
                        + "("+multiploDeCinco+" - " + nota
                        + (multiploDeCinco - nota == 3
                        ?" é igual a"
                        :" é maior que")
                        + " 3)\n");
            }
            if(this.getNota() >= 40)
                this.setIsAprovado(true);
        }

        return this.getNota();
    }

    private static int getProximoMultiploCinco(int numeroAtual){
        do {
            if (numeroAtual % 5 == 0)
                return numeroAtual;
            numeroAtual++;
        }while (true);
    }
}
