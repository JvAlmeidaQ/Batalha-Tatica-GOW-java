package br.com.BatalhaTatica.view;

import br.com.BatalhaTatica.model.Personagem;

public class RepresentacaoVisual {

    public String getRepresentacaoVisual(Personagem personagem) {
        if (personagem == null) {
            return "[ ]"; // Retorna um espaço vazio se não houver personagem
        }
        return switch (personagem.getCasa()) {
            case STARK -> "🐺"; // Ou qualquer emoji que você escolher
            case LANNISTER -> "🦁";
            case TARGARYEN -> "🐲";
            default -> "?"; // Caso de erro
        };
    }
}

