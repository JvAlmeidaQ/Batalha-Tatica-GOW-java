package br.com.BatalhaTatica.controller;

import br.com.BatalhaTatica.model.Casas;
import br.com.BatalhaTatica.model.Direcao;
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

    public void preJogo() {
        int escolhaDoModo = view.modoDaPartida();

        this.jogo.recebeModoDaPartida(escolhaDoModo);

        if (escolhaDoModo == 1) {
            this.criarTime(1); //Cria os 2 times humanos
            this.criarTime(2);
        } else {
            this.criarTime(1); //cria time Humano
            jogo.criarBots(); // crria time dos bots
        }

        jogo.posicionarTimes();
        view.mensagemDeCriacao();
    }

    public void iniciarJogo() {
        String nome = view.enviarNome();
        Casas casa = view.enviaCasa();

        jogo.criarPersonagem(nome, casa);
    }

    public void executarTurnos() {
        while (!jogo.fimDeJogo()) {
            view.numTurno(jogo.getNumTurno());
            if (jogo.turnoDeQuem().equals("Jogador")) {
                //logicaHumano
                Personagem personagemEscolhido = view.escolhaDoPersonagem(jogo.getTime());
                Direcao direcaoEscolhida = view.escolhaDoDirecao();
                jogo.acaoPersonagem(personagemEscolhido, direcaoEscolhida);

            } else
                break;
            jogo.setNumTurno();
        }
    }

    private void criarTime(int time) {
        view.mensagemCriarTime(time);
        for (int i = 0; i < 3; i++) {
            iniciarJogo();
        }
    }
}
