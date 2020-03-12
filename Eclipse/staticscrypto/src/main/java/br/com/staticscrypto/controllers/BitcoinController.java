package br.com.staticscrypto.controllers;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.staticscrypto.models.Trade;
import br.com.staticscrypto.reader.LeitorJson;
import br.com.staticscrypto.ws.ClientBitcoinTradeWS;

@RequestMapping("bitcoin")
@RestController
public class BitcoinController {

	@Autowired
	private ClientBitcoinTradeWS clientBitcoinTradeWS;

	@GetMapping
	public ResponseEntity<List<Trade>> listar() {

		try {

			clientBitcoinTradeWS = new ClientBitcoinTradeWS();
			Optional<List<InputStream>> InputStreamTrades = clientBitcoinTradeWS.consultarTrades();
			List<Trade> trades = null;
			if (InputStreamTrades.isPresent())
				trades = new LeitorJson().carrega(InputStreamTrades.get());
			 
			return ResponseEntity.ok().body(trades);

		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

	}
}
