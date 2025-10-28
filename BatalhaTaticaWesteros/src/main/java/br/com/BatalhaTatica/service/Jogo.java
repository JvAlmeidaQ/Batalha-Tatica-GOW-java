package br.com.BatalhaTatica.service;

import br.com.BatalhaTatica.model.*;

import java.util.ArrayList;
import java.util.List;


public class Jogo {

    private List<Personagem> time1 = new ArrayList<>();
    private List<Personagem> time2 = new ArrayList<>();

    public Personagem criarPersonagem(int id, String nome, Casas casa) {
        Personagem personagem;
        if (casa == Casas.STARK)
            personagem = new Stark(id, nome);
        else if (casa == Casas.LANNISTER)
            personagem = new Lannister(id, nome);
        else
            personagem = new Targaryen(id, nome);
        if(id <= 3)
            time1.add(personagem);
        else
            time2.add(personagem);
        return personagem;
    }

    public void getTime(){

    }




}