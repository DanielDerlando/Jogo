package Problema;

import Estado.Estado;
import java.util.ArrayList;

public abstract class Jogador {

    public ArrayList<Peca> mao;
    String nome;

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
        System.out.println("");
    }
    public boolean maoEhValida(){
        int gabao =0;
        for (Peca mao1 : mao) {
            if (mao1.ehGabao()) {
                gabao++;
            }
        }
        return gabao<7;
    }
    public boolean jogaPeca(Peca p){
        return mao.remove(p);
    }
    public abstract Estado executa(Estado estadoReal);

    public String getNome() {
        return nome;
    }
    
}
