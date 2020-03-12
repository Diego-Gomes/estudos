package br.com.alura.argentum.indicadores;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.alura.argentum.modelo.Candle;
import br.com.alura.argentum.modelo.SerieTemporal;

public class GeradorDeSerie {
    public static SerieTemporal criaSerie(double... valores){
        List<Candle> candles = new ArrayList<>();
        for (double d : valores) {
            candles.add(new Candle(d, d, d, d, 1000, LocalDateTime.now()));
        }
        return new SerieTemporal(candles);
    }
}