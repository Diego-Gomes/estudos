package alura.com.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public class FraudDetectorService {
	/** * @param args */
	public static void main(String[] args) {
		FraudDetectorService fraudDetectorService = new FraudDetectorService();
		try (KafkaService<Order> kafkaService = new KafkaService<>(FraudDetectorService.class.getSimpleName(),
				"ECOMMERCE_NEW_ORDER", fraudDetectorService::parse, Order.class)) {
			kafkaService.run();
		}
	}

	private void parse(ConsumerRecord<String, Order> record) {
		
		
		System.out.println("-----------------------------");
		System.out.println("Fraud processada para o usu�rio: " + record.key());
		System.out.println("N�mero do pedido : " + record.value().getOrderId());
		System.out.println("Valor do pedido: " + record.value().getValor());
		
	}
}