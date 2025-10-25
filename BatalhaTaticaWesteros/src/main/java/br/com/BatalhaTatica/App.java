package br.com.BatalhaTatica;

import br.com.BatalhaTatica.model.*;
import br.com.BatalhaTatica.service.Movimentacao;
import br.com.BatalhaTatica.view.JogoVisualizer;


public class App {
    public static void main(String[] args) {
        Tabuleiro tabuleiro = new Tabuleiro();
        JogoVisualizer jogoVisualizer = new JogoVisualizer(tabuleiro);

        Personagem stark1 = new Stark(101, "Tony");
        Personagem lanni = new Lannister(2, "Lol");
        Personagem targ = new Targaryen(3, "Targ");

        Movimentacao movimentacao1 = new Movimentacao(tabuleiro);

        movimentacao1.setPosicaoInicial(stark1);
        movimentacao1.setPosicaoInicial(lanni);
        movimentacao1.setPosicaoInicial(targ);

        String tabuleiroFormatado = jogoVisualizer.imprimeTabuleiro();
        System.out.println(tabuleiroFormatado);
    }
}
