package IA;

import Acao.Acao;
import Problema.Problema;
import Estado.Estado;

public class MiniMaxPodaAlfaBeta extends IA {

    public MiniMaxPodaAlfaBeta(Problema p) {
        super(p);
    }

    @Override
    public Acao executa(Estado e) {
        int alfa = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;
        int valor_atual = Integer.MIN_VALUE;
        Acao maxAcao = null;
        for (Acao aux : getProblema().acoes(e)) {
            valor_atual = Math.max(valor_atual, valor_min(getProblema().resultado(e, aux), Integer.MIN_VALUE, Integer.MAX_VALUE));
            if (valor_atual >= alfa) {
                maxAcao = aux;
                alfa = valor_atual;
            }
            if (valor_atual >= beta) {
                break;
            }            
        }
        return maxAcao;
    }

    public int valor_max(Estado e, int alfa, int beta) {
        if (getProblema().testeDeTermino(e)) {
            return getProblema().utilidade(e);
        } else {
            Estado estado;
            int valorMin = Integer.MIN_VALUE;
            for (Acao acaoDaVez : getProblema().acoes(e)) {
                estado = getProblema().resultado(e, acaoDaVez);
                valorMin = Math.max(valorMin, valor_min(estado, alfa, beta));
                if (valorMin >= beta) {
                    return valorMin;
                }
                alfa = Math.max(alfa, valorMin);
            }
            return valorMin;
        }
    }

    private int valor_min(Estado e, int alfa, int beta) {
        if (getProblema().testeDeTermino(e)) {
            return getProblema().utilidade(e);
        } else {
            Estado estado;
            int valorMax = Integer.MAX_VALUE;
            for (Acao acaoDaVez : getProblema().acoes(e)) {
                estado = getProblema().resultado(e, acaoDaVez);
                valorMax = Math.min(valorMax, valor_max(estado, alfa, beta));
                if (valorMax <= alfa) {
                    return valorMax;
                }
                beta = Math.min(beta, valorMax);
            }
            return valorMax;
        }
    }
}
