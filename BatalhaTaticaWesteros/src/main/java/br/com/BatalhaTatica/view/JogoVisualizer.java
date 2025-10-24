package br.com.BatalhaTatica.view;

import br.com.BatalhaTatica.model.Personagem;
import br.com.BatalhaTatica.model.Posicao;
import br.com.BatalhaTatica.model.Tabuleiro;

public class JogoVisualizer {

    private final Tabuleiro tabuleiro;

    public JogoVisualizer(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public String getRepresentacaoVisual(Personagem personagem) {
        if (personagem == null) {
            return " ";
        }
        return switch (personagem.getCasa()) {
            case STARK -> "🐺";
            case LANNISTER -> "🦁";
            case TARGARYEN -> "🐲";
            default -> "?";
        };
    }

    public String imprimeTabuleiro() {
        StringBuilder tab = new StringBuilder();
        tab.append("╔═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╗\n");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 10; j++) {
                tab.append("║");
                Posicao posicao = new Posicao(i, j);
                if (!this.tabuleiro.verificarPosicao(posicao))
                    tab.append("   ");
                else
                    tab.append(" " + this.getRepresentacaoVisual(this.tabuleiro.getPosicaoPersonagem(posicao)) + " ");
            }
            tab.append("║\n");
            tab.append("╞═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╡\n");
        }
        for (int j = 0, i = 9; j < 10; j++) {
            tab.append("║");
            Posicao posicao = new Posicao(i, j);
            if (!this.tabuleiro.verificarPosicao(posicao))
                tab.append("   ");
            else
                tab.append(" " + this.getRepresentacaoVisual(this.tabuleiro.getPosicaoPersonagem(posicao)) + " ");
        }
        tab.append("║\n");
        tab.append("╚═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╝\n");

        return tab.toString();
    }
}

