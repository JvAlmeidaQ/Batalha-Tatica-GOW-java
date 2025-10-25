package br.com.BatalhaTatica.model;

public class Targaryen extends Personagem {
    public Targaryen(Integer id, String nome) {
        super(id, nome, Casas.TARGARYEN, 45, 20, 10, null);
    }

    @Override
    public int modOfensivo() {
        return this.getAtkBase();
    }

    @Override
    public Integer modOfdef(Integer dano) {
        return dano;
    }

    @Override
    public int alcanceMax() {
        return 3;
    }
}
