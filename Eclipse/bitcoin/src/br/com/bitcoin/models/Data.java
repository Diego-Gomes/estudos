package br.com.bitcoin.models;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

import br.com.bitcoin.models.Trade;

public class Data {
	@SerializedName("pagination")
	private final Pagination pagination;
	@SerializedName("trades")
	private final ArrayList<Trade> trades;

	public Data(Pagination pagination, ArrayList<Trade> trades) {
		this.pagination = pagination;
		this.trades = trades;
	}

	public Pagination getPagination() {
		return pagination;
	}
	
	public ArrayList<Trade> getTrades() {
		return trades;
	}
	
}