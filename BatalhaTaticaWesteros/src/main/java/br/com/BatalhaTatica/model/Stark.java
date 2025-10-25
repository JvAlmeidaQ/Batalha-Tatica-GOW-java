package br.com.BatalhaTatica.model;

public class Stark extends Personagem {

    public Stark(Integer id, String nome) {
        super(id, nome, Casas.STARK, 60, 20, 10, null);
    }

    @Override
    public int modOfensivo() {
        return this.getAtkBase();
    }

    @Override
    public Integer modOfdef(Integer dano) {
        return (int) (dano * 0.80);
    }

    @Override
    public int alcanceMax() {
        return 1;
    }

}
