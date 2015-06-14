package Acao;

import Problema.Computador;
import Estado.Estado;
import Problema.Jogador;
import Util.Label;
import Problema.Peca;
import java.util.ArrayList;

public class AcaoJoga extends Acao {

    public AcaoJoga(Peca p, int ponta) {
        super(p, ponta);
    }

    @Override
    public Estado executa(Estado e) {
        int jogadorDaVez;
        Estado novo;

        if (e.getJogadorDaVez() == Label.JOGADOR_MAX) {
            jogadorDaVez = Label.JOGADOR_MIN;
            Jogador atualizaIA = ((Computador)e.getJogadorIa()).copia();
            atualizaIA.jogaPeca(getPeca());
            novo = new Estado(e.getMesa(), atualizaIA, jogadorDaVez, e.getPecasInimigo());
        } else {
            //Caso: JOGADOR_MIN
            jogadorDaVez = Label.JOGADOR_MAX;
            ArrayList<Peca> pecas = new ArrayList<>();
            pecas.addAll(e.getPecasInimigo());
            pecas.remove(getPeca());
            novo = new Estado(e.getMesa(), e.getJogadorIa(), jogadorDaVez, pecas);
        }

        if (getPonta() == Label.PONTA_ESQUERDA) {
            novo.getMesa().inserirEsquerda(getPeca());
        } else if (getPonta() == Label.PONTA_DIREITA) {
            novo.getMesa().inserirDireita(getPeca());
        }

        return novo;
    }

}
