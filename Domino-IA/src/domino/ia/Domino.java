package domino.ia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Domino {//Gerencia o jogo

    private Jogador jogador1;
    private Jogador jogador2;
    private Jogador jogadorDaVez;
    private Repositorio reposi;
    private Mercado mercado;
    private Mesa mesa;

    public Domino() {
        mesa = new Mesa();
        reposi = new Repositorio();
        int opcao = 0;
        boolean executa = false;
        Scanner leitor = new Scanner(System.in);
        do {
            System.out.println("Escolha o número da opção:"
                    + "\n 1 -> Você x Computador;"
                    + "\n 2 -> Computador x Computador;");
            opcao = leitor.nextInt();
            switch (opcao) {
                case 1:
                    jogador1 = new Humano();
                    jogador2 = new Computador(new MiniMaxPodaABIA());
                    break;
                case 2:
                    jogador1 = new Computador(new MiniMaxPodaABIA());
                    jogador2 = new Computador(new MCTSIA());
                    break;
                default:
                    System.out.println("Digite Novamente uma opção!");
                    executa = true;
                    break;
            }
        } while (executa);
    }

    public void distribui() {//falta implementar contagem de gabão!Tem que ser menor que 4 para cada jogador!
        ArrayList<Peca> aux = new ArrayList<>();
        do {
            for (int i = 0; i < reposi.getPecas().size(); i++) {
                aux.add(reposi.getPecas().get(i));
            }
            Collections.shuffle(aux);
            for (int i = 0; i < 7; i++) {
                jogador1.mao.add(aux.remove(i));
                System.out.print("Jogador 1: " + jogador1.mao.get(i).getEsquerda() + "/" + jogador1.mao.get(i).getDireita() + "\t");
                jogador2.mao.add(aux.remove(i));//Pode criar erros sem ter certeza do que tah fazendo.
                System.out.println("Jogador 2: " + jogador2.mao.get(i).getEsquerda() + "/" + jogador2.mao.get(i).getDireita());
            }
            mercado = new Mercado(aux);//assumindo que existem somente 14 peças dentro de repositório agora.
            System.out.println("Distribuiu");
        } while (!jogador1.maoEhValida() || !jogador2.maoEhValida());
        for (int i = 0; i < mercado.getMercado().size(); i++) {
            System.out.print(mercado.getMercado().get(i).toString() + " ");
        }
    }

    public void executa() {//Turnos e tudo mais...
        boolean jogarNovamente = false;
        distribui();//1º Passo = distribuir peças.
        realizaPrimeiraJogada();
        
        do {
            mesa.verMesa();
            Peca pecaJog1 = jogadorDaVez.escolhePeca();
            jogadorDaVez.escolhePonta();
            
            if (mesa.inserirEsquerda(pecaJog1)) {
                jogadorDaVez.mao.remove(pecaJog1);
                jogarNovamente = false;
            } else if (mesa.inserirDireita(pecaJog1)) {
                jogadorDaVez.mao.remove(pecaJog1);
                jogarNovamente = false;
            } else {
                jogarNovamente = true;
            }
        } while (jogarNovamente);
    
        
        //Jogador com maior gabão joga.
        //o outro jogador joga.
        //intercala os jogadores.
        
    }

    public void realizaPrimeiraJogada() {
        Peca pecaInicial = verificaJogadaInicial(jogador1, jogador2);//Verifica quem começa por causa do gabão.
        if (pecaInicial == null) {
            pecaInicial = jogadorDaVez.escolhePeca();
        }
        jogadorDaVez.mao.remove(pecaInicial);
        mesa.jogadaInicial(pecaInicial);
        trocaJogador();
    }

    /**
     * Seleciona o maior gabão entre as duas mãos dos jogadores e seta o
     * jogadorDaVez com o jogador que possuia o maior gabão.
     */
    private Peca verificaJogadaInicial(Jogador jogador1, Jogador jogador2) {
        //verifica qual mão possui o maior gabão e seta o jogador que possui maior gabao como o jogadorDaVez

        Peca pecaMaior1 = new Peca(-1, -1);
        Peca pecaMaior2 = new Peca(-1, -1);
        for (int i = 0; i < jogador1.mao.size(); i++) {
            if (jogador1.mao.get(i).ehGabao() && jogador1.mao.get(i).getDireita() > pecaMaior1.getDireita()) {
                pecaMaior1 = jogador1.mao.get(i);
            }
            if (jogador2.mao.get(i).ehGabao() && jogador2.mao.get(i).getDireita() > pecaMaior2.getDireita()) {
                pecaMaior2 = jogador2.mao.get(i);
            }
        }
        if (pecaMaior1.getDireita() > pecaMaior2.getDireita()) {
            jogadorDaVez = jogador1;
            return pecaMaior1;
        } else if (pecaMaior1.getDireita() < pecaMaior2.getDireita()) {
            jogadorDaVez = jogador2;
            return pecaMaior2;
        } else { //se nenhum jogador possui gabão, o jogador1 é setado como jogadorDaVez
            jogadorDaVez = jogador1;
            return null;
        }
    }

    private void trocaJogador() {
        if(jogadorDaVez.equals(jogador1)){
            jogadorDaVez = jogador2;
        }else{
            jogadorDaVez = jogador1;
        }
        
    }
    
    public boolean ehObjetivo(Estado e){
        return true;
    }
    
    public ArrayList<Estado> geraSucessor(){
        return null;
    }
    
    public void defineUtilidade(){
        
    }
    
    
    

}
