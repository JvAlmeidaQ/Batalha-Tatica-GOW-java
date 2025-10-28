package br.com.BatalhaTatica.service;

import br.com.BatalhaTatica.model.Personagem;
import br.com.BatalhaTatica.model.Tabuleiro;
import br.com.BatalhaTatica.util.DistanciaChebyshev;
import br.com.BatalhaTatica.view.JogoVisualizer;

public class Combate {

    private Tabuleiro tabuleiro;
    private JogoVisualizer view;

    public Combate(Tabuleiro tabuleiro, JogoVisualizer jogoVisualizer) {
        this.tabuleiro = tabuleiro;
        this.view = jogoVisualizer;
    }

    public boolean distanciaValida(Personagem atacante, Personagem defensor) {

        DistanciaChebyshev chebyshev = new DistanciaChebyshev();
        int distancia = chebyshev.calculaDistancia(atacante, defensor);
        if (atacante.alcanceMax() >= distancia) {
            return true;
        }
        return false;
    }

    public boolean estaVivo(Personagem defensor) {
        return defensor.getVidaAtual() > 0;
    }

    public void ataque(Personagem atacante, Personagem defensor) {
        if (distanciaValida(atacante, defensor) && estaVivo(defensor)) {

            int danoBruto = atacante.modOfensivo(defensor);

            int danoLiquid = defensor.modOfdef(danoBruto);

            defensor.receberDano(danoLiquid);

            if (!estaVivo(defensor)) {
                System.out.println(view.mensagemMorte(atacante, defensor));
                this.tabuleiro.limparPosicao(defensor.getPosicao());
            }
        } //else
        // lançar excessão;
    }

}
