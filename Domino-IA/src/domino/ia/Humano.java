package domino.ia;

import java.util.ArrayList;
import java.util.Scanner;

public class Humano extends Jogador {

    Scanner in;

    public Humano() {
        this.mao = new ArrayList<>();
        in = new Scanner(System.in);
    }

    public Peca escolhePeca() {
        boolean valido;
        int indice;
        do {
            verMao();
            System.out.print("Entre com o indice da peça que deseja jogar: ");
            indice = in.nextInt();
            valido = mao.size() > indice && indice >= 0;
        } while (!valido);
        return mao.get(indice);
    }

    public int escolhePonta() {
        System.out.println("Digite a ponta desejada:\n[Esquerda: 0 | Direita: 1]");
        return in.nextInt();
    }

    //IMPLEMENTAR A COMPRA QUE A IA SIMULA, NA COMPRA NÂO ESTAMOS MUDANDO A MÂO NEM O MERCADO DO HUMANO DE FATO
    public void decidirPeca() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Estado joga(Peca pecaEscolhida, int pontaEscolhida, Estado estadoReal) {
        if (pontaEscolhida == 0) { //esquerda
            estadoReal.getMesa().inserirEsquerda(pecaEscolhida);
        } else if (pontaEscolhida == 1) { //direita
            estadoReal.getMesa().inserirDireita(pecaEscolhida);

        } else {
            System.out.println("Jogada Inválida, tente novamente");
            return null;
        }
        this.mao.remove(pecaEscolhida);
        return new Estado(estadoReal.getMesa(), estadoReal.getIa(), Label.JOGADOR_COMPUTADOR, this.mao);
    }

}
