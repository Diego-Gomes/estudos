package br.com.staticscrypto.reader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.staticscrypto.gson.DateDeserializer;
import br.com.staticscrypto.models.JsonResult;
import br.com.staticscrypto.models.Trade;

public class LeitorJson {

	public List<Trade> carrega(List<InputStream> lista) {

		List<JsonResult> listaResult = new ArrayList<JsonResult>();

			lista.forEach(inputStream-> {
				Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new DateDeserializer()).create();
				
				try {
					Reader reader = new InputStreamReader(inputStream, "UTF-8");
					listaResult.add(gson.fromJson(reader, JsonResult.class));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			
		
		List<Trade> retorno = new ArrayList<Trade>();
		
		listaResult.forEach(x -> retorno.addAll(x.getData().getTrades()) );
		
		return retorno;

	}
	
}