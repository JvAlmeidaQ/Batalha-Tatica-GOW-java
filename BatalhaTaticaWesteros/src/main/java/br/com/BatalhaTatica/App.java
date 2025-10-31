package br.com.BatalhaTatica;

import br.com.BatalhaTatica.controller.Controller;
import br.com.BatalhaTatica.model.Tabuleiro;
import br.com.BatalhaTatica.service.*;
import br.com.BatalhaTatica.view.JogoVisualizer;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Tabuleiro tabuleiro = new Tabuleiro();
        JogoVisualizer jogoVisualizer = new JogoVisualizer(tabuleiro);
        Combate combate = new Combate(tabuleiro, jogoVisualizer);
        Movimentacao movimentacao = new Movimentacao(tabuleiro);
        Bots bot = new Bots(combate);
        Replay replay = new Replay(jogoVisualizer);
        Jogo jogo = new Jogo(combate, movimentacao, bot, tabuleiro, replay);
        Controller controller = new Controller(jogoVisualizer, jogo, bot);

        controller.iniciarJogo();
    }
}