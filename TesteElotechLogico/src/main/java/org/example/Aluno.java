package org.example;

public class Aluno {
    private int notaInicial;
    private int notaFinal;
    private boolean isAprovado;
    private boolean isArredondado;

    public Aluno(int nota) {
        this.notaInicial = nota;
        this.isAprovado = false;
        this.isArredondado = false;
    }

    public int getNotaFinal() {
        return notaFinal;
    }

    public void setNota() {
        this.notaFinal = this.calculoNota();
        this.setIsAprovado(this.notaFinal >= 40);
    }

    public boolean getIsAprovado() {
        return isAprovado;
    }

    public void setIsAprovado(boolean aprovado) {
        this.isAprovado = aprovado;
    }

    public boolean isArredondado() {
        return isArredondado;
    }

    public void setArredondado(boolean arredondado) {
        isArredondado = arredondado;
    }

    public int getNotaInicial() {
        return notaInicial;
    }

    public int calculoNota(){
        int notaCalc;

        if (this.getNotaInicial() < 0) notaCalc = 0;
        else if (this.getNotaInicial() > 100) notaCalc = 100;
        else notaCalc = this.getNotaInicial();

        if(notaCalc >= 38) {
            int multiploDeCinco = this.getProximoMultiploCinco(notaCalc);

            if (multiploDeCinco - notaCalc < 3 && multiploDeCinco - notaCalc > 0) {
                this.setArredondado(true);
                return multiploDeCinco;
            }
        }

        return notaCalc;
    }

    public int getProximoMultiploCinco(int numeroAtual){
        do {
            if (numeroAtual % 5 == 0)
                return numeroAtual;
            numeroAtual++;
        }while (true);
    }
}
