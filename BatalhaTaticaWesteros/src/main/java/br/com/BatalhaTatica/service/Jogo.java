package br.com.BatalhaTatica.service;

import br.com.BatalhaTatica.model.*;
import br.com.BatalhaTatica.util.Turno;
import br.com.BatalhaTatica.view.JogoVisualizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Jogo {

    private List<Personagem> time1 = new ArrayList<>();
    private List<Personagem> time2 = new ArrayList<>();

    private Combate combate;
    private Movimentacao movimentacao;
    private Bots bot;
    private Tabuleiro tabuleiro;
    private Replay replay;
    private Turno turno;

    private Random random = new Random();
    private int proximoId = 1;
    private int numTurno = 0;
    private int modoPartida;

    public Jogo(Combate combate, Movimentacao movimentacao, Bots bot, Tabuleiro tabuleiro, Replay replay) {
        this.combate = combate;
        this.movimentacao = movimentacao;
        this.bot = bot;
        this.tabuleiro = tabuleiro;
        this.replay = replay;
    }

    public Personagem criarPersonagem(String nome, Casas casa) {

        int id = this.proximoId;
        this.proximoId++;

        Personagem personagem;
        if (casa == Casas.STARK)
            personagem = new Stark(id, nome);
        else if (casa == Casas.LANNISTER)
            personagem = new Lannister(id, nome);
        else
            personagem = new Targaryen(id, nome);
        if (id <= 3)
            time1.add(personagem);
        else
            time2.add(personagem);

        return personagem;
    }

    public void criarBots() {
        int qtdStark = 0;
        int qtdLannister = 0;
        int qtdTargaryen = 0;

        for (int i = 0; i < 3; i++) {
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

            this.criarPersonagem(nome, casaGerada);
        }
    }

    private int geraNumero() {
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

    public void posicionarTimes() {
        for (Personagem personagem : this.time1)
            movimentacao.setPosicaoInicial(personagem);
        for (Personagem personagem : this.time2)
            movimentacao.setPosicaoInicial(personagem);
    }

    public void recebeModoDaPartida(int modo) {
        this.modoPartida = modo;
    }

    public int getNumTurno() {
        return this.numTurno;
    }

    public void setNumTurno() {
        this.numTurno++;
    }

    public String turnoDeQuem() {
        if (this.numTurno % 2 == 0)
            return "Jogador";
        else if (this.modoPartida == 2)
            return "Robo";
        else
            return "Jogador";
    }

    public void gravaTurno(String printTabuleiro, String printTimes){
        Turno turno = new Turno(printTabuleiro, printTimes);
        replay.setTurno(turno);
    }

    public List<Personagem> getTime() {
        if (this.numTurno % 2 == 0)
            return this.time1;
        else
            return this.time2;
    }


    public List<Personagem> getTime1() {
        return this.time1;
    }

    public List<Personagem> getTime2() {
        return this.time2;
    }

    public boolean movimentarPersonagem(Personagem personagemEscolhido, Direcao direcaoEscolhida) {
        return movimentacao.moverPersonagem(personagemEscolhido, direcaoEscolhida);
    }

    public List<Personagem> alvos(Personagem atacante) {
        List<Personagem> alvosValidos = new ArrayList<>();
        if (this.numTurno % 2 == 0) {
            for (Personagem inimigo : this.time2) {
                if (this.combate.estaVivo(inimigo) && this.combate.distanciaValida(atacante, inimigo)) {
                    alvosValidos.add(inimigo);
                }
            }
        } else {
            for (Personagem inimigo : this.time1) {
                if (this.combate.estaVivo(inimigo) && this.combate.distanciaValida(atacante, inimigo)) {
                    alvosValidos.add(inimigo);
                }
            }
        }
        return alvosValidos;
    }

    public Personagem alvoAleatorio(List<Personagem> possiveisAlvos) {
        int tamList = possiveisAlvos.size();
        int escolha = bot.gerarNumeroDeZeroAN_1(tamList);
        return possiveisAlvos.get(escolha);
    }

    public void atacar(Personagem atacante, Personagem alvo) {
        this.combate.ataque(atacante, alvo);
        verificaVivo();
    }

    public void verificaVivo() {
        Personagem delete = null;
        for (Personagem p : time1) {
            if (p.getVidaAtual() <= 0) {
                delete = p;
            }
        }
        if (delete != null)
            time1.remove(delete);
        for (Personagem p : time2) {
            if (p.getVidaAtual() <= 0) {
                delete = p;
            }
        }
        if (delete != null)
            time2.remove(delete);
    }

    public boolean fimDeJogo() {
        if (this.time1.isEmpty() || this.time2.isEmpty())
            return true;
        return false;
    }

    public int timeVencedor() {
        if (this.time1.isEmpty())
            return 2;
        else
            return 1;
    }

    public void chamaReplay(){
        replay.imprimeReplay();
    }

    public void limparTabuleiro()
    {
        if(this.timeVencedor() == 1) {
            for (Personagem p : time1)
                this.tabuleiro.limparPosicao(p.getPosicao());
        }
        else{
            for(Personagem p : time2)
                this.tabuleiro.limparPosicao(p.getPosicao());
        }
    }

    public void resetarJogo()
    {
        this.numTurno = 0;
        this.time1.clear();
        this.time2.clear();
        this.proximoId = 1;
        this.modoPartida = 0;
        this.replay.getReplay().clear();
    }
}