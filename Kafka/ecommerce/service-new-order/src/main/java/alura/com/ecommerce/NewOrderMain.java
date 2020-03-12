package alura.com.ecommerce;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		try (KafkaDispatcher<Order> fraudDetectorDispatcher = new KafkaDispatcher<Order>()) {
			try (KafkaDispatcher<Order> emailkafkaDispatcher = new KafkaDispatcher<Order>()) {

				for (int i = 0; i < 10; i++) {


					String userId = UUID.randomUUID().toString();
					String orderId = UUID.randomUUID().toString();
					
					BigDecimal valor = new BigDecimal(Math.random() * 5000 + 1);
					Order order = new Order(userId, orderId, valor);
					
					String topic = "ECOMMERCE_NEW_ORDER";
					fraudDetectorDispatcher.send(topic, orderId, order);

					topic = "ECOMMERCE_SEND_EMAIL";
					emailkafkaDispatcher.send(topic, orderId, order);

				}
			}
		}
	}
}
