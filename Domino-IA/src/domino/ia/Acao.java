package domino.ia;

public abstract class Acao {

    private Peca peca;
    private int ponta;

    public Acao(Peca peca, int ponta) {
        this.peca = peca;
        this.ponta = ponta;
    }

    public Acao() {
    }

    public abstract Estado executa(Estado e);

    public Peca getPeca() {
        return peca;
    }

    public int getPonta() {
        return ponta;
    }

}
