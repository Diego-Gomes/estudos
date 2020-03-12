package br.com.alura.argentum.graficos;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.OhlcChartModel;
import org.primefaces.model.chart.OhlcChartSeries;

import br.com.alura.argentum.modelo.Candle;
import br.com.alura.argentum.modelo.SerieTemporal;

public class GeradorModeloGraficoCandle {
	
	private SerieTemporal serie;

	public GeradorModeloGraficoCandle(SerieTemporal serie) {
		this.serie = serie;
	}

	public OhlcChartModel plota() {
		List<OhlcChartSeries> todasAsCandleSeries = new ArrayList<OhlcChartSeries>();
		for(int i=0; i <= serie.getUltimaPosicao();i++){
			Candle candle = serie.getCandle(i);
			OhlcChartSeries candleSeries = new OhlcChartSeries(i, candle.getAbertura(), candle.getMaximo(), candle.getMinimo(), candle.getFechamento());
			todasAsCandleSeries.add(candleSeries);
		}
		OhlcChartModel ohlcModel = new OhlcChartModel(todasAsCandleSeries);
		ohlcModel.setTitle("Candlestick");
        ohlcModel.setCandleStick(true);
        ohlcModel.getAxis(AxisType.X).setLabel("Sector");
        ohlcModel.getAxis(AxisType.Y).setLabel("Index Value");
        
		return ohlcModel;
	}

}