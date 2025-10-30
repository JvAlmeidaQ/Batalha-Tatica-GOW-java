package br.com.BatalhaTatica.util;

import br.com.BatalhaTatica.model.Personagem;
import br.com.BatalhaTatica.model.Tabuleiro;
import br.com.BatalhaTatica.service.Jogo;

import java.util.ArrayList;
import java.util.List;

public class Turno {

    private Tabuleiro tabuleiroTurno;
    private String printTimes;

    public Turno(Tabuleiro tabuleiro, String times) {

        printTimes = times;
        try {
            this.tabuleiroTurno = tabuleiro.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Falha ao clonar o Tabuleiro.", e);
        }
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiroTurno;
    }

    public String getPrintTimes(){
        return printTimes;
    }
}
