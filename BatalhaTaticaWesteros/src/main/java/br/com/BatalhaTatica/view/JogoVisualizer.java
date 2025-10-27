package br.com.BatalhaTatica.view;

import br.com.BatalhaTatica.model.Casas;
import br.com.BatalhaTatica.model.Personagem;
import br.com.BatalhaTatica.model.Posicao;
import br.com.BatalhaTatica.model.Tabuleiro;

import java.util.Scanner;

public class JogoVisualizer {

    private final Tabuleiro tabuleiro;
    Scanner sc = new Scanner(System.in);

    public JogoVisualizer(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public String gerarTabelaCasas() {
        String header = String.format(
                "%-21s | %-25s | %-25s | %-25s |\n",
                "Atributo",
                "STARK",
                "LANNISTER",
                "TARGARYEN"
        );
        String separator = "----------------------|---------------------------|---------------------------|---------------------------|\n";
        String vidaMaxima = String.format(
                "%-21s | %-25s | %-25s | %-25s |\n",
                "Vida Máxima",
                "60",
                "50",
                "45"
        );
        String ataqueBase = String.format(
                "%-21s | %-25s | %-25s | %-25s |\n",
                "Ataque Base",
                "20",
                "20",
                "20"
        );
        String defesaBase = String.format(
                "%-21s | %-25s | %-25s | %-25s |\n",
                "Defesa Base",
                "10",
                "10",
                "10"
        );
        String alcanceMaximo = String.format(
                "%-21s | %-25s | %-25s | %-25s |\n",
                "Alcance Máximo",
                "1 Casa (Corpo a Corpo)",
                "2 Casas (Padrão)",
                "3 Casas (Distância)"
        );
        String modOfensivo = String.format(
                "%-21s | %-25s | %-25s | %-25s |\n",
                "Modificador Ofensivo",
                "Não se aplica",
                "+15% de Dano",
                "Ignora Defesa Base"
        );
        String modDefensivo = String.format(
                "%-21s | %-25s | %-25s | %-25s |\n",
                "Modificador Defensivo",
                "-20% de Dano",
                "Não se aplica",
                "Não se aplica"
        );
        return header +
                separator +
                vidaMaxima +
                ataqueBase +
                defesaBase +
                alcanceMaximo +
                modOfensivo +
                modDefensivo +
                separator;
    }

    public String imprimeTabuleiro() {
        StringBuilder tab = new StringBuilder();
        tab.append("╔════╤════╤════╤════╤════╤════╤════╤════╤════╤════╗\n");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 10; j++) {
                tab.append("║");
                Posicao posicao = new Posicao(i, j);
                if (!this.tabuleiro.verificarPosicao(posicao))
                    tab.append("   ");
                else
                    tab.append(" " + this.getRepresentacaoVisual(this.tabuleiro.getPosicaoPersonagem(posicao)) + " ");
            }
            tab.append("║\n");
            tab.append("╞════╬════╬════╬════╬════╬════╬════╬════╬════╬════╡\n");
        }
        for (int j = 0, i = 9; j < 10; j++) {
            tab.append("║");
            Posicao posicao = new Posicao(i, j);
            if (!this.tabuleiro.verificarPosicao(posicao))
                tab.append("   ");
            else
                tab.append(" " + this.getRepresentacaoVisual(this.tabuleiro.getPosicaoPersonagem(posicao)) + " ");
        }
        tab.append("║\n");
        tab.append("╚════╧════╧════╧════╧════╧════╧════╧════╧════╧════╝\n");

        return tab.toString();
    }

    public String getRepresentacaoVisual(Personagem personagem) {
        if (personagem == null) {
            return "  ";
        }
        return switch (personagem.getCasa()) {
            case STARK -> "🐺";
            case LANNISTER -> "🦁";
            case TARGARYEN -> "🐲";
            default -> "?";
        };
    }

    public String enviarNome() {
        System.out.println("Digite o nome do personagem:");
        String nome = sc.nextLine();

        String trim = nome.trim();
        while (trim.isEmpty() || !verificaNome(trim)) {
            System.out.println("Forneça um nome válido");
            nome = sc.nextLine();
            trim = nome.trim();
        }
        return nome;
    }

    public boolean verificaNome(String nome) {
        for (int i = 0; i < nome.length(); i++) {
            if (!Character.isDigit(nome.charAt(i)))
                return true;
        }
        return false;
    }

    public Casas enviaCasa() {
        System.out.println(this.gerarTabelaCasas());
        System.out.println();
        System.out.println("Qual a casa do personagem?");
        Casas casaEscolhida = null;
        String nomeCasa;

        while (casaEscolhida == null) {
            nomeCasa = sc.nextLine().trim().toUpperCase();
            try {
                casaEscolhida = Casas.valueOf(nomeCasa);
            } catch (IllegalArgumentException erro) {
                System.out.println("Casa digitada inválida!\nDigite novamente.");
            }
        }
        return casaEscolhida;
    }

}

