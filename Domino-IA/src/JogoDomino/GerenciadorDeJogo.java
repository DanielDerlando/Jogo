/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JogoDomino;

import Problema.Humano;
import Problema.Peca;
import Problema.Mesa;
import Problema.Computador;
import Problema.Repositorio;
import Estado.Estado;
import IA.MiniMaxPodaABIA;
import IA.MiniMax;
import IA.IA;
import Util.Label;
import Acao.Acao;
import Problema.Problema;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author Daniel
 */
public class GerenciadorDeJogo {

    private Humano jogadorHumano;
    private Computador jogadorIA;
    private Computador jogadorIA2;
    private Repositorio repositorio;
    private Problema p;

    public GerenciadorDeJogo(Problema p) {
        this.p = p;
    }

    public void iniciaJogo() {
        repositorio = new Repositorio();
        int opcao = 0;
        boolean executa = false;
        Scanner leitor = new Scanner(System.in);
        IA ia1, ia2;
        do {
            System.out.print("Menu: "
                    + "\n1 -> Você x Computador;"
                    + "\n2 -> Computador x Computador;"
                    + "\n Escolha o número da opção: "
            );
            opcao = leitor.nextInt();
            switch (opcao) {
                case 1:
                    ia1 = new MiniMaxPodaABIA(p);
                    jogadorIA = new Computador(ia1);
                    jogadorHumano = new Humano();
                    this.executaHxC(jogadorIA, jogadorHumano);
                    break;
                case 2:
                    ia1 = new MiniMaxPodaABIA(p);
                    ia2 = new MiniMax(p);
                    jogadorIA = new Computador(ia1);
                    jogadorIA2 = new Computador(ia2);
                    this.executaCxC(jogadorIA, jogadorIA2);
                    break;
                default:
                    System.out.println("Digite Novamente uma opção!");
                    executa = true;
                    break;
            }
        } while (executa);
    }

    public void distribui() {//falta implementar contagem de gabão!Tem que ser menor que 4 para cada jogador!
//        System.out.println("\n\n");
        ArrayList<Peca> aux = new ArrayList<>();
        do {
            for (int i = 0; i < 28; i++) {
                aux.add(repositorio.getPeca(i));
            }
            Collections.shuffle(aux);
            for (int i = 0; i < 14; i++) {
                jogadorHumano.mao.add(aux.remove(0));
//                System.out.print("Jogador 1: " + jogadorHumano.mao.get(i).getEsquerda() + "/" + jogadorHumano.mao.get(i).getDireita() + "\t");
                jogadorIA.mao.add(aux.remove(0));//Pode criar erros sem ter certeza do que tah fazendo.
//                System.out.println("Jogador 2: " + jogadorIA.mao.get(i).getEsquerda() + "/" + jogadorIA.mao.get(i).getDireita());
            }
            Repositorio repositorioAux = repositorio.copia();
            repositorioAux.complemento(jogadorIA.mao);
        } while (!(jogadorHumano.maoEhValida() && jogadorIA.maoEhValida()));

    }

    private void executaHxC(Computador jogadorIA, Humano jogadorHumano) {//Turnos e tudo mais...
//        /*boolean jogarNovamente = false;
        distribui();//1º Passo = distribuir peças.
        p.setEstadoReal(new Estado(new Mesa(), jogadorIA, Label.JOGADOR_COMPUTADOR, jogadorHumano.mao));
        Estado estadoReal = p.getEstadoReal();
        realizaPrimeiraJogada(estadoReal);
        while (!p.testeDeTermino(estadoReal)) {
            System.out.println("Mesa: ");
            estadoReal.getMesa().verMesa();
            if (estadoReal.getJogadorDaVez() == Label.JOGADOR_COMPUTADOR) { //se está na vez da IA jogar
                System.out.println("Aguarde um instante. Estou pensando!");
                Acao acao = jogadorIA.getIA().executa(new Estado(estadoReal.getMesa(), estadoReal.getJogadorIA(), estadoReal.getJogadorDaVez(), jogadorHumano.mao));
                p.setEstadoReal(p.resultado(estadoReal, acao));
                System.out.println("O Computador tem " + (estadoReal.getIa().mao.size() - 1) + " de peças na mão!");
            } else {  //se está na vez do Humano jogar
                Estado aux = null;
                do {
                    Peca pecaEscolhida = jogadorHumano.escolhePeca();
                    if (pecaEscolhida == null) {//quer passar a vez
                        aux = jogadorHumano.passar(estadoReal);
                    } else {
                        int pontaEscolhida = jogadorHumano.escolhePonta();
                        aux = jogadorHumano.joga(pecaEscolhida, pontaEscolhida, estadoReal);
                    }
                } while (aux == null);//enquanto jogada for inválida
                p.setEstadoReal(aux);

            }
            estadoReal = p.getEstadoReal();
        }
        exibeResultado(estadoReal);
    }

    private void executaCxC(Computador jogadorIA, Computador jogadorIA2) {

    }

    public void realizaPrimeiraJogada(Estado estado) {
        Peca gabaoDeSeis = new Peca(6, 6);
        Peca peca;
        for (int i = 0; i < 14; i++) {
            peca = jogadorHumano.mao.get(i);
            if (peca.equals(gabaoDeSeis)) {
                estado.getMesa().jogadaInicial(jogadorHumano.mao.remove(i));
                estado.setJogadorDaVez(Label.JOGADOR_COMPUTADOR);
                System.out.println("\n Você fez a primeira jogada!");
                return;
            }
        }
        for (int i = 0; i < 14; i++) {
            peca = jogadorIA.mao.get(i);
            if (peca.equals(gabaoDeSeis)) {
                estado.getMesa().jogadaInicial(jogadorIA.mao.remove(i));
                estado.setJogadorDaVez(Label.JOGADOR_HUMANO);
                System.out.println("\n Eu fiz a primeira jogada!");
                return;
            }
        }
    }

    /**
     * Verifica a última jogada e imprime o Vencedor. Se o estado está com o
     * humano como o jogador da vez a IA foi a última a jogar, logo a IA venceu.
     *
     *
     * @param estadoFinal Estado Final
     */
    private void exibeResultado(Estado estadoFinal) {
        estadoFinal.getMesa().verMesa();
        if (estadoFinal.getJogadorDaVez() == Label.JOGADOR_HUMANO) {
            System.out.println("Você perdeu :(");
        } else {
            System.out.println("Você venceu!");
        }
    }
}
