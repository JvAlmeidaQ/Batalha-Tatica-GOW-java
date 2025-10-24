package br.com.BatalhaTatica;

import br.com.BatalhaTatica.model.*;
import br.com.BatalhaTatica.view.JogoVisualizer;


public class App {
    public static void main(String[] args) {
        Tabuleiro tabuleiro = new Tabuleiro();
        JogoVisualizer jogoVisualizer = new JogoVisualizer(tabuleiro);

        Personagem stark1 = new Stark(101, "Tony");
        Personagem lanni = new Lannister(2, "Lol");
        Personagem targ = new Targaryen(3, "Targ");

        Posicao posStark = new Posicao(0, 4);
        Posicao posLannister = new Posicao(9, 4);
        Posicao posTargaryen = new Posicao(5, 5);

        tabuleiro.adicionarPersonagem(stark1, posStark);
        tabuleiro.adicionarPersonagem(lanni, posLannister);
        tabuleiro.adicionarPersonagem(targ, posTargaryen);

        String tabuleiroFormatado = jogoVisualizer.imprimeTabuleiro();
        System.out.println(tabuleiroFormatado);
    }
}
