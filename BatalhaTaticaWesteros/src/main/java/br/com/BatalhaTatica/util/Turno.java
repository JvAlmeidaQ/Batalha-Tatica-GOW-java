package br.com.BatalhaTatica.util;

import br.com.BatalhaTatica.model.Personagem;
import br.com.BatalhaTatica.model.Tabuleiro;
import br.com.BatalhaTatica.service.Jogo;

import java.util.ArrayList;
import java.util.List;

public class Turno {

    private String printTabuleiro;
    private String printTimes;

    public Turno(String tabuleiroTurno, String times) {
        printTabuleiro = tabuleiroTurno;
        printTimes = times;
    }

    public String getprintTabuleiro() {
        return printTabuleiro;
    }

    public String getPrintTimes(){
        return printTimes;
    }
}
