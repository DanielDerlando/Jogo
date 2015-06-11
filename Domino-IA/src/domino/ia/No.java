package domino.ia;

public class No {
    private final No pai;
    private final Estado estado;
    private final AcaoJoga jogada;
    private int utilidade;
    
    public No(No pai, Estado estado, AcaoJoga jogada){
        this.pai = pai;
        this.estado = estado;
        this.jogada = jogada;
    }
    
}
