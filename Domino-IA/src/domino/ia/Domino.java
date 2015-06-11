package domino.ia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Domino {//Gerencia o jogo

    private Humano jogadorHumano; 
    private Computador jogadorIA;
    private int jogadorDaVez;
    private Repositorio repositorio;
    private Mesa mesa;
    private Estado estadoReal;

    public Domino() {
        mesa = new Mesa();
        repositorio = new Repositorio();
        int opcao = 0;
        boolean executa = false;
        Scanner leitor = new Scanner(System.in);
        IA ia1;
        do {
            System.out.println("Escolha o número da opção:"
                    + "\n 1 -> Você x Computador;"
                    + "\n 2 -> Computador x Computador;");
            opcao = leitor.nextInt();
            switch (opcao) {
                case 1:
                    jogadorHumano = new Humano();
                    ia1 = new MiniMax(this);
                    jogadorIA = new Computador(ia1);
                    break;
                case 2:
                    ia1 = new MiniMax(this);
                    jogadorIA = new Computador(ia1);
                    //falta ia2
                    break;
                default:
                    System.out.println("Digite Novamente uma opção!");
                    executa = true;
                    break;
            }
        } while (executa);
    }

    public void distribui() {//falta implementar contagem de gabão!Tem que ser menor que 4 para cada jogador!
        System.out.println("\n\n");
        ArrayList<Peca> aux = new ArrayList<>();
        do {
            for (int i = 0; i < 28; i++) {
                aux.add(repositorio.getPeca(i));
            }
            Collections.shuffle(aux);
            for (int i = 0; i < 14; i++) {
                jogadorHumano.mao.add(aux.remove(0));
                System.out.print("Jogador 1: " + jogadorHumano.mao.get(i).getEsquerda() + "/" + jogadorHumano.mao.get(i).getDireita() + "\t");
                jogadorIA.mao.add(aux.remove(0));//Pode criar erros sem ter certeza do que tah fazendo.
                System.out.println("Jogador 2: " + jogadorIA.mao.get(i).getEsquerda() + "/" + jogadorIA.mao.get(i).getDireita());
            }
            Repositorio repositorioAux = repositorio.copia();
            repositorioAux.complemento(jogadorIA.mao);
        } while (!(jogadorHumano.maoEhValida() && jogadorIA.maoEhValida()));

    }

    public void executa() {//Turnos e tudo mais...
//        /*boolean jogarNovamente = false;
        distribui();//1º Passo = distribuir peças.
        boolean acabou = false;
        
        this.estadoReal = new Estado(mesa, jogadorIA, jogadorDaVez, jogadorHumano.mao); //A IA deveria calcular jogadorHumano.mao
        realizaPrimeiraJogada(estadoReal.getMesa()); 
        while (!acabou) {
            if (estadoReal.getJogadorDaVez() == Label.JOGADOR_COMPUTADOR) { //se está na vez da IA jogar
                Acao acao = jogadorIA.getIA().executa(new Estado(estadoReal.getMesa(), estadoReal.getJogadorIA(), estadoReal.getJogadorDaVez(), jogadorHumano.mao));
                estadoReal=resultado(estadoReal, acao);
            } else {  //se está na vez do Humano jogar
                boolean jogadaValida = false;
                while(!jogadaValida){
                    mesa.verMesa();
                    Peca pecaEscolhida = jogadorHumano.escolhePeca();
                    int pontaEscolhida = jogadorHumano.escolhePonta();
                    estadoReal=jogadorHumano.joga(pecaEscolhida, pontaEscolhida,estadoReal);
                }
            }
        }
    }

    public void realizaPrimeiraJogada(Mesa mesa) {
        Peca gabaoDeSeis = new Peca(6, 6);
        Peca peca;
        for (int i = 0; i < 14; i++) {
            peca = jogadorHumano.mao.get(i);
            if (peca.equals(gabaoDeSeis)) {
                mesa.jogadaInicial(jogadorHumano.mao.remove(i));
                jogadorDaVez = Label.JOGADOR_COMPUTADOR;
                return;
            }
        }
        for (int i = 0; i < 14; i++) {
            peca = jogadorIA.mao.get(i);
            if (peca.equals(gabaoDeSeis)) {
                mesa.jogadaInicial(jogadorIA.mao.remove(i));
                jogadorDaVez = Label.JOGADOR_HUMANO;
                return;
            }
        }
    }

    /**
     * Retorna a MAX_VALUE, caso NO_MAX venceu, MIN_VALUE, caso NO_MIN venceu e
     * em caso de empate retorna um valor coerente.
     *
     * @param e
     * @param jogador
     * @return
     */
    public int utilidade(Estado e) {
        if (e.getIa().mao.isEmpty()) { //se teve um vencedor
            return Integer.MAX_VALUE;
        } else if (e.getPecasInimigo().isEmpty()) {
            return Integer.MIN_VALUE;
        } else {//se teve um empate
            int soma = 0;
            for (Peca p : e.getPecasInimigo()) { //MIN
                soma = soma + p.getDireita() + p.getEsquerda();
            }
            for (Peca p : e.getIa().mao) { //MAX
                soma = soma - (p.getDireita() + p.getEsquerda());
            }
            return soma;
        }
    }

    public ArrayList<Acao> acoes(Estado e) { //quais as ações validas dado um estado, retorna as ações que vão gerar os proximos estados
        ArrayList<Acao> acoesPossiveis;
        Peca direita = e.getMesa().getPontaDireita();
        Peca esquerda = e.getMesa().getPontaEsquerda();
        acoesPossiveis = new ArrayList<>();
        if (e.getJogadorDaVez() == Label.JOGADOR_MAX) { //CASO MAX
            for (Peca pecaAtual : e.getIa().mao) {
                if ((pecaAtual.getDireita() == direita.getDireita()) || (pecaAtual.getEsquerda() == direita.getDireita())) {
                    acoesPossiveis.add(new AcaoJoga(pecaAtual, Label.PONTA_DIREITA));
                }
                if ((pecaAtual.getDireita() == esquerda.getEsquerda()) || (pecaAtual.getEsquerda() == esquerda.getEsquerda())) {
                    acoesPossiveis.add(new AcaoJoga(pecaAtual, Label.PONTA_ESQUERDA));
                }
            }
            if (acoesPossiveis.isEmpty()) {
                acoesPossiveis.add(new AcaoPassa());
            }
        } else { //CASO MIN
            for (Peca pecaAtual : e.getPecasInimigo()) {
                if ((pecaAtual.getDireita() == direita.getDireita()) || (pecaAtual.getEsquerda() == direita.getDireita())) {
                    acoesPossiveis.add(new AcaoJoga(pecaAtual, Label.PONTA_DIREITA));
                }
                if ((pecaAtual.getDireita() == esquerda.getEsquerda()) || (pecaAtual.getEsquerda() == esquerda.getEsquerda())) {
                    acoesPossiveis.add(new AcaoJoga(pecaAtual, Label.PONTA_ESQUERDA));
                }
            }
            if (acoesPossiveis.isEmpty()) {
                acoesPossiveis.add(new AcaoPassa());
            }
        }
        return acoesPossiveis;
    }

    public Estado resultado(Estado e, Acao a) {
        return a.executa(e);
    }

    /**
     * Se as jogadas disponíveis, dado um estado atual, tanto para MIN, quanto
     * para MAX, são passar a jogada. Ou se uma das mãos dos jogadores acabou
     *
     * @param e
     * @return
     */
    public boolean testeDeTermino(Estado e) {
        //se um dos dois jogadores venceu
        if (e.getIa().mao.isEmpty() || e.getPecasInimigo().isEmpty()) {
            return true;
        }
        //Caso não tenha acabado (as mãos contem peças que podem ser jogadas)
        int pontaDireita = e.getMesa().getPontaDireita().getDireita();
        int pontaEsquerda = e.getMesa().getPontaEsquerda().getEsquerda();

        for (Peca pecaMax : e.getIa().mao) {
            if (pecaMax.getDireita() == pontaDireita || pecaMax.getEsquerda() == pontaDireita
                    || pecaMax.getDireita() == pontaEsquerda || pecaMax.getEsquerda() == pontaEsquerda) {
                return false;
            }
        }
        for (Peca pecaMin : e.getPecasInimigo()) {
            if (pecaMin.getDireita() == pontaDireita || pecaMin.getEsquerda() == pontaDireita
                    || pecaMin.getDireita() == pontaEsquerda || pecaMin.getEsquerda() == pontaEsquerda) {
                return false;
            }
        }
        //caso mão nao esta vazia e ainda tenho peças pra jogar 
        return true;
    }

}
