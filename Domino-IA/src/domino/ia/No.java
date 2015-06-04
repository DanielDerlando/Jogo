package domino.ia;

public class No {
    private final No pai;
    private final Estado estado;
    private final Jogada jogada;
    private int utilidade;
    
    public No(No pai, Estado estado, Jogada jogada){
        this.pai = pai;
        this.estado = estado;
        this.jogada = jogada;
    }
    
}
