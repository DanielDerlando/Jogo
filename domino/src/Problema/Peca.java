package Problema;

public class Peca {

    private int esquerda;
    private int direita;
    private int dono;
    
    public Peca(int esquerda, int direita, int dono) {
        this.esquerda = esquerda;
        this.direita = direita;
        this.dono = dono;
    }

    public int getEsquerda() {
        return esquerda;
    }

    public int getDireita() {
        return direita;
    }

    public int getDono() {
        return dono;
    }

    public void setDono(int dono) {
        this.dono = dono;
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
    public int getValor(){
        return this.direita+this.esquerda;
    }

}
