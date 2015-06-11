/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domino.ia;

import java.util.ArrayList;
import java.util.Scanner;

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
}
