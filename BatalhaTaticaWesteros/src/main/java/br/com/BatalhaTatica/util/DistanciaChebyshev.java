package br.com.BatalhaTatica.util;

import br.com.BatalhaTatica.model.Personagem;

public class DistanciaChebyshev {

    public int calculaDistancia(Personagem atacante, Personagem oponente) {
        int distancia;
        distancia = Math.max(Math.abs(atacante.getLinha() - oponente.getLinha()), Math.abs(oponente.getColuna() - atacante.getColuna()));
        return distancia;
    }
}
