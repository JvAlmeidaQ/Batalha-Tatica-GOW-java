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

    public void iniciarJogo() {
        String nome = view.enviarNome();
        Casas casa = view.enviaCasa();

        Personagem novoPersonagem = jogo.criarPersonagem(0, nome, casa);
        // decidir como fazer o id.

    }
}
