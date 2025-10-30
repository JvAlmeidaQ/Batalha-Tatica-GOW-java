package br.com.BatalhaTatica.view;

import br.com.BatalhaTatica.model.*;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
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

    public void imprimeTabuleiro() {
        StringBuilder tab = new StringBuilder();
        tab.append("╔════╤════╤════╤════╤════╤════╤════╤════╤════╤════╗\n");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 10; j++) {
                tab.append("║");
                Posicao posicao = new Posicao(i, j);
//                if (!this.tabuleiro.posicaoIsOcupada(posicao))
//                    tab.append("   ");
//                else
                tab.append(" " + this.getRepresentacaoVisual(this.tabuleiro.getPosicaoPersonagem(posicao)) + " ");
            }
            tab.append("║\n");
            tab.append("╞════╬════╬════╬════╬════╬════╬════╬════╬════╬════╡\n");
        }
        for (int j = 0, i = 9; j < 10; j++) {
            tab.append("║");
            Posicao posicao = new Posicao(i, j);
//            if (!this.tabuleiro.posicaoIsOcupada(posicao))
//                tab.append("   ");
//            else
            tab.append(" " + this.getRepresentacaoVisual(this.tabuleiro.getPosicaoPersonagem(posicao)) + " ");
        }
        tab.append("║\n");
        tab.append("╚════╧════╧════╧════╧════╧════╧════╧════╧════╧════╝\n");

        System.out.println(tab.toString());
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

    public String enviarNome(List<Personagem> personagens) {
        System.out.println("Digite o nome do personagem:");
        String nome = sc.nextLine();

        String trim = nome.trim();
        while (trim.isEmpty() || !verificaNome(trim) || verificaNomeRepetido(nome, personagens)) {
            System.out.println("Esse nome já está sendo utilizado ou Não é valido! \nForneça outro nome: ");
            nome = sc.nextLine();
            trim = nome.trim();
        }
        return nome;
    }

    public boolean verificaNomeRepetido(String nome, List<Personagem> time) {
        if (!time.isEmpty()) {
            for (Personagem p : time) {
                if (Objects.equals(p.getNome(), nome)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean verificaNome(String nome) {
        for (int i = 0; i < nome.length(); i++) {
            if (!Character.isDigit(nome.charAt(i)))
                return true;
        }
        return false;
    }

    public Casas enviaCasa() {
        System.out.println();
        System.out.println(this.gerarTabelaCasas());
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
        System.out.println();
        return casaEscolhida;
    }

    public void mensagemNumeroPersonagem(int ind) {
        switch (ind) {
            case 0 -> {
                System.out.println("Criação do Primeriro personagem");
            }
            case 1 -> {
                System.out.println("Criação do Segundo personagem");
            }
            case 2 -> {
                System.out.println("Criação do Terceiro personagem");
            }
        }
    }

    public String mensagemMorte(Personagem atacante, Personagem morto) {
        String tabulacao = "---------------------------------------------------------------------------\n";
        String visualKill = getRepresentacaoVisual(atacante) + "🗡" + getRepresentacaoVisual(morto) + "\n";
        String msg = morto.getNome() + " foi morto por " + atacante.getNome() + "!\n";
        return tabulacao
                + visualKill
                + msg
                + tabulacao;
    }

    public void mensagemAtaque(Personagem atacante, Personagem defensor) {
        System.out.println(defensor.getNome() + " foi atacado por " + atacante.getNome() + "!");
    }

    public int modoDaPartida() {
        int escolha = -1;

        System.out.println("\n==============================");
        System.out.println("        MODO DA PARTIDA       ");
        System.out.println("==============================");
        System.out.println("Escolha o modo de jogo:");
        System.out.println("  1. Jogador vs Jogador");
        System.out.println("  2. Jogador vs Bot");
        System.out.print("→ Sua escolha: ");

        while (escolha != 1 && escolha != 2) {
            try {
                escolha = sc.nextInt();

                if (escolha != 1 && escolha != 2) {
                    System.out.println("\nEscolha fora do intervalo. Digite 1 ou 2.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Digite apenas os números 1 ou 2.");
                sc.nextLine();
            }
        }
        sc.nextLine();
        return escolha;
    }

    public void mensagemCriarTime(int time) {
        if (time == 1)
            System.out.println("CRIAÇÃO DO PRIMEIRO TIME");
        else {
            System.out.println();
            System.out.println("CRIAÇÃO DO SEGUNDO TIME");
            System.out.println();
        }
    }

    public void mensagemDeCriacao() {
        System.out.println("⚔️  Times foram formados, bandeiras erguidas!");
        System.out.println("🏟️  Cada equipe está em posição, pronta para a batalha.");
        System.out.println("🔥  O jogo está prestes a começar... Que vença o melhor!");
        System.out.println();
    }

    public void numTurno(int turno) {
        System.out.println();
        System.out.println("🔄 Turno " + turno + " iniciado.");
        System.out.println("Analise, planeje e execute sua jogada!");
        System.out.println();
    }

    public String retornaImprecaoPersonagens(List<Personagem> time1, List<Personagem> time2) {
        String printTime1 = "TIME 1\n";
        for (Personagem p : time1) {
            printTime1 = printTime1 + p + "\n";
        }
        String printTime2 = "\nTIME 2\n";
        for (Personagem p : time2) {
            printTime2 = printTime2 + p + "\n";
        }
        return printTime1
                +printTime2;
    }

    public void imprimePersonagens(List<Personagem> time1, List<Personagem> time2){
        System.out.print(retornaImprecaoPersonagens(time1, time2));
    }

    public void imprimeString(String print){
        System.out.println(print);
    }

    public Personagem escolhaDoPersonagem(List<Personagem> personagens) {
        System.out.println("============ Lista de Personagens Ativos ============");
        System.out.println("---------------------------------------------------------------------------");
        for (Personagem p : personagens) {
            System.out.println(p);
            System.out.println("---------------------------------------------------------------------------");
        }

        Personagem personagemEncontrado = null;
        while (personagemEncontrado == null) {
            System.out.print("Digite o nome do Personagem que você vai jogar nessa rodada: ");
            String nome = sc.nextLine();

            for (Personagem p : personagens) {
                if (p.getNome().equals(nome))
                    personagemEncontrado = p;
            }
            if (personagemEncontrado == null)
                System.out.print("Personagem não encontrado, digite um nome valido: ");
        }

        return personagemEncontrado;
    }


    public Direcao escolhaDoDirecao() {
        Direcao direcaoEscolhida = null;

        while (direcaoEscolhida == null) {
            System.out.println("Qual será o seu movimento na rodada: ");
            System.out.println("W (Cima) / A (Esquerda) / S (Baixo) / D (Direita) / Parado (FicarParado)");
            System.out.print("Sua escolha: ");
            String escolha = sc.nextLine();

            try {
                direcaoEscolhida = Direcao.valueOf(escolha.toUpperCase());

            } catch (IllegalArgumentException e) {
                System.out.println("Direção inválida! Por favor, digite apenas W, A, S, D ou se não quer se mover");
            }

        }
        return direcaoEscolhida;
    }

    public void movimentacaoInvalida() {
        System.out.println("Nao é possível mover para esta posição, escolha outra direção!");
        escolhaDoDirecao();
    }

    public Personagem escolherAlvo(List<Personagem> personagensAlvos) {
        System.out.println("=== Lista de Possíveis Alvos ===");
        for (Personagem alvo : personagensAlvos) {
            System.out.println(alvo);
            System.out.println("----------------------------");
        }
        Personagem alvoEscolhido = null;
        while (alvoEscolhido == null) {
            System.out.println("Digite o nome do personagem que você quer atacar?");
            String nome = sc.nextLine();

            for (Personagem alvo : personagensAlvos) {
                if (alvo.getNome().equals(nome))
                    alvoEscolhido = alvo;
            }
            if (alvoEscolhido == null)
                System.out.print("Personagem não encontrado, digite um nome valido: ");
        }

        return alvoEscolhido;
    }

    public int msgFimdeJogo(int timeVencedor) {
        System.out.println("FIM DE JOGO!");
        System.out.println("O time " + timeVencedor + " Venceu!!!");
        System.out.println("Obrigado por jogar!");
        System.out.println("1.Jogar novamente");
        System.out.println("2.Replay");
        System.out.println("3.Sair");
        int opcao = -1;

        while (opcao != 1 && opcao != 2 && opcao != 3) {
            try {
                opcao = sc.nextInt();

                if (opcao != 1 && opcao != 2 && opcao != 3) {
                    System.out.println("\nEscolha invalida. Digite 1, 2 ou 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Digite apenas os números 1, 2 ou 3.");
                sc.nextLine();
            }
        }
        sc.nextLine();
        return opcao;
    }
}

