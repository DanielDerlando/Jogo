package Estado;

import Problema.Jogador;
import Problema.Mesa;
import Problema.Peca;
import java.util.ArrayList;

public class Estado {

    private final Mesa mesa;
    private final Jogador jogadorIA;
    private int jogadorDaVez;
    private final ArrayList<Peca> jogadorInimigo;
//    private int utilidade;

    public Estado(Mesa mesa, Jogador jogadorIA, int jogadorDaVez, ArrayList<Peca> pecasInimigo) { //construtor copiando
        this.mesa = mesa.copia();
        this.jogadorDaVez = jogadorDaVez;
        this.jogadorInimigo = pecasInimigo;
        this.jogadorIA = jogadorIA;
    }

    public ArrayList<Peca> getPecasInimigo (){
        return jogadorInimigo;
    }
    
    public Mesa getMesa() {
        return mesa;
    }


    public int getJogadorDaVez() {
        return jogadorDaVez;
    }


    public void setJogadorDaVez(int jogadorDaVez) {
        this.jogadorDaVez = jogadorDaVez;
    }

    public Jogador getJogadorIa() {
        return jogadorIA;
    }
    
}
