package br.com.alura.argentum.indicadores;

import br.com.alura.argentum.modelo.SerieTemporal;

public class IndicadorFechamento implements Indicador {
	
	@Override
	public double calcula(int posicao, SerieTemporal serie) {
        return serie.getCandle(posicao).getFechamento();
    }
	
	public String toString() {
        return "Fechamento";
    }

}