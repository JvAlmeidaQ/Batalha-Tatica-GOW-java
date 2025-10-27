package br.com.BatalhaTatica.service;

import br.com.BatalhaTatica.model.Casas;

import java.util.Random;

public class Bots {

    private Jogo jogo;
    private Random random = new Random();

    public Bots(Jogo jogo) {
        this.jogo = jogo;
    }

    public int geraNumero() {
        int min = 1;
        int max = 3;
        int numAleatorio = min + random.nextInt(max - min + 1);
        return numAleatorio;
    }

    public Casas geraCasa(int numeroGerado) {
        switch (numeroGerado) {
            case 1:
                return Casas.STARK;
            case 2:
                return Casas.TARGARYEN;
            case 3:
                return Casas.LANNISTER;
            default:
                return null;
        }
    }

    public String geraNomeCompleto(String numBotGerado) {
        return "Bot" + numBotGerado;
    }

    public void criarBots() {
        int qtdStark = 0;
        int qtdLannister = 0;
        int qtdTargaryen = 0;

        for (int i = 1; i <= 3; i++) {
            int numeroGerado = this.geraNumero();
            Casas casaGerada = this.geraCasa(numeroGerado);

            String numBot;
            if (casaGerada == Casas.LANNISTER)
                numBot = "Lannister" + String.valueOf(++qtdLannister);
            else if (casaGerada == Casas.STARK)
                numBot = "Stark" + String.valueOf(++qtdStark);
            else
                numBot = "Targaryen" + String.valueOf(++qtdTargaryen);

            String nome = this.geraNomeCompleto(numBot);

            this.jogo.criarPersonagem(i, nome, casaGerada);
        }
    }


}
