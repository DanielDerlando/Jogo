package Acao;

import Estado.Estado;
import Util.Label;
import Problema.Peca;

public class AcaoPassa extends Acao {

    public AcaoPassa(Peca p, int ponta) {
        super(p, ponta);
    }
    
    public AcaoPassa(){
        super();
    }

    @Override
    public Estado executa(Estado e) {
        int proxJogador;
        if (e.getJogadorDaVez() == Label.JOGADOR_MAX) {
            proxJogador = Label.JOGADOR_MIN;
        } else {
            proxJogador = Label.JOGADOR_MAX;
        }        
        return new Estado(e.getMesa(), e.getIa(), proxJogador, e.getPecasInimigo());

    }

}
