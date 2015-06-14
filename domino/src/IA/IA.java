package IA;

import Acao.Acao;
import Problema.Problema;
import Estado.Estado;

public abstract class IA {
    private Problema p;

    public IA(Problema p) {
        this.p = p;
    }

    public Problema getProblema() {
        return p;
    }
    
    public abstract Acao executa(Estado e);
    
}
