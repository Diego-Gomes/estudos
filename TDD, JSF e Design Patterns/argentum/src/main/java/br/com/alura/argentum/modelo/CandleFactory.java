package br.com.alura.argentum.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CandleFactory {
	
	public Candle geraCandleParaData(List<Negociacao> negociacoes, LocalDateTime data){
		double abertura = negociacoes.isEmpty() ? 0 : negociacoes.get(0).getPreco();
		double fechamento = negociacoes.isEmpty() ? 0 :negociacoes.get(negociacoes.size()-1).getPreco();
		
		double minimo = negociacoes.isEmpty() ? 0 : negociacoes.get(0).getPreco();
		double maximo = negociacoes.isEmpty() ? 0 : negociacoes.get(0).getPreco();
		
		double volume = 0;
		for (Negociacao negociacao : negociacoes) {
			volume += negociacao.getVolume();
			
			if(negociacao.getPreco() > maximo){
				maximo = negociacao.getPreco();
			}else if ( negociacao.getPreco() < minimo){
				minimo = negociacao.getPreco();
			}
			
		}

		return new Candle(abertura, fechamento, maximo, minimo, volume, data);
	}

	public List<Candle> constroiCandles(List<Negociacao> negociacoes) {
		
		List<Candle> Candles = new ArrayList<>();
		
		List<Negociacao> negociacoesDoDia = new ArrayList<>();
		
		LocalDateTime dataAtual = negociacoes.get(0).getData();
				
		for (Negociacao negociacao : negociacoes) {
			
			if(negociacao.isMesmoDia(dataAtual)){
				negociacoesDoDia.add(negociacao);
			}else{
				Candle candle = geraCandleParaData(negociacoesDoDia, dataAtual);
				Candles.add(candle);
				
				negociacoesDoDia = new ArrayList<>();
				dataAtual = negociacao.getData();
			}
			
		}
		
		Candle candle = geraCandleParaData(negociacoesDoDia, dataAtual);
		Candles.add(candle);
		
		return Candles;
	}

}