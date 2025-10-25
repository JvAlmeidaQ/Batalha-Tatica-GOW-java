package br.com.BatalhaTatica.util;

import br.com.BatalhaTatica.model.Personagem;

public class DistanciaChebyshev {

    private Personagem atacante;
    private Personagem oponente;

    public DistanciaChebyshev(Personagem atacante, Personagem oponente) {
        this.atacante = atacante;
        this.oponente = oponente;
    }

    public int calculaDistancia() {
        int distancia;
        distancia = Math.max(Math.abs(this.atacante.getLinha() - this.oponente.getLinha()), Math.abs(this.oponente.getColuna() - this.atacante.getColuna()));
        return distancia;
    }
}
