package br.com.bitcoin.beans;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.OhlcChartModel;
import org.primefaces.model.chart.OhlcChartSeries;

import br.com.bitcoin.models.Trade;
import br.com.bitcoin.reader.LeitorJson;
import br.com.bitcoin.ws.ClientBitcoinTradeWS;

@ManagedBean
@ViewScoped
public class BitcoinBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private OhlcChartModel ohlcModel;
	private List<Trade> trades;

	@PostConstruct
	public void init() {
		createrTrades();
		createOhlcModel();
	}

	public List<Trade> getTrades() {
		return trades;
	}

	public OhlcChartModel getOhlcModel() {
		return ohlcModel;
	}

	private void createOhlcModel() {
		ohlcModel = new OhlcChartModel();

		for (int i = 1; i < 41; i++) {
			ohlcModel.add(new OhlcChartSeries(i, Math.random() * 80 + 80, Math.random() * 50 + 110,
					Math.random() * 20 + 80, Math.random() * 80 + 80));
		}

		ohlcModel.setTitle("Candlestick");
		ohlcModel.setCandleStick(true);
		ohlcModel.getAxis(AxisType.X).setLabel("Sector");
		ohlcModel.getAxis(AxisType.Y).setLabel("Index Value");
	}

	private void createrTrades() {

		ClientBitcoinTradeWS clientBitcoinTradeWS = new ClientBitcoinTradeWS();
		Optional<InputStream> InputStreamTrades = clientBitcoinTradeWS.consultarTrades();

		if (InputStreamTrades.isPresent())
			trades = new LeitorJson().carrega(InputStreamTrades.get());
	}

	public void itemSelect(ItemSelectEvent event) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
				"Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}