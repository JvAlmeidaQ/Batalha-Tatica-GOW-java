package br.com.BatalhaTatica.service;

import br.com.BatalhaTatica.model.*;
import br.com.BatalhaTatica.view.JogoVisualizer;

import java.util.Scanner;

public class Jogo {


    public Personagem criarPersonagem(Scanner sc, int ID){
        System.out.println("Qual o nome do personagem?");
        String nome = sc.nextLine();
        while (nome == ""){
            System.out.println("Forneça um nome válido");
            nome = sc.nextLine();
        }
        System.out.println(JogoVisualizer.gerarTabelaCasas());
        System.out.println();
        System.out.println("Qual a casa do personagem?");
        Casas casaEscolhida = null;
        String nomeCasa;

        while (casaEscolhida == null){
            nomeCasa = sc.nextLine().trim().toUpperCase();
            try{
                casaEscolhida = Casas.valueOf(nomeCasa);
            }catch (IllegalArgumentException erro){
                System.out.println("Casa digitada inválida!\nDigite novamente.");
            }
        }

        if(casaEscolhida == Casas.STARK)
            return new Stark(ID, nome);
        else if(casaEscolhida == Casas.LANNISTER)
            return new Lannister(ID, nome);
        else
            return new Targaryen(ID, nome);
    }


}