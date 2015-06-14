/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Problema;

import Acao.Acao;
import Estado.Estado;
import IA.IA;
import Util.Label;
import java.util.ArrayList;

/**
 *
 * @author 212031119
 */
public class Computador extends Jogador{

    private IA ia;
    
    public Computador(IA ia,String nome) {
        this.ia = ia;
        this.mao = new ArrayList<>();
        this.nome=nome;
    }
    
    public Computador copia(){
        Computador retorno = new Computador(this.ia,this.nome);
        retorno.mao.addAll(this.mao);
        return retorno;
    }
    

    public IA getIA(){
        return ia;
    }

    @Override
    public Estado executa(Estado estadoInicial) {
        Estado retorno;
        System.out.println("Aguarde um instante. Estou pensando!");
        Acao acao = ia.executa(estadoInicial);
        retorno = ia.getProblema().resultado(estadoInicial, acao);
        if(estadoInicial.getMesa().getLista().size()<retorno.getMesa().getLista().size()){
            if(!estadoInicial.getMesa().getPontaDireita().equals(retorno.getMesa().getPontaDireita())){
                mao.remove(retorno.getMesa().getPontaDireita());
            }else{
                mao.remove(retorno.getMesa().getPontaEsquerda());
            }
        }
        System.out.println("O "+nome+" tem " + mao.size() + " de peças na mão!");
        //}else{
        //    System.out.println("O "+nome+" tem " + (estadoReal.getPecasInimigo().size()) + " de peças na mão!");
        //}
        return retorno;
    }
}
