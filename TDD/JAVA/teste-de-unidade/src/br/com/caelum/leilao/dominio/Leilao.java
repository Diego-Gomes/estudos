package br.com.caelum.leilao.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao {

	private String descricao;
	private List<Lance> lances;
	private final int LIMITE_LANCE_POR_USUARIO = 5;
	
	public Leilao(String descricao) {
		this.descricao = descricao;
		this.lances = new ArrayList<Lance>();
	}
	
	public void propoe(Lance lance) {
		
		if(this.getLances().isEmpty() || validarLanceUsuario(lance.getUsuario())){
			lances.add(lance);
		}
	}

	private boolean validarLanceUsuario(Usuario usuario) {
		return (!isUsuarioUltimoLance(usuario) 
				&&  qtdLancesPorUsuario(usuario) < LIMITE_LANCE_POR_USUARIO);
	}

	private boolean isUsuarioUltimoLance(Usuario usuario) {
		return getUsuarioUltimoLance().equals(usuario);
	}
	
	private Usuario getUsuarioUltimoLance() {
		return this.lances.get(this.lances.size()-1).getUsuario();
	}

	private int qtdLancesPorUsuario(Usuario usuario) {
		int qtdLanceUsuario = 0;
		
		for (Lance lance : lances) {
			if(lance.getUsuario().equals(usuario))
				qtdLanceUsuario += 1;
		}
		
		return qtdLanceUsuario ;
	}
	
	
	public void dobraLance(Usuario usuario)
	{
		Lance ultimoLance = getUltimoLanceDoUsuario(usuario);
		if(ultimoLance != null)
			propoe(new Lance(usuario, ultimoLance.getValor() * 2));
	}
	
	public Lance getUltimoLanceDoUsuario(Usuario usuario) {
		Lance ultimoLance = null;
		for (Lance lance : lances) {
			if(lance.getUsuario().equals(usuario))
				ultimoLance = lance;
		}
	
		return ultimoLance;
		
	}
	
	

	public String getDescricao() {
		return descricao;
	}

	public List<Lance> getLances() {
		return Collections.unmodifiableList(lances);
	}
}
