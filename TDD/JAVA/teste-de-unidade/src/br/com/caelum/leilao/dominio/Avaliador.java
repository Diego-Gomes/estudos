package br.com.caelum.leilao.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Avaliador {

	private Lance maiorLance;
	private Lance menorLance;
	List<Lance> maioresLances;

	public Lance getMaiorLance() {
		return this.menorLance;
	}

	public Lance getMenorLance() {
		return this.maiorLance;
	}

	public void avalia(Leilao leilao) {

		if(leilao.getLances().size() ==0) 
	            throw new RuntimeException("Não é possível avaliar um leilão sem lances");

		this.maiorLance = new Lance(null, Double.POSITIVE_INFINITY);
		this.menorLance = new Lance(null, Double.NEGATIVE_INFINITY);

		for (Lance lance : leilao.getLances()) {

			if (lance.getValor() > this.menorLance.getValor())
				menorLance = lance;

			if (lance.getValor() < this.maiorLance.getValor())
				maiorLance = lance;
		}

		this.maioresLances = new ArrayList<Lance>(leilao.getLances());

		Collections.sort(this.maioresLances, new Comparator<Lance>() {
			public int compare(Lance o1, Lance o2) {
				if (o1.getValor() < o2.getValor())
					return 1;
				if (o1.getValor() > o2.getValor())
					return -1;
				return 0;

			}
		});

		this.maioresLances = this.maioresLances.subList(0,
				this.maioresLances.size() > 3 ? 3 : this.maioresLances.size());

	}

	public List<Lance> getTresMaioresLances() {
		return this.maioresLances;
	}

}
