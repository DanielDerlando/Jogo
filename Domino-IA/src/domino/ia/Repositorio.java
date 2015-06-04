package domino.ia;

import java.util.ArrayList;

public class Repositorio {//tah criando certo, duplicatas não estão aqui...

    private final ArrayList<Peca> pecas;

    public Repositorio() {
        this.pecas = new ArrayList<>();
        for (int i = 0; i <= 6; i++) {
            for (int j = i; j <= 6; j++) {
                pecas.add(new Peca(i, j));
//                System.out.println(i + "/" + j);
            }
        }
    }

    public ArrayList<Peca> getPecas() {
        return pecas;
    }

}
