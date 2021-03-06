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
import IA.MiniMaxPodaAlfaBeta;
import IA.MiniMax;
import IA.IA;
import Util.Label;
import Acao.Acao;
import IA.MiniMaxCutOff;
import Problema.Jogador;
import Problema.Problema;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author Daniel
 */
public class GerenciadorDeJogo {

    private Jogador jogador1;
    private Jogador jogador2;
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
        IA ia2, ia1;
        do {
            System.out.print("Menu: "
                    + "\n1 -> Você x Computador;"
                    + "\n2 -> Computador x Computador;"
                    + "\n Escolha o número da opção: "
            );
            opcao = leitor.nextInt();
            switch (opcao) {
                case 1:
                    leitor.nextLine();
                    System.out.print("Digite o seu nome: ");
                    jogador2 = new Humano(leitor.nextLine());
                    String nome = "";
                    do {
                        System.out.print("\n1 -> MiniMaxPodaAlfaBeta"
                                + "\n2 -> MiniMaxCutOff"
                                +"\nEntre com número da IA que deseja jogar contra:");
                        opcao = leitor.nextInt();
                        switch (opcao) {
                            case 1:
                                ia1 = new MiniMaxPodaAlfaBeta(p);
                                nome = "AlfaBeta";
                                break;
                            case 2:
                                ia1 = new MiniMaxCutOff(p);
                                nome = "CutOff";
                                break;
                            default:
                                System.out.println("Digite Novamente uma opção!");
                                executa = true;
                                ia1 = null;
                                break;
                        }
                    } while (executa);
                    executa = false;
                    jogador1 = new Computador(ia1, nome);
                    break;
                case 2:
                    ia1 = new MiniMaxPodaAlfaBeta(p);
                    ia2 = new MiniMaxCutOff(p);
                    jogador1 = new Computador(ia1, "PodaAlfaBeta");
                    jogador2 = new Computador(ia2, "CutOff");
                    break;
                default:
                    System.out.println("Digite Novamente uma opção!");
                    executa = true;
                    break;
            }
        } while (executa);
        executa();
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
                jogador2.mao.add(aux.remove(0));
                jogador2.mao.get(i).setDono(Label.JOGADOR2);
//                System.out.print("Jogador 1: " + jogadorHumano.mao.get(i).getEsquerda() + "/" + jogadorHumano.mao.get(i).getDireita() + "\t");
                jogador1.mao.add(aux.remove(0));//Pode criar erros sem ter certeza do que tah fazendo.
                jogador1.mao.get(i).setDono(Label.JOGADOR1);
//                System.out.println("Jogador 2: " + jogadorIA.mao.get(i).getEsquerda() + "/" + jogadorIA.mao.get(i).getDireita());
            }
            Repositorio repositorioAux = repositorio.copia();
            repositorioAux.complemento(jogador1.mao);
        } while (!(jogador2.maoEhValida() && jogador1.maoEhValida()));

    }

    private void executa() {//Turnos e tudo mais...
//        /*boolean jogarNovamente = false;
        distribui();//1º Passo = distribuir peças.
        p.setEstadoReal(new Estado(new Mesa(), jogador1, Label.JOGADOR1, jogador2.mao));
        Estado estadoReal = p.getEstadoReal();
        realizaPrimeiraJogada(estadoReal);
        while (!p.testeDeTermino(estadoReal)) {
            System.out.println("Mesa: ");
            estadoReal.getMesa().verMesa();
            if (estadoReal.getJogadorDaVez() == Label.JOGADOR1) { //se está na vez da IA jogar
                System.out.println("Turno "+jogador1.getNome()+":");
                p.setEstadoReal(jogador1.executa(p.getEstadoReal()));
            } else {  //se está na vez do Humano jogar
                System.out.println("Turno "+jogador2.getNome()+":");
                p.setEstadoReal(jogador2.executa(p.getEstadoReal()));

            }
            estadoReal = p.getEstadoReal();
        }
        exibeResultado(estadoReal);
    }

    public Estado realizaPrimeiraJogada(Estado estado) {
        Peca gabaoDeSeis = new Peca(6, 6, -1);
        Peca peca;
        for (int i = 0; i < 14; i++) {
            peca = estado.getPecasInimigo().get(i);
            if (peca.equals(gabaoDeSeis)) {
                estado.getMesa().jogadaInicial(estado.getPecasInimigo().remove(i));
                estado.setJogadorDaVez(Label.JOGADOR1);
                System.out.println("\n "+jogador2.getNome()+" fez a primeira jogada!");
                return estado;
            }
        }
        for (int i = 0; i < 14; i++) {
            peca = estado.getJogadorIa().mao.get(i);
            if (peca.equals(gabaoDeSeis)) {
                estado.getMesa().jogadaInicial(estado.getJogadorIa().mao.remove(i));
                estado.setJogadorDaVez(Label.JOGADOR2);
                System.out.println("\n "+jogador1.getNome()+" fez a primeira jogada!");
                return estado;
            }
        }
        return null;
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
        if (estadoFinal.getJogadorDaVez() == Label.JOGADOR2) {
            System.out.println(jogador1.getNome()+" venceu!");
        } else {
            System.out.println(jogador2.getNome()+" venceu!");
        }
    }
}
