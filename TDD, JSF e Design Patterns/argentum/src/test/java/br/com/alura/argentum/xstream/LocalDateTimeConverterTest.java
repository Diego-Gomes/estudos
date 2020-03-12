package br.com.alura.argentum.xstream;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import br.com.alura.argentum.modelo.Negociacao;

public class LocalDateTimeConverterTest {

	@Test
	public void deveRetornarUmXmlComADataCorreta(){
		LocalDateTime hoje = LocalDateTime.of(2016, 04, 04, 12, 00);
		
		Negociacao negociacao  = new Negociacao(10.0, 4, hoje);
		
		XStream stream = new XStream( new DomDriver());
		stream.alias( "negociacao",Negociacao.class);
		stream.registerLocalConverter(Negociacao.class, "data", new LocalDateTimeConverter());
		String xmlConvertido = stream.toXML(negociacao);
		
		String xmlEsperado = 
				"<negociacao>\n"
				+ "  <preco>10.0</preco>\n"
				+ "  <quantidade>4</quantidade>\n"
				+ "  <data>\n"
				+ "    <time>1459782000000</time>\n"
				+ "    <timezone>America/Sao_Paulo</timezone>\n"
				+ "  </data>\n"
				+ "</negociacao>";
		
		
		Assert.assertEquals(xmlEsperado, xmlConvertido);
	}
	
	@Test
	public void deveConverterUmXmlParaUmaNegociacaoCorreta(){
		
		String xml = 
				"<negociacao>\n"
				+ "  <preco>10.0</preco>\n"
				+ "  <quantidade>4</quantidade>\n"
				+ "  <data>\n"
				+ "    <time>1459782000000</time>\n"
				+ "    <timezone>America/Sao_Paulo</timezone>\n"
				+ "  </data>\n"
				+ "</negociacao>";
		
		XStream stream = new XStream( new DomDriver());
		stream.registerLocalConverter(Negociacao.class, "data", new LocalDateTimeConverter());
		stream.alias( "negociacao",Negociacao.class);
		Negociacao negociacaoGerada = (Negociacao) stream.fromXML(xml);
		
		LocalDateTime hoje = LocalDateTime.of(2016, 04, 04, 12, 00);
		Negociacao negociacaoEsperada = new Negociacao(10.0, 4, hoje);
				
		Assert.assertEquals(negociacaoEsperada, negociacaoGerada);
		
		
	}

}
