package JogoDomino;

import Problema.Domino;

public class DominoIA {

    public static void main(String[] args) {
        Domino domino = new Domino();
        GerenciadorDeJogo gj = new GerenciadorDeJogo(domino);
        gj.iniciaJogo();
        
    }
    
}
