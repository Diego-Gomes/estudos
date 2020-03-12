package br.com.staticscrypto.ws;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class ClientBitcoinTradeWS {

	private final String URL = "https://api.bitcointrade.com.br/v3/public/BRLBTC/trades?page_size=500&current_page=";

	public Optional<List<InputStream>> consultarTrades() {

		List<InputStream> inputStream = new ArrayList<InputStream>();

		HttpURLConnection openConnection = null;

		for (int i = 1; i <= 10; i++) {

			try {

				URL url = new URL(URL + i);

				openConnection = (HttpURLConnection) url.openConnection();

				inputStream.add(openConnection.getInputStream());

			} catch (Exception e) {

			} finally {

			}

		}
		return Optional.of(inputStream);

	}

}
