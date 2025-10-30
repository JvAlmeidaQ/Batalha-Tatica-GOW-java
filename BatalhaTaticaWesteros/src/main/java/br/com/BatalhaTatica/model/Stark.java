package br.com.BatalhaTatica.model;

public class Stark extends Personagem {

    public Stark(Integer id, String nome) {
        super(id, nome, Casas.STARK, 60, 20, 10, null);
    }

    @Override
    public int modOfensivo(Personagem defensor) {
        int dano = this.getAtkBase() - defensor.getDefesaBase();
        return Math.max(0, dano);
    }

    @Override
    public Integer modOfdef(Integer dano) {
        return (int) (dano * 0.80);
    }

    @Override
    public int alcanceMax() {
        return 1;
    }

    @Override
    public String toString() {
        return String.format(
                "🧙 %-12s | 🐺 %-10s | ❤️ %3d/%-3d | ⚔️ %-3d | 🛡️ %-3d | 📍 %s",
                getNome(), getCasa(), getVidaAtual(), getVidaMax(), getAtkBase(), getDefesaBase(), getPosicao()
        );
    }
}
