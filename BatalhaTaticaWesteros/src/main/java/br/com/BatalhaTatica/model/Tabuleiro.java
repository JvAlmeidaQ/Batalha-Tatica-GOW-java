package br.com.BatalhaTatica.model;

import br.com.BatalhaTatica.model.Personagem;

public class Tabuleiro {
    public Integer [][] tabuleiro;
    


    public Tabuleiro() {
        tabuleiro = new Integer[10][10];
    }
    
    public int setRandomLinha() {
    	return (int) (Math.random() * 10);
    }
    
    public int setRandomColuna() {
    	return (int) (Math.random() * 10)/7;
    }
    public final void setInicialPosition(Personagem x) {
    	int line, column;
    	line = setRandomLinha();
    	column = setRandomColuna();
    	while(tabuleiro[line][column] != null) { //checa se a posição esta vazia
    		line = setRandomLinha();
    		column = setRandomColuna();
    	}
    	
    	x.Posicao.linha = line;
    	x.Posicao.coluna = column;
    	
    	tabuleiro[line][column] = x.casa;
    }

    //norte 1
    //leste 2
    //sul 3
    //oeste 4
    public int move(Personagem x, int direcao) {
    	if(direcao == 1) {
    		if(Posicao.linha == 0)
    			return -2;
    		else
    			x.Posicao.linha--;
    	}
    	else if(direcao == 2) {
    		if(Posicao.coluna == 9)
    			return -2;
    		else
    			x.Posicao.coluna++;
    	}
    	else if(direcao == 3) {
    		if(Posicao.linha == 9)
    			return -2;
    		else    		
    			x.Posicao.linha++;
    	}
    	else if(direcao == 4) {
    		if(Posicao.coluna == 0)
    			return -2;
    		else
    			x.Posicao.coluna--;
    	}
    	else
    		return -1;
    	return 0;
    }

	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("╔═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╗\n");
		for(int i=0; i<9; i++) {
			for(int j=0; j<10; j++) {
				sb.append("║");
				if(this.tabuleiro[i][j] == null)
					sb.append("   ");
				else
					sb.append(" " + tabuleiro[i][j] + " ");
			}
			sb.append("║\n");
			sb.append("╞═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╡\n");
		}
		for(int j=0, i=9; j<10; j++) {
			sb.append("║");
			if(this.tabuleiro[i][j] == null)
				sb.append("   ");
			else
				sb.append(" " + tabuleiro[i][j] + " ");
		}
		sb.append("║\n");
		sb.append("╚═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╝\n");
		
		return sb.toString();
	}
}
