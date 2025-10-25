package br.com.BatalhaTatica;

import br.com.BatalhaTatica.model.*;
import br.com.BatalhaTatica.service.Jogo;
import br.com.BatalhaTatica.service.Movimentacao;
import br.com.BatalhaTatica.view.JogoVisualizer;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Tabuleiro tabuleiro = new Tabuleiro();
        JogoVisualizer jogoVisualizer = new JogoVisualizer(tabuleiro);
        Jogo jogo = new Jogo();

        Personagem player1 = jogo.criarPersonagem(sc, 1);
        Personagem stark1 = new Stark(101, "Tony");
        Personagem lanni = new Lannister(2, "Lol");
        Personagem targ = new Targaryen(3, "Targ");

        Movimentacao movimentacao1 = new Movimentacao(tabuleiro);

        movimentacao1.setPosicaoInicial(stark1);
        movimentacao1.setPosicaoInicial(lanni);
        movimentacao1.setPosicaoInicial(targ);
        movimentacao1.setPosicaoInicial(player1);

        System.out.println(JogoVisualizer.gerarTabelaCasas());
        String tabuleiroFormatado = jogoVisualizer.imprimeTabuleiro();
        System.out.println(tabuleiroFormatado);

    }
}

