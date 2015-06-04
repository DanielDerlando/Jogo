package domino.ia;

import java.util.ArrayList;
import java.util.Random;

public class Mercado {

    private final ArrayList<Peca> mercado;
    private final Random aleatorio;

    public Mercado(ArrayList<Peca> mercado) {
        this.mercado = mercado;
        aleatorio = new Random();
    }

    public Peca vender() {
        Peca pecaVendida;
        pecaVendida = mercado.remove(aleatorio.nextInt(mercado.size()));
        return pecaVendida;
    }

    public ArrayList<Peca> getMercado() {
        return mercado;
    }

}
