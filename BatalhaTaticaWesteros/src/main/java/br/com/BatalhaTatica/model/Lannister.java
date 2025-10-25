package br.com.BatalhaTatica.model;

public class Lannister extends Personagem {

    public Lannister(Integer id, String nome) {
        super(id, nome, Casas.LANNISTER, 50, 20, 10, null);
    }

    @Override
    public int modOfensivo() {
        return (int) (this.getAtkBase() * 1.15);
    }


    @Override
    public Integer modOfdef(Integer dano) {
        return dano;
    }

    @Override
    public int alcanceMax() {
        return 2;
    }
}
