package alura.com.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public class SendEmailService {

	public static void main(String[] args) {

		SendEmailService sendEmailService = new SendEmailService();

		try (KafkaService<Order> kafkaService = new KafkaService<>(SendEmailService.class.getSimpleName(),
				"ECOMMERCE_SEND_EMAIL", sendEmailService::parse, Order.class)) {
			kafkaService.run();
		}

	}

	private void parse(ConsumerRecord<String, Order> record) {
		System.out.println("---------------------------------");
		System.out.println("Email enviado com sucesso para o usuário " + record.key());
		System.out.println("Número do pedido : " + record.value().getOrderId());
		System.out.println("Valor do pedido: " + record.value().getValor());
		System.out.println("---------------------------------");

	}

}
