package domino.ia;

public class Estado {
    Mesa mesa;
    int qtdPecasMercado;
    int qtdPecasJogadodor1;
    int qtdPecasJogadodor2;
    int jogadorDaVez;
    
    public Estado(Mesa mesa, int qtdPecasMercado, int qtdPecasJogadodor1, int qtdPecasJogadodor2, int jogadorDaVez){
        this.mesa = mesa.copia();
        this.qtdPecasJogadodor1 = qtdPecasJogadodor1;
        this.qtdPecasJogadodor2 = qtdPecasJogadodor2;
        this.qtdPecasMercado = qtdPecasMercado;
    }
    
}
