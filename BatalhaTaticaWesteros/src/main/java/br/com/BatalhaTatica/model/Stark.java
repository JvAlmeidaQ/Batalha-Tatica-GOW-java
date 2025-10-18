package br.com.BatalhaTatica.model;

public class Stark extends Personagem {

    public Stark(Integer id, String nome) {
        super(id, nome, Casas.STARK, 60, 20, 10, null);
    }

    @Override
    public Integer modOfensivo(Personagem alvo) {
        int dano = this.getAtkBase() - alvo.getDefesaBase();
        return dano;
    }

    @Override
    public Integer modOfdef(Integer dano) {
        return (int) (dano * 0.80);
    }

    @Override
    public Integer alcanceMax() {
        return 1;
    }

}
