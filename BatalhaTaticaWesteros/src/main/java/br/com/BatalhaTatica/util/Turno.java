package br.com.BatalhaTatica.util;

import br.com.BatalhaTatica.model.Personagem;
import br.com.BatalhaTatica.model.Tabuleiro;
import br.com.BatalhaTatica.service.Jogo;

import java.util.ArrayList;
import java.util.List;

public class Turno {

    private Jogo jogo;
    private Tabuleiro tabuleiroTurno;

    private List<Personagem> time1Turno; // = jogo.getTime1();
    private List<Personagem> time2Turno; // = jogo.getTime2();

    public Turno(Tabuleiro tabuleiro, Jogo jogo) {
        this.jogo = jogo;
        //this.tabuleiro = tabuleiro;

        try {
            // Clona o objeto Tabuleiro. A referência 'tabuleiroTurno' agora aponta
            // para um NOVO objeto, independente do Tabuleiro original.
            this.tabuleiroTurno = tabuleiro.clone();
        } catch (CloneNotSupportedException e) {
            // Trate o erro se a clonagem falhar
            throw new RuntimeException("Falha ao clonar o Tabuleiro.", e);
        }

        this.time1Turno = new ArrayList<>();
        this.time2Turno = new ArrayList<>();

        for (Personagem pOriginal : jogo.getTime()) {
            try {
                this.time1Turno.add(pOriginal.clone());
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException("Falha ao clonar Personagem do Time 1.", e);
            }
        }
        for (Personagem pOriginal : jogo.getTime()) {
            try {
                this.time2Turno.add(pOriginal.clone());
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException("Falha ao clonar Personagem do Time 2.", e);
            }
        }
    }

    public List<Personagem> getTime1() {
        return this.time1Turno;
    }

    public List<Personagem> getTime2() {
        return this.time2Turno;
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiroTurno;
    }
}
