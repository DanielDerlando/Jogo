package Problema;

import Estado.Estado;
import Util.Label;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Humano extends Jogador {

    Scanner in;

    public Humano() {
        this.mao = new ArrayList<>();
        
    }

    public Peca escolhePeca() {
        boolean valido;
        int indice;
        in = new Scanner(System.in);
        do {
            verMao();
            System.out.print("Entre com o indice da peça que deseja jogar: ");
            try{
            indice = in.nextInt();
            }catch(InputMismatchException e){
                String passar = in.nextLine();
                if(passar.equals("passar")){
                    return null;
                }else{
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
        in=null;
        return retorno;
    }

    //IMPLEMENTAR A COMPRA QUE A IA SIMULA, NA COMPRA NÂO ESTAMOS MUDANDO A MÂO NEM O MERCADO DO HUMANO DE FATO
    public void decidirPeca() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Estado joga(Peca pecaEscolhida, int pontaEscolhida, Estado estadoReal) {
        if (pontaEscolhida == Label.PONTA_ESQUERDA) {
            if(!estadoReal.getMesa().inserirEsquerda(pecaEscolhida)){
                System.out.println("Peça Inválida, tente novamente");
                return null;
            }
        } else if (pontaEscolhida == Label.PONTA_DIREITA) {
            if(!estadoReal.getMesa().inserirDireita(pecaEscolhida)){
                System.out.println("Peça Inválida, tente novamente");
                return null;
            }
        } else {
            System.out.println("Ponta Inválida, tente novamente");
            return null;
        }
        this.mao.remove(pecaEscolhida);
        return new Estado(estadoReal.getMesa(), estadoReal.getIa(), Label.JOGADOR_COMPUTADOR, this.mao);
    }
    public Estado passar(Estado estadoReal){
        return new Estado(estadoReal.getMesa(), estadoReal.getIa(), Label.JOGADOR_COMPUTADOR, this.mao);
    }

}
