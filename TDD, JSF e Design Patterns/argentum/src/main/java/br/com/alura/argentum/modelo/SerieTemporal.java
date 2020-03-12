package br.com.alura.argentum.modelo;

import java.util.List;

public class SerieTemporal {
    private final List<Candle> candles;
    public SerieTemporal(List<Candle> candles){
        this.candles = candles;
    }
    public Candle getCandle(int posicao){
        return candles.get(posicao);
    }
    public int getUltimaPosicao(){
        return candles.size() -1;
    }
}