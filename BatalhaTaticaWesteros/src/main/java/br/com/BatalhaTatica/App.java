package br.com.BatalhaTatica;

import br.com.BatalhaTatica.model.Tabuleiro;
import br.com.BatalhaTatica.service.Combate;
import br.com.BatalhaTatica.view.JogoVisualizer;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Tabuleiro tabuleiro = new Tabuleiro();
        JogoVisualizer jogoVisualizer = new JogoVisualizer(tabuleiro);
        Combate combate = new Combate(tabuleiro, jogoVisualizer);

    }
}

