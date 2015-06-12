package domino.ia;

import java.util.ArrayList;


public class MiniMaxPodaABIA extends IA {

    public MiniMaxPodaABIA(Domino d) {
        super(d);
    }

    @Override
    public Acao executa(Estado e) {
        ArrayList<Acao> acoes = getDomino().acoes(e);
        //ArrayList<Integer> valoresUtilidade = new ArrayList<>();
        int valor_max = Integer.MIN_VALUE;
        Acao maxAcao = null;
        for (Acao aux : acoes) {
            int valor_atual = valor_min(getDomino().resultado(e, aux),Integer.MIN_VALUE,Integer.MAX_VALUE);            
            if(valor_atual > valor_max){
                maxAcao = aux;
                valor_max = valor_atual;
            }            
        }        
        return maxAcao;
    }

    public int valor_max(Estado e,int alfa,int beta) {
        if (getDomino().testeDeTermino(e)) {
            return getDomino().utilidade(e);
        } else {
            Estado estado;
            int valorMin = Integer.MIN_VALUE;
            for (Acao acaoDaVez : getDomino().acoes(e)) {
                estado = getDomino().resultado(e, acaoDaVez);
                valorMin = Math.max(valorMin,valor_min(estado,alfa,beta));
                if(valorMin>=beta){
                    return valorMin;
                }
                alfa = Math.max(alfa, valorMin);
            }
            return valorMin;
        }
    }

    private int valor_min(Estado e,int alfa,int beta) {
        if (getDomino().testeDeTermino(e)) {
            return getDomino().utilidade(e);
        } else {
            Estado estado;
            int valorMax = Integer.MAX_VALUE;
            for (Acao acaoDaVez : getDomino().acoes(e)) {
                estado = getDomino().resultado(e, acaoDaVez);
                valorMax = Math.min(valorMax,valor_max(estado,alfa,beta));
                if(valorMax<=alfa){
                    return valorMax;
                }
                beta = Math.min(beta, valorMax);
            }
            return valorMax;
        }
    }
}
