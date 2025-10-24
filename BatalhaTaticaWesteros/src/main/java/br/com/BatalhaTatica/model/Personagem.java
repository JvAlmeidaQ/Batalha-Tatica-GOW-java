package br.com.BatalhaTatica.model;

public abstract class Personagem {
    private Integer id;
    private String nome;
    private Casas casa;
    private final Integer vidaMax;
    private Integer vidaAtual;
    private Integer atkBase;
    private Integer defesaBase;
    private Posicao posicao;

    public Personagem(Integer id, String nome, Casas casa, Integer vidaMax, Integer atkBase, Integer defesaBase, Posicao posicao) {
        this.id = id;
        this.nome = nome;
        this.casa = casa;
        this.vidaMax = vidaMax;
        this.vidaAtual = vidaMax;
        this.atkBase = atkBase;
        this.defesaBase = defesaBase;
        this.posicao = null;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Casas getCasa() {
        return casa;
    }

    public Integer getVidaMax() {
        return vidaMax;
    }

    public Integer getVidaAtual() {
        return vidaAtual;
    }

    public Integer getAtkBase() {
        return atkBase;
    }

    public Integer getDefesaBase() {
        return defesaBase;
    }

    public Posicao getPosicao() {
        return posicao;
    }

    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }

    public int getLinha() {
        return this.posicao.getLinha();
    }

    public void setLinha(int linha) {
        this.posicao.setLinha(linha);
    }

    public int getColuna() {
        return this.posicao.getColuna();
    }

    public void setColuna(int coluna) {
        this.posicao.setColuna(coluna);
    }

    public abstract Integer modOfensivo(Personagem alvo);

    public abstract Integer modOfdef(Integer dano);

    public abstract Integer alcanceMax();


}
