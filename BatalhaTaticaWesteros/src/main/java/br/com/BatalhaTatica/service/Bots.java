package br.com.BatalhaTatica.service;

import br.com.BatalhaTatica.model.Casas;
import br.com.BatalhaTatica.model.Direcao;
import br.com.BatalhaTatica.model.Personagem;
import br.com.BatalhaTatica.util.DistanciaChebyshev;
import br.com.BatalhaTatica.service.Combate;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class Bots {

    private Jogo jogo;
    private Combate combate;
    private Random random = new Random();
    private DistanciaChebyshev dist;

    public Bots(Jogo jogo, Combate combate) {
        this.jogo = jogo;
        this.combate = combate;
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

        for (int i = 4; i <= 6; i++) {
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

    public int gerarNumeroDeZeroAN_1(int n){
        return random.nextInt(n);
    }

    public int possivbeisAtacantes(List<Personagem> time1, List<Personagem> time2){
        DistanciaChebyshev chebyshev = new DistanciaChebyshev();
        int personagensAptos = 0;
        int menorDist;
        int distancia;
        for(Personagem pt2 : time2){
            menorDist = 100;
            for(Personagem pt1 : time1){
                distancia = chebyshev.calculaDistancia(pt2, pt1);
                if(menorDist < distancia)
                    menorDist = distancia;
            }
            if(pt2.alcanceMax() >= menorDist )
                personagensAptos += pt2.getId();
        }
        return personagensAptos;
    }

    public int possivbeisAlvos(List<Personagem> time1, Personagem atacante){
        int personagensAptos = 0;
        for(Personagem pt1 : time1){
            if(combate.distanciaValida(atacante, pt1))
                personagensAptos += pt1.getId()+1;
        }
        return personagensAptos;
    }

    public Personagem randomplayerAtacante(List<Personagem> time1, List<Personagem> time2) {
        int atacantesAptos = possivbeisAtacantes(time1, time2);
        switch (atacantesAptos) {
            case 0, 15 -> { //Aleatório 3/3
                return time2.get(gerarNumeroDeZeroAN_1(time2.size()));
            }
            case 4, 5, 6 -> { //Expecífico 1/3
                for (Personagem p : time2) {
                    if (p.getId() == atacantesAptos)
                        return p;
                }
            }
            case 9 -> { //Aleatório 2/3
                if (gerarNumeroDeZeroAN_1(2) == 0)
                    return time2.getFirst();
                else
                    for (Personagem p : time2) {
                        if (p.getId() == 5)
                            return p;
                    }
            }
            case 10 -> { //Aleatório 2/3
                if (gerarNumeroDeZeroAN_1(2) == 0)
                    return time2.getFirst();
                else {
                    for (Personagem p : time2) {
                        if (p.getId() == 6)
                            return p;
                    }
                }
            }
            case 11 -> { //Aleatório 2/3
                if (gerarNumeroDeZeroAN_1(2) == 0) {
                    for (Personagem p : time2) {
                        if (p.getId() == 5)
                            return p;
                    }
                } else {
                    for (Personagem p : time2) {
                        if (p.getId() == 6)
                            return p;
                    }
                }
            }
            default -> {
                return time2.getFirst();
            }
        }
        return null;
    }

    public Personagem randomPlayerAlvo(List<Personagem> time1, Personagem atacante){
        int alvosAptos = possivbeisAlvos(time1, atacante);
        switch (alvosAptos){
            case 0 -> {
                DistanciaChebyshev chebyshev = new DistanciaChebyshev();
                int distancia;
                int menorDist = 100;
                Personagem alvo = time1.getFirst();
                for(Personagem pt1 : time1){
                    distancia = chebyshev.calculaDistancia(atacante, pt1);
                    if(menorDist < distancia){
                        menorDist = distancia;
                        alvo = pt1;
                    }
                }
                return alvo;
            }
            case 9 -> {
                return time1.get(gerarNumeroDeZeroAN_1(time1.size()));
            }
            case 2, 3, 4 -> {
                for (Personagem p : time1) {
                    if (p.getId() == alvosAptos-1)
                        return p;
                }
            }
            case 5 -> { // ID 1 ou 2
                if (gerarNumeroDeZeroAN_1(2) == 0)
                    return time1.getFirst();
                else
                    for (Personagem p : time1) {
                        if (p.getId() == 2)
                            return p;
                    }
            }
            case 6 -> {// ID 1 ou 3
                if (gerarNumeroDeZeroAN_1(2) == 0)
                    return time1.getFirst();
                else
                    for (Personagem p : time1) {
                        if (p.getId() == 3)
                            return p;
                    }
            }
            case 7 -> {// ID 2 ou 3
                if (gerarNumeroDeZeroAN_1(2) == 0) {
                    for (Personagem p : time1) {
                        if (p.getId() == 2)
                            return p;
                    }
                } else {
                    for (Personagem p : time1) {
                        if (p.getId() == 3)
                            return p;
                    }
                }
            }
            default -> {
                return time1.getFirst();
            }
        }
        return null;
    }

    public Direcao defineDirecaoBOT(Personagem atacante, Personagem alvo){
        if(Math.abs(atacante.getPosicao().getLinha() - alvo.getPosicao().getLinha()) <= Math.abs(atacante.getPosicao().getColuna() - alvo.getPosicao().getColuna())){
            if (atacante.getPosicao().getColuna() >= alvo.getPosicao().getColuna())
                return Direcao.ESQUERDA;
            else
                return Direcao.DIREITA;
        }
        else{
            if (atacante.getPosicao().getLinha() >= alvo.getPosicao().getLinha())
                return Direcao.CIMA;
            else
                return Direcao.BAIXO;
        }
    }

    public Direcao direcaoAleatoria(){
        int val = gerarNumeroDeZeroAN_1(4);
        switch (val){
            case 0 -> {return Direcao.CIMA;}
            case 1 -> {return Direcao.BAIXO;}
            case 2 -> {return Direcao.ESQUERDA;}
            case 3 -> {return Direcao.DIREITA;}
            default -> {return Direcao.ESQUERDA;}
        }
    }
}
