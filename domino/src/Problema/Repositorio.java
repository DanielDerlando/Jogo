package Problema;

import java.util.ArrayList;

public class Repositorio {

    private final ArrayList<Peca> pecas;

    public Repositorio() {
        this.pecas = new ArrayList<>();
        for (int i = 0; i <= 6; i++) {
            for (int j = i; j <= 6; j++) {
                pecas.add(new Peca(i, j,-1));
//                System.out.println(i + "/" + j);
            }
        }
    }

    public int getTamanho() {
        return pecas.size();
    }

    public Peca getPeca(int i) {
        return pecas.get(i);
    }

    public boolean complemento(ArrayList<Peca> l) {
        return pecas.removeAll(l);
    }

    public Repositorio copia() {
        Repositorio novo = new Repositorio();
        for (Peca peca : pecas) {
            novo.pecas.add(peca);
        }
        return novo;
    }

}
