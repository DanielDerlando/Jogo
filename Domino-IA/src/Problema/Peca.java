package Problema;

public class Peca {

    private int esquerda;
    private int direita;

    public Peca(int esquerda, int direita) {
        this.esquerda = esquerda;
        this.direita = direita;
    }

    public int getEsquerda() {
        return esquerda;
    }

    public int getDireita() {
        return direita;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Peca outra = (Peca) obj;
        return (this.esquerda == outra.esquerda && this.direita == outra.direita)
                || (this.direita == outra.esquerda && this.esquerda == outra.direita);
    }

    public void girarPeca() {
        int aux = this.esquerda;
        this.esquerda = this.direita;
        this.direita = aux;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + this.esquerda;
        hash = 61 * hash + this.direita;
        return hash;
    }

    @Override
    public String toString() {
        return "[" + esquerda + "|" + direita + "]";
    }
    public boolean ehGabao(){
        return this.direita==this.esquerda;
    }

}
