package br.com.BatalhaTatica.model;

public class Lannister extends Personagem {

    public Lannister(Integer id, String nome) {
        super(id, nome, Casas.LANNISTER, 50, 20, 10, null);
    }

    @Override
    public Integer modOfensivo(Personagem alvo) {
        int modificador = (int) (this.getAtkBase() * 1.15);
        int dano = modificador - alvo.getDefesaBase();
        return dano;
    }


    @Override
    public Integer modOfdef(Integer dano) {
        return dano;
    }

    @Override
    public Integer alcanceMax() {
        return 2;
    }
}
