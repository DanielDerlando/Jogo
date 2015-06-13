/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Problema;

import Acao.Acao;
import Estado.Estado;
import IA.IA;
import java.util.ArrayList;

/**
 *
 * @author 212031119
 */
public class Computador extends Jogador{

    private IA ia;
    
    public Computador(IA ia) {
        this.ia = ia;
        this.mao = new ArrayList<>();
    }
    
    public Computador copia(){
        Computador retorno = new Computador(this.ia);
        retorno.mao.addAll(this.mao);
        return retorno;
    }
    

    public IA getIA(){
        return ia;
    }

    @Override
    public Estado executa(Estado estadoReal) {
        Estado retorno;
        System.out.println("Aguarde um instante. Estou pensando!");
        Acao acao = ia.executa(estadoReal);
        retorno = ia.getProblema().resultado(estadoReal, acao);
        System.out.println("O Computador tem " + (estadoReal.getIa().mao.size() - 1) + " de peças na mão!");
        return retorno;
    }
}
