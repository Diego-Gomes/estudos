package br.com.alura.argentum.ws;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import br.com.alura.argentum.modelo.Negociacao;
import br.com.alura.argentum.reader.LeitorXml;

public class ClientWebservice {
	
	public static final String URL_WEBSERVICES = "http://argentumws.caelum.com.br/negociacoes";

	public List<Negociacao> getNegociacoes() {

		HttpURLConnection connection = null;

		try {
			URL url = new URL(URL_WEBSERVICES);
			connection = (HttpURLConnection) url.openConnection();

			InputStream content = connection.getInputStream();
			return new LeitorXml().carrega(content);

		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			connection.disconnect();
		}

	}
	
	public static void main(String[] args) {
		
		ClientWebservice ws = new ClientWebservice();
		
		List<Negociacao> negociacoes = ws.getNegociacoes();
		
		for (Negociacao negociacao : negociacoes) {
			System.out.println(negociacao.getPreco());
		}
	}

}
