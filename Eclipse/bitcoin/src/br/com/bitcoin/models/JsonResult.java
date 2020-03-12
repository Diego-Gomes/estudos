package br.com.bitcoin.models;

import com.google.gson.annotations.SerializedName;

public class JsonResult {

	private final String code;
	private final String message;

	@SerializedName("data")
	private Data data;

	public JsonResult(String code, String message, Data data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public Data getData() {
		return data;
	}
}