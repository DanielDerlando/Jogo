package domino.ia;

import java.util.ArrayList;
import java.util.Scanner;

public class Humano extends Jogador {

    Scanner in;

    public Humano() {
        this.mao = new ArrayList<>();
        in = new Scanner(System.in);
    }

    @Override
    public Peca escolhePeca() {
        boolean valido = true;
        int indice;
        do {
            verMao();
            System.out.print("Entre com o indice da peça que deseja jogar: ");
            indice = in.nextInt();
            valido = mao.size() > indice && indice >= 0;
        } while (!valido);
        return mao.get(indice);
    }

    @Override
    public Peca escolhePonta() {
        return null;
    }

}
