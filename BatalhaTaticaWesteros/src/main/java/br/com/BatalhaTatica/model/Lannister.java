package br.com.BatalhaTatica.model;

public class Lannister extends Personagem {

    public Lannister(Integer id, String nome) {
        super(id, nome, Casas.LANNISTER, 50, 20, 10, null);
    }

    @Override
    public int modOfensivo(Personagem defensor) {
        int atk = (int) (this.getAtkBase() * 1.15);
        int dano = atk - defensor.getDefesaBase();
        return Math.max(0, dano);
    }


    @Override
    public Integer modOfdef(Integer dano) {
        return dano;
    }

    @Override
    public int alcanceMax() {
        return 2;
    }

    @Override
    public String toString() {
        return String.format(
                "🧙 %-12s | 🦁 %-10s | ❤️ %3d/%-3d | ⚔️ %-3d | 🛡️ %-3d | 📍 %s",
                getNome(), getCasa(), getVidaAtual(), getVidaMax(), getAtkBase(), getDefesaBase(), getPosicao()
        );
    }
}
