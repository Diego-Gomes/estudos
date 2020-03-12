package br.com.bitcoin.ws;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

public class ClientBitcoinTradeWS {

	private final String URL = "https://api.bitcointrade.com.br/v3/public/BRLBTC/trades?page_size=100&current_page=1";

	public Optional<InputStream> consultarTrades() {

		InputStream inputStream = null;
		HttpURLConnection openConnection = null;

		try {

			URL url = new URL(URL);

			openConnection = (HttpURLConnection) url.openConnection();

			inputStream = openConnection.getInputStream();

		} catch (Exception e) {

		} finally {
		
		}

		return Optional.of(inputStream);

	}

}
