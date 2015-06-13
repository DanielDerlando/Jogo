package Estado;

import Problema.Jogador;
import Problema.Mesa;
import Problema.Peca;
import java.util.ArrayList;

public class Estado {

    private final Mesa mesa;
    private final Jogador jogadorIA;
    private int jogadorDaVez;
    private ArrayList<Peca> pecasInimigo = new ArrayList<>();
//    private int utilidade;

    public Estado(Mesa mesa, Jogador jogadorIA, int jogadorDaVez, ArrayList<Peca> pecasInimigo) { //construtor copiando
        this.mesa = mesa.copia();
        this.jogadorDaVez = jogadorDaVez;
        this.pecasInimigo = new ArrayList<>();
        this.pecasInimigo.addAll(pecasInimigo);
        this.jogadorIA = jogadorIA;
    }

    public ArrayList<Peca> getPecasInimigo (){
        return pecasInimigo;
    }
    
    public Mesa getMesa() {
        return mesa;
    }

    public Jogador getIa() {
        return jogadorIA;
    }

    public int getJogadorDaVez() {
        return jogadorDaVez;
    }

    public Jogador getJogadorIA() {
        return jogadorIA;
    }

    public void setJogadorDaVez(int jogadorDaVez) {
        this.jogadorDaVez = jogadorDaVez;
    }
}
