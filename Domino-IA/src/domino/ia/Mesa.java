package domino.ia;

import java.util.ArrayList;

public class Mesa {
    private ArrayList<Peca> mesa;
    
    public Mesa(){
        mesa = new ArrayList<>();
    }
    
    public Peca getPontaEsquerda(){
        return mesa.get(0);
    }
    
    public Peca getPontaDireita(){
        return mesa.get(mesa.size()-1);
    }
    
    public boolean inserirEsquerda(Peca p){
        Peca ponta = mesa.get(0);
        if(p.getDireita() == ponta.getEsquerda()){
            mesa.add(0, p);
            return true;
        } else if(p.getEsquerda() == ponta.getEsquerda()) {
            p.girarPeca();
            mesa.add(0, p);
            return true;
        }
        return false;
    }
    
    public boolean inserirDireita(Peca p){
        Peca ponta = mesa.get(mesa.size()-1);
        if(p.getDireita() == ponta.getDireita()){
            p.girarPeca();
            mesa.add(mesa.size()-1, p);
            return true;
        } else if(p.getEsquerda() == ponta.getDireita()) {
            mesa.add(mesa.size()-1, p);
            return true;
        }
        return false;
    }
    
    public void verMesa(){
        System.out.println(mesa);
    }
    
    public void jogadaInicial(Peca p){
        mesa.add(p);
    }
    
    public Mesa copia(){
        Mesa nova = new Mesa();
        for (int i = 0; i < mesa.size(); i++) {
            nova.mesa.add(mesa.get(i));
        }
        return nova;
    }
    
}
