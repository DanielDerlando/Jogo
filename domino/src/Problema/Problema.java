/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Problema;

import Acao.Acao;
import Estado.Estado;
import java.util.ArrayList;

/**
 *
 * @author Daniel
 */
public abstract class Problema {
    private Estado estadoReal;

    public abstract int utilidade(Estado e);
    public abstract ArrayList<Acao> acoes(Estado e);
    public abstract Estado resultado(Estado e, Acao a);
    public abstract boolean testeDeTermino(Estado e);
    public abstract int eval(Estado e);
    public abstract boolean cut_off(Estado e, int profundidade);
    
    public Estado getEstadoReal() {
        return estadoReal;
    }

    public void setEstadoReal(Estado estadoReal) {
        this.estadoReal = estadoReal;
    }
}
