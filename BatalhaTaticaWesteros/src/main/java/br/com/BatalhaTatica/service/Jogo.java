package br.com.BatalhaTatica.service;

import br.com.BatalhaTatica.model.*;

public class Jogo {


    public Personagem criarPersonagem(int id, String nome, Casas casa) {
        if (casa == Casas.STARK)
            return new Stark(id, nome);
        else if (casa == Casas.LANNISTER)
            return new Lannister(id, nome);
        else
            return new Targaryen(id, nome);
    }


}