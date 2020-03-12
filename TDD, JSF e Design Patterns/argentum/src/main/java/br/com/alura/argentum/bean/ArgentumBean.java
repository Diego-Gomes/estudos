package br.com.alura.argentum.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.OhlcChartModel;

import br.com.alura.argentum.graficos.GeradorModeloGrafico;
import br.com.alura.argentum.graficos.GeradorModeloGraficoCandle;
import br.com.alura.argentum.indicadores.IndicadorFactory;
import br.com.alura.argentum.modelo.Candle;
import br.com.alura.argentum.modelo.CandleFactory;
import br.com.alura.argentum.modelo.Negociacao;
import br.com.alura.argentum.modelo.SerieTemporal;
import br.com.alura.argentum.ws.ClientWebservice;

@ManagedBean
@ViewScoped
public class ArgentumBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Negociacao> negociacoes;
	private LineChartModel modeloGrafico;
	private OhlcChartModel modeloGraficoCandle;

	private String nomeMedia;
	private String nomeIndicadorBase;

	public void setNomeIndicadorBase(String nomeIndicadorBase) {
		this.nomeIndicadorBase = nomeIndicadorBase;
	}

	public void setNomeMedia(String nomeMedia) {
		this.nomeMedia = nomeMedia;
	}

	public String getNomeIndicadorBase() {
		return nomeIndicadorBase;
	}

	public String getNomeMedia() {
		return nomeMedia;
	}

	public ArgentumBean() {
		negociacoes = new ClientWebservice().getNegociacoes();
		gerarGrafico();
	}

	public List<Negociacao> getNegociacoes() {
		return negociacoes;
	}

	public LineChartModel getModeloGrafico() {
		return modeloGrafico;
	}

	public void gerarGrafico() {
		List<Candle> candles = new CandleFactory().constroiCandles(negociacoes);
		SerieTemporal serie = new SerieTemporal(candles);

		GeradorModeloGrafico geradorGrafico = new GeradorModeloGrafico(serie, 2, serie.getUltimaPosicao());

		IndicadorFactory fabrica = new IndicadorFactory(nomeMedia, nomeIndicadorBase);
		geradorGrafico.plotaIndicador(fabrica.defineIndicador());
		this.modeloGrafico = geradorGrafico.getModeloGrafico();

		GeradorModeloGraficoCandle geradorGraficoCandle = new GeradorModeloGraficoCandle(serie);
		this.modeloGraficoCandle = geradorGraficoCandle.plota();
	}

	public OhlcChartModel getModeloGraficoCandle() {
		return modeloGraficoCandle;
	}

	public void setModeloGraficoCandle(OhlcChartModel modeloGraficoCandle) {
		this.modeloGraficoCandle = modeloGraficoCandle;
	}

}
