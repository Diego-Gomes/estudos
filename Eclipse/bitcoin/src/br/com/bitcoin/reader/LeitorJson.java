package br.com.bitcoin.reader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.bitcoin.gson.DateDeserializer;
import br.com.bitcoin.models.JsonResult;
import br.com.bitcoin.models.Trade;

public class LeitorJson {

	public List<Trade> carrega(InputStream inputStream) {

		Reader reader;
		JsonResult result = null;
		try {

			Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new DateDeserializer()).create();

			reader = new InputStreamReader(inputStream, "UTF-8");

			result = gson.fromJson(reader, JsonResult.class);

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result.getData().getTrades();

	}
	
}