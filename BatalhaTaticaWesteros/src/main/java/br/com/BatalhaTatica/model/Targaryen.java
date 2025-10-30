package br.com.BatalhaTatica.model;

public class Targaryen extends Personagem {
    public Targaryen(Integer id, String nome) {
        super(id, nome, Casas.TARGARYEN, 45, 2000, 10, null);
    }

    @Override
    public int modOfensivo(Personagem defensor) {
        return this.getAtkBase();
    }

    @Override
    public Integer modOfdef(Integer dano) {
        return dano;
    }

    @Override
    public int alcanceMax() {
        return 30;
    }

    @Override
    public String toString() {
        return String.format(
                "🧙 %-12s | 🐲 %-10s | ❤️ %3d/%-3d | ⚔️ %-3d | 🛡️ %-3d | 📍 %s",
                getNome(), getCasa(), getVidaAtual(), getVidaMax(), getAtkBase(), getDefesaBase(), getPosicao()
        );
    }
}
