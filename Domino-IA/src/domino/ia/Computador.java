package domino.ia;

import java.util.ArrayList;

public class Computador extends Jogador {
    private IIA ia;

    public Computador(IIA ia) {
        this.ia = ia;
        this.mao = new ArrayList<>();
    }
    
    @Override
    public Peca escolhePeca() {
       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Peca escolhePonta() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
