package domino.ia;

import java.util.ArrayList;

public class MiniMax extends IA {

    public MiniMax(Domino d) {
        super(d);
    }

    @Override
    public Acao executa(Estado e) {
        ArrayList<Acao> acoes = getDomino().acoes(e);
        //ArrayList<Integer> valoresUtilidade = new ArrayList<>();
        int valor_max = Integer.MIN_VALUE;
        Acao maxAcao = null;
        for (Acao aux : acoes) {
            int valor_atual = valor_min(getDomino().resultado(e, aux));            
            if(valor_atual > valor_max){
                maxAcao = aux;
                valor_max = valor_atual;
            }            
        }        
        return maxAcao;
    }

    public int valor_max(Estado e) {
        int utilidade;
        if (getDomino().testeDeTermino(e)) {
            return getDomino().utilidade(e);
        } else {
            utilidade = Integer.MIN_VALUE;
            Estado estado;
            int valorMin;
            for (Acao acaoDaVez : getDomino().acoes(e)) {
                estado = getDomino().resultado(e, acaoDaVez);
                valorMin = valor_min(estado);
                if (utilidade < valorMin) {
                    utilidade = valorMin;
                }
            }
            return utilidade;
        }
    }

    private int valor_min(Estado e) {
        int utilidade;
        if (getDomino().testeDeTermino(e)) {
            return getDomino().utilidade(e);
        } else {
            utilidade = Integer.MAX_VALUE;
            Estado estado;
            int valorMax;
            for (Acao acaoDaVez : getDomino().acoes(e)) {
                estado = getDomino().resultado(e, acaoDaVez);
                valorMax = valor_max(estado);
                if (utilidade > valorMax) {
                    utilidade = valorMax;
                }
            }
            return utilidade;
        }
    }

//    function MINIMAX-DECISION(estado) retorna uma ação
//return arg max a in ACTIONS ( s) MIN-VALUE(RESULT(state, a))
//______________________________________________________________
//function MAX-VALUE( estado) retorna um valor de utilidade
//if TERMINAL-TEST(estado) then return UTILITY(estado)
//v = - 00
//for each a in ACTIONS(estado) do
//v <- MAX(v, MIN-VALUE(RESULT(s, a)))
//return v
//______________________________________________________________
//function MIN-VALUE( estado) retorna um valor de utilidade
//if TERMINAL-TEST(estado) then return UTILITY(estado)
//v = 00
//for each a in ACTIONS(estado) do
//v <- MIN(v, MAX-VALUE(RESULT(s, a)))
//return v
}
