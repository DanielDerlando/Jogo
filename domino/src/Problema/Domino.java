package Problema;

import Acao.Acao;
import Acao.AcaoJoga;
import Acao.AcaoPassa;
import Estado.Estado;
import IA.MiniMaxCutOff;
import Util.Label;
import java.util.ArrayList;

public class Domino extends Problema {//Gerencia o jogo


    public Domino() {
    }

    /**
     * Retorna a MAX_VALUE, caso NO_MAX venceu, MIN_VALUE, caso NO_MIN venceu e
     * em caso de empate retorna um valor coerente.
     *
     * @param e
     * @param jogador
     * @return
     */
    public int utilidade(Estado e) {
        if (e.getJogadorIa().mao.isEmpty()) { //se teve um vencedor
            return Integer.MAX_VALUE;
        } else if (e.getPecasInimigo().isEmpty()) {
            return Integer.MIN_VALUE;
        } else {//se teve um empate
            int soma = 0;
            for (Peca p : e.getPecasInimigo()) { //MIN
                soma = soma + p.getDireita() + p.getEsquerda();
            }
            for (Peca p : e.getJogadorIa().mao) { //MAX
                soma = soma - (p.getDireita() + p.getEsquerda());
            }
            return soma;
        }
    }

    public ArrayList<Acao> acoes(Estado e) { //quais as ações validas dado um estado, retorna as ações que vão gerar os proximos estados
        ArrayList<Acao> acoesPossiveis;
        Peca direita = e.getMesa().getPontaDireita();
        Peca esquerda = e.getMesa().getPontaEsquerda();
        acoesPossiveis = new ArrayList<>();
        if (e.getJogadorDaVez() == Label.JOGADOR_MAX) { //CASO MAX
            for (Peca pecaAtual : e.getJogadorIa().mao) {
                if ((pecaAtual.getDireita() == direita.getDireita()) || (pecaAtual.getEsquerda() == direita.getDireita())) {
                    acoesPossiveis.add(new AcaoJoga(pecaAtual, Label.PONTA_DIREITA));
                }
                if ((pecaAtual.getDireita() == esquerda.getEsquerda()) || (pecaAtual.getEsquerda() == esquerda.getEsquerda())) {
                    acoesPossiveis.add(new AcaoJoga(pecaAtual, Label.PONTA_ESQUERDA));
                }
            }
            if (acoesPossiveis.isEmpty()) {
                acoesPossiveis.add(new AcaoPassa());
            }
        } else { //CASO MIN
            for (Peca pecaAtual : e.getPecasInimigo()) {
                if ((pecaAtual.getDireita() == direita.getDireita()) || (pecaAtual.getEsquerda() == direita.getDireita())) {
                    acoesPossiveis.add(new AcaoJoga(pecaAtual, Label.PONTA_DIREITA));
                }
                if ((pecaAtual.getDireita() == esquerda.getEsquerda()) || (pecaAtual.getEsquerda() == esquerda.getEsquerda())) {
                    acoesPossiveis.add(new AcaoJoga(pecaAtual, Label.PONTA_ESQUERDA));
                }
            }
            if (acoesPossiveis.isEmpty()) {
                acoesPossiveis.add(new AcaoPassa());
            }
        }
        return acoesPossiveis;
    }

    public Estado resultado(Estado e, Acao a) {
        return a.executa(e);
    }

    /**
     * Se as jogadas disponíveis, dado um estado atual, tanto para MIN, quanto
     * para MAX, são passar a jogada. Ou se uma das mãos dos jogadores acabou
     *
     * @param e
     * @return
     */
    public boolean testeDeTermino(Estado e) {
        //se um dos dois jogadores venceu
        if (e.getJogadorIa().mao.isEmpty() || e.getPecasInimigo().isEmpty()) {
            return true;
        }
        //Caso não tenha acabado (as mãos contem peças que podem ser jogadas)
        int pontaDireita = e.getMesa().getPontaDireita().getDireita();
        int pontaEsquerda = e.getMesa().getPontaEsquerda().getEsquerda();

        for (Peca pecaMax : e.getJogadorIa().mao) {
            if (pecaMax.getDireita() == pontaDireita || pecaMax.getEsquerda() == pontaDireita
                    || pecaMax.getDireita() == pontaEsquerda || pecaMax.getEsquerda() == pontaEsquerda) {
                return false;
            }
        }
        for (Peca pecaMin : e.getPecasInimigo()) {
            if (pecaMin.getDireita() == pontaDireita || pecaMin.getEsquerda() == pontaDireita
                    || pecaMin.getDireita() == pontaEsquerda || pecaMin.getEsquerda() == pontaEsquerda) {
                return false;
            }
        }
        //caso mão nao esta vazia e ainda tenho peças pra jogar 
        return true;
    }

    /**
     * Retorna a avaliação de um estado Qtd de peças é importante
     *
     * @param e
     * @return
     */
    public int eval(Estado e) {
        int soma = 0;
        soma = soma + 2*qtdPecasJogaveisDoInimigo(e);
        soma = soma + 1*descartaGabao(e);
        soma = soma + 2*qtdGabao(e);
        soma = soma + 1*qtdPecasDosJogadores(e);
        soma = soma + 2*qtdPontasJogaveis(e);
        soma = soma + 2*verificaPontuacao(e);
        return soma;
    }

    /**
     * Testa se o estado esta na profundidade definida
     *
     * @param e
     * @param profundidade
     * @return
     */
    public boolean cut_off(Estado e, int profundidade) {
        profundidade= MiniMaxCutOff.nivel+ profundidade;
        if(testeDeTermino(e)){
            profundidade =e.getMesa().getLista().size();
        }
        return e.getMesa().getLista().size() == profundidade;
    }


    /**
     * Verifica a pontuação do estado
     *
     * @return Retorna 0 se as maos estão com pontos iguais, retorna um numero
     * negativo se a mao do max esta com mais pontos, retorna um numero positivo
     * Se a mao do min esta com mais pontos.
     */
    public int verificaPontuacao(Estado e) {
        int soma = 0;
        for (Peca p : e.getPecasInimigo()) { //MIN
            soma = soma + p.getValor();
        }
        for (Peca p : e.getJogadorIa().mao) { //MAX
            soma = soma - (p.getValor());
        }
        return soma;
    }

    /**
     * Verifica as qtd de peça dos 2 jogadores
     *
     * @param e
     * @return positivo de a mao do inimigo tem mais peças, caso contrario
     * retorna negativo
     */
    public int qtdPecasDosJogadores(Estado e) {
        return e.getPecasInimigo().size() - e.getJogadorIa().mao.size();
    }

    public int descartaGabao(Estado e) {
        int retorno=0;
        Peca esq = e.getMesa().getPontaEsquerda();
        Peca dir = e.getMesa().getPontaDireita();
        for (Peca peca : e.getJogadorIa().mao) {
            if(peca.ehGabao()){
                if(esq.getEsquerda()==peca.getDireita())
                    retorno++;
                if(dir.getDireita()==peca.getDireita())
                    retorno++;
            }
        }
        return retorno;
    }
    public int qtdGabao(Estado e){
        int retorno=0;
        for (Peca peca : e.getJogadorIa().mao) {
            if(peca.ehGabao()){
                retorno--;
            }
        }
        return retorno;
    }

    public int qtdPecasJogaveisDoInimigo(Estado e) {
        Peca direita = e.getMesa().getPontaDireita();
        Peca esquerda = e.getMesa().getPontaEsquerda();
        int retorno = 0;
        if (e.getJogadorDaVez() == Label.JOGADOR_MAX) {//min jogou
            for (Peca p : e.getJogadorIa().mao) {
                if ((direita.getDireita() == p.getDireita() || direita.getDireita() == p.getEsquerda()) || (esquerda.getEsquerda() == p.getDireita() || esquerda.getEsquerda() == p.getEsquerda())) {
                    retorno = retorno + 1;
                }
            }
        } else {//max jogou
            for (Peca p : e.getPecasInimigo()) {
                if ((direita.getDireita() == p.getDireita() || direita.getDireita() == p.getEsquerda()) || (esquerda.getEsquerda() == p.getDireita() || esquerda.getEsquerda() == p.getEsquerda())) {
                    retorno = retorno + 1;
                }
            }
        }

        return retorno;
    }

    public int qtdPontasJogaveis(Estado e) {
        Peca direita = e.getMesa().getPontaDireita();
        Peca esquerda = e.getMesa().getPontaEsquerda();
        int esq = 0;
        int dir=0;
        int nenhum =0;
        if (e.getJogadorDaVez() == Label.JOGADOR_MAX) {//min jogou
            for (Peca p : e.getPecasInimigo()) {
                if(direita.getDireita() == p.getDireita() || direita.getDireita() == p.getEsquerda()){
                    dir++;
                }
                if(esquerda.getEsquerda() == p.getDireita() || esquerda.getEsquerda() == p.getEsquerda()){
                    esq++;
                }
                if(!((direita.getDireita() == p.getDireita() || direita.getDireita() == p.getEsquerda()) || (esquerda.getEsquerda() == p.getDireita() || esquerda.getEsquerda() == p.getEsquerda()))){
                    nenhum++;
                }
            }
            return esq+dir-nenhum;
        } else {//max jogou
            for (Peca p : e.getJogadorIa().mao) {//max jogou
             if(direita.getDireita() == p.getDireita() || direita.getDireita() == p.getEsquerda()){
                    dir++;
                }
                if(esquerda.getEsquerda() == p.getDireita() || esquerda.getEsquerda() == p.getEsquerda()){
                    esq++;
                }
                if(!((direita.getDireita() == p.getDireita() || direita.getDireita() == p.getEsquerda()) || (esquerda.getEsquerda() == p.getDireita() || esquerda.getEsquerda() == p.getEsquerda()))){
                    nenhum++;
                }
            }
            return esq+dir-nenhum;
        }
    }

}
