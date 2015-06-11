package domino.ia;


public class MiniMaxPodaABIA extends IA {

    public MiniMaxPodaABIA(Domino d) {
        super(d);
    }

    @Override
    public Acao executa(Estado e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    função ALPHA-BETA-SEARCH(estado) retorna uma ação
//    v = MAX- VALUE(estado, -oo, +oo)
//    retorna a ação em ACTIONS(estado) com valor v
//    função MAX-VALUE(estado,alfa,beta) retorna um valor de utilidade
//    if TERMINAL-TEST(estado) then retorna UTILITY(estado)
//    v = - 00
//    for each a in ACTIONS(estado) do
//    v = MAX(v, MIN-VALUE(RESULT(s,a),alfa,beta))
//    if v >= beta then retorna v
//    alfa = MAX(alfa, v)
//    retorna v
//    função MIN-VALUE(estado,alfa,beta) retorna um valor de utilidade
//    if TERMINAL-TEST(estado) then retorna UTILITY(estado)
//    v = + 00
//    for each a in ACTIONS(estado) do
//    v = MIN(v, MAX-VALUE(RESULT(s,a),alfa,beta))
//    if v <= alfa then retorna v
//    beta = MIN(beta, v)
//    retorna v
}
