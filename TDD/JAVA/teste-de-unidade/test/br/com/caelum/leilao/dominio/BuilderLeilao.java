package br.com.caelum.leilao.dominio;

public class BuilderLeilao {
	
	
	private Leilao leilao;

	public BuilderLeilao Para(String descricao) {
	
		this.leilao = new Leilao(descricao);
	
		return this;
	}
	
	public BuilderLeilao Lance(Usuario usuario, Double valor) {
		
		this.leilao.propoe(new Lance(usuario, valor));
		
		return this;
	}
	

	public BuilderLeilao DobrarLance(Usuario usuario) {
		this.leilao.dobraLance(usuario);
		return this;
	}

	public Leilao Build() {
		return this.leilao;
	}

}
