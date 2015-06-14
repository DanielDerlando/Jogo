package IA;

import Acao.Acao;
import Estado.Estado;
import Problema.Peca;
import Problema.Problema;
import Util.Label;
import java.util.ArrayList;

public class MiniMaxCutOff extends IA {
    
    public static int nivel;
    public MiniMaxCutOff(Problema p) {
        super(p);
    }

    @Override
    public Acao executa(Estado e) {
        nivel=e.getMesa().getLista().size();
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
        if (getProblema().cut_off(e, 5)) {
            return getProblema().eval(e);
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
        if (getProblema().cut_off(e, 5)) {
            return getProblema().eval(e);
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
