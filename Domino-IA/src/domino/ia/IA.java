package domino.ia;

public abstract class IA {
    private Domino d;

    public IA(Domino d) {
        this.d = d;
    }

    public Domino getDomino() {
        return d;
    }
    
    public abstract Acao executa(Estado e);
    
}
