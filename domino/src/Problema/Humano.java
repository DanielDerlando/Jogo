package Problema;

import Estado.Estado;
import Util.Label;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Humano extends Jogador {

    Scanner in;

    public Humano(String nome) {
        this.mao = new ArrayList<>();
        this.nome=nome;
    }

    public Peca escolhePeca() {
        boolean valido;
        int indice;
        in = new Scanner(System.in);
        do {
            verMao();
            System.out.print("Entre com o indice da peça que deseja jogar: ");
            try {
                indice = in.nextInt();
            } catch (InputMismatchException e) {
                String passar = in.nextLine();
                if (passar.equals("passar")) {
                    return null;
                } else {
                    indice = Integer.MAX_VALUE;
                }
            }
            valido = mao.size() > indice && indice >= 0;
        } while (!valido);
        in = null;
        return mao.get(indice);
    }

    public int escolhePonta() {
        in = new Scanner(System.in);
        System.out.println("Digite a ponta desejada:\n[Esquerda: 0 | Direita: 1]");
        int retorno = in.nextInt();
        in = null;
        return retorno;
    }

    public Estado joga(Peca pecaEscolhida, int pontaEscolhida, Estado estadoReal) {
        if (pontaEscolhida == Label.PONTA_ESQUERDA) {
            if (!estadoReal.getMesa().inserirEsquerda(pecaEscolhida)) {
                System.out.println("Peça Inválida, tente novamente");
                return null;
            }
        } else if (pontaEscolhida == Label.PONTA_DIREITA) {
            if (!estadoReal.getMesa().inserirDireita(pecaEscolhida)) {
                System.out.println("Peça Inválida, tente novamente");
                return null;
            }
        } else {
            System.out.println("Ponta Inválida, tente novamente");
            return null;
        }
        this.mao.remove(pecaEscolhida);
        return new Estado(estadoReal.getMesa(), estadoReal.getJogadorIa(), Label.JOGADOR1, this.mao);
    }

    public Estado passar(Estado estadoReal) {
        return new Estado(estadoReal.getMesa(), estadoReal.getJogadorIa(), Label.JOGADOR1, this.mao);
    }

    @Override
    public Estado executa(Estado estadoReal) {
        Estado retorno;
        do {
            Peca pecaEscolhida = escolhePeca();
            if (pecaEscolhida == null) {//quer passar a vez
                retorno = passar(estadoReal);
            } else {
                int pontaEscolhida = escolhePonta();
                retorno = joga(pecaEscolhida, pontaEscolhida, estadoReal);
            }
        } while (retorno == null);
        return retorno;
    }

}
