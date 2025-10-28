package br.com.BatalhaTatica.controller;

import br.com.BatalhaTatica.model.Casas;
import br.com.BatalhaTatica.model.Personagem;
import br.com.BatalhaTatica.service.Jogo;
import br.com.BatalhaTatica.view.JogoVisualizer;

public class Controller {

    private JogoVisualizer view;
    private Jogo jogo;

    public Controller(JogoVisualizer view, Jogo jogo) {
        this.view = view;
        this.jogo = jogo;
    }

    public void iniciarJogo(int id) {
        String nome = view.enviarNome();
        Casas casa = view.enviaCasa();

        Personagem player1 = jogo.criarPersonagem(id, nome, casa);
        // decidir como fazer o id.

    }

    public void criarTime(int time){
        view.mensagemCriarTime(time);
        if(time == 2) {time = 4;}
        for(int i=time; i<time+3; i++){
            iniciarJogo(i);
        }
    }
}
