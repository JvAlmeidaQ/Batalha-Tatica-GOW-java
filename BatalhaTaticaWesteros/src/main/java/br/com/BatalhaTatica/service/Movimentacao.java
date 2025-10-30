package br.com.BatalhaTatica.service;

import br.com.BatalhaTatica.model.Direcao;
import br.com.BatalhaTatica.model.Personagem;
import br.com.BatalhaTatica.model.Posicao;
import br.com.BatalhaTatica.model.Tabuleiro;

public class Movimentacao {

    private final Tabuleiro tabuleiro;

    public Movimentacao(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public int setRandomLinha() {
        return (int) (Math.random() * 10);
    }

    public int setRandomColuna() {
        return (int) (Math.random() * 10) / 7;
    }

    public void setPosicaoInicial(Personagem personagem) {
        int linha = setRandomLinha();
        int coluna = setRandomColuna();

        Posicao posicao;
        if (personagem.getId() <= 3)
            posicao = new Posicao(linha, coluna);
        else
            posicao = new Posicao(linha, 9 - coluna);

        if (!this.tabuleiro.posicaoIsOcupada(posicao)) {
            personagem.setPosicao(posicao);
            this.tabuleiro.adicionarPersonagem(personagem, posicao);
        } else
            setPosicaoInicial(personagem);
    }

    public boolean moverPersonagem(Personagem personagem, Direcao direcao) {
        Posicao novaPosicao;
        switch (direcao) {
            case W -> novaPosicao = new Posicao(personagem.getLinha() - 1, personagem.getColuna());
            case D -> novaPosicao = new Posicao(personagem.getLinha(), personagem.getColuna() + 1);
            case S -> novaPosicao = new Posicao(personagem.getLinha() + 1, personagem.getColuna());
            case A -> novaPosicao = new Posicao(personagem.getLinha(), personagem.getColuna() - 1);
            case PARADO -> novaPosicao = new Posicao(personagem.getLinha(), personagem.getColuna());
            default -> throw new IllegalStateException("Unexpected value: " + direcao);
        }
        if (!this.tabuleiro.verificarPosicao(novaPosicao))
            return false;
        this.tabuleiro.novaPosicao(personagem, novaPosicao);
        return true;
    }

}
