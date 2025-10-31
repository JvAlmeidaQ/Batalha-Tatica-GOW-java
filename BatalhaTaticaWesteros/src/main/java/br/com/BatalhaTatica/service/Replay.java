package br.com.BatalhaTatica.service;

import br.com.BatalhaTatica.util.Turno;
import br.com.BatalhaTatica.view.JogoVisualizer;

import java.util.ArrayList;
import java.util.List;

public class Replay {

    private JogoVisualizer view;

    private List<Turno> replay = new ArrayList<>();

    public Replay(JogoVisualizer jogoVisualizer) {
        this.view = jogoVisualizer;
    }

    public void setTurno(Turno T){
        replay.add(T);
    }

    public List<Turno> getReplay()
    {
        return replay;
    }
    public void imprimeReplayPersonagens(Turno T) {
        view.imprimeString(T.getPrintTimes());
    }

    public void imprimeReplayTabuleiro(Turno T) {
        view.imprimeString(T.getprintTabuleiro());
    }

    public void imprimeReplay() {
        for (Turno T : replay) {
            System.out.println("Turno " + replay.indexOf(T));
            imprimeReplayTabuleiro(T);
            imprimeReplayPersonagens(T);
        }
    }
}
