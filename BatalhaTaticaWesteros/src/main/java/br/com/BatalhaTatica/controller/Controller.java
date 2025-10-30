package br.com.BatalhaTatica.controller;

import br.com.BatalhaTatica.model.Casas;
import br.com.BatalhaTatica.model.Direcao;
import br.com.BatalhaTatica.model.Personagem;
import br.com.BatalhaTatica.service.Jogo;
import br.com.BatalhaTatica.view.JogoVisualizer;

import java.util.List;

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
            view.imprimeTabuleiro();
            view.imprimePersonagens(this.jogo.getTime1(), this.jogo.getTime2());
            if (jogo.turnoDeQuem().equals("Jogador")) {
                //logicaHumano
                Personagem personagemEscolhido = view.escolhaDoPersonagem(jogo.getTime());

                boolean movimentoFoiValido = false;
                while (!movimentoFoiValido) {
                    Direcao direcaoEscolhida = view.escolhaDoDirecao();
                    Boolean movimentoBemSucedido = jogo.movimentarPersonagem(personagemEscolhido, direcaoEscolhida);
                    if (movimentoBemSucedido) {
                        movimentoFoiValido = true;
                    } else
                        view.movimentacaoInvalida();
                }

                List<Personagem> possiveisAlvos = jogo.alvos(personagemEscolhido);
                if (!possiveisAlvos.isEmpty()) {
                    Personagem alvoEscolhido = view.escolherAlvo(possiveisAlvos);
                    view.mensagemAtaque(personagemEscolhido, alvoEscolhido);
                    jogo.atacar(personagemEscolhido, alvoEscolhido);
                }
            } else {
                break; //logica robo
            }

            jogo.setNumTurno();
        }
    }

    private void criarTime(int time) {
        view.mensagemCriarTime(time);
        for (int i = 0; i < 3; i++) {
            view.mensagemNumeroPersonagem(i);
            iniciarJogo();
        }
    }
}
