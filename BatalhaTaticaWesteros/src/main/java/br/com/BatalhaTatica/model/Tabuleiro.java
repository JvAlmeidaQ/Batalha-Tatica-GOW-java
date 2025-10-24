package br.com.BatalhaTatica.model;

public class Tabuleiro {

    private final int TAM = 10;
    private final Personagem[][] tabuleiro = new Personagem[TAM][TAM];

    public Personagem getPosicaoPersonagem(Posicao p) {
        return this.tabuleiro[p.getLinha()][p.getColuna()];
    }

    public boolean posicaoIsOcupada(Posicao p) {
        return getPosicaoPersonagem(p) != null;
    }

    public void adicionarPersonagem(Personagem personagem, Posicao p) {
        this.tabuleiro[p.getLinha()][p.getColuna()] = personagem;
    }

    public void novaPosicao(Personagem personagem, Posicao novaPosicao) {
        this.limparPosicao(personagem.getPosicao());
        personagem.setPosicao(novaPosicao);
        this.adicionarPersonagem(personagem, novaPosicao);
    }

    public void limparPosicao(Posicao p) {
        this.tabuleiro[p.getLinha()][p.getColuna()] = null;
    }

    public boolean posNosLimites(Posicao p) {
        if (p.getLinha() >= TAM && p.getColuna() >= TAM)
            return false;
        return p.getLinha() >= 0 && p.getColuna() >= 0;
    }

    public boolean verificarPosicao(Posicao p) {
        if (!this.posNosLimites(p) && this.posicaoIsOcupada(p))
            return false;
        return true;
    }
}
