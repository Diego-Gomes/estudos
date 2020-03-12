package br.com.staticscrypto.models;

import java.util.Date;

public class Trade {

	private final String type; // Tipo da ordem ativa - sell / buy (STRING)
	private final float amount; // Valor da ordem (FLOAT)
	private final float unit_price; // Pre�o da unidade da moeda (FLOAT)
	private final String active_order_code; // C�digo da ordem ativa (STRING)
	private final String passive_order_code; // C�digo da ordem passiva (STRING)
	private final Date date; // Data da ordem (DATE - formato UTC)

	public Trade(String type, float amount, float unit_price, String active_order_code, String passive_order_code,
			Date date) {
		this.type = type;
		this.amount = amount;
		this.unit_price = unit_price;
		this.active_order_code = active_order_code;
		this.passive_order_code = passive_order_code;
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public float getAmount() {
		return amount;
	}

	public float getUnit_price() {
		return unit_price;
	}

	public String getActive_order_code() {
		return active_order_code;
	}

	public String getPassive_order_code() {
		return passive_order_code;
	}

	public Date getDate() {
		return date;
	}

	
	
	
}
