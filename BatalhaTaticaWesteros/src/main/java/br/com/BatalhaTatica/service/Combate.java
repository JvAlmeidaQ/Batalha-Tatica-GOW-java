package br.com.BatalhaTatica.service;

import br.com.BatalhaTatica.model.Personagem;
import br.com.BatalhaTatica.model.Tabuleiro;
import br.com.BatalhaTatica.util.DistanciaChebyshev;

public class Combate {

    private Tabuleiro tabuleiro;

    public Combate(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public boolean distanciaValida(Personagem atacante, Personagem defensor) {

        DistanciaChebyshev chebyshev = new DistanciaChebyshev(atacante, defensor);
        int distancia = chebyshev.calculaDistancia();
        if (distancia > 0 && atacante.alcanceMax() <= distancia) {
            return true;
        }
        return false;
    }

    public boolean estaVivo(Personagem defensor) {
        return defensor.getVidaAtual() > 0;
    }

    public void ataque(Personagem atacante, Personagem defensor) {
        if (distanciaValida(atacante, defensor) && estaVivo(defensor)) {
            int danoBruto = atacante.modOfensivo();
            int danoLiquid = defensor.modOfdef(danoBruto) - defensor.getDefesaBase();
            defensor.setVidaAtual(danoLiquid);
            if (!estaVivo(defensor)) {
                this.tabuleiro.limparPosicao(defensor.getPosicao());
                //chamar mensagem de morte na view
            }
        } //else
        // lançar excessão;
    }

}
