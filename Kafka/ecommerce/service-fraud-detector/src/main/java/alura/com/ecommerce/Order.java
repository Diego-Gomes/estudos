package alura.com.ecommerce;

import java.math.BigDecimal;

class Order {

	private final String userId;
	private final String orderId;	
	private final BigDecimal valor;
	
	public Order(String userId, String orderId, BigDecimal valor) {
		super();
		this.userId = userId;
		this.orderId = orderId;
		this.valor = valor;
	}

	public String getUserId() {
		return userId;
	}

	public String getOrderId() {
		return orderId;
	}

	public BigDecimal getValor() {
		return valor;
	}
	
	
	
}
