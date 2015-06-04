package domino.ia;

import java.util.List;

public abstract class Jogador {

    List<Peca> mao;

    public abstract Peca escolhePeca();
    
    public abstract Peca escolhePonta();

    public boolean comprar(Mercado mercado) {
        return mao.add(mercado.vender());
    }

    public boolean taNaMao(Peca p) {
        return mao.contains(p);
    }

    public void verMao() {
        System.out.println("\nMão: ");
        for (Peca mao1 : mao) {
            System.out.print(mao1.toString() + "\t");
        }
        System.out.println("");
        for (int i = 0; i < mao.size(); i++) {
            System.out.print("  " + i + "\t");
        }
    }
    public boolean maoEhValida(){
        int gabao =0;
        for (Peca mao1 : mao) {
            if (mao1.ehGabao()) {
                gabao++;
            }
        }
        return gabao<4;
    }
}