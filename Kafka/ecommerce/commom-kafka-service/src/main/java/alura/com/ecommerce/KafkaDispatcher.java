package alura.com.ecommerce;

import java.io.Closeable;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

class KafkaDispatcher<T> implements Closeable {

	KafkaProducer<String, T> producer;

	void send(String topic, String key, T value) throws InterruptedException, ExecutionException {

		producer = new KafkaProducer<String, T>(properties());

		ProducerRecord<String, T> record = new ProducerRecord<>(topic, key, value);
		Callback confirmacaoDoEnvio = new Callback() {

			public void onCompletion(RecordMetadata data, Exception ex) {

				if (ex != null) {
					System.out.println(ex.getMessage());
					ex.printStackTrace();
					return;
				}
				
				System.out.println("---------------------");
				System.out.println(data.offset());
				

			}
		};

		producer.send(record, confirmacaoDoEnvio).get();

	}

	Properties properties() {

		Properties properties = new Properties();

		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "diego-ubunto:9092");
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, GsonSerializer.class.getName());

		return properties;
	}

	@Override
	public void close() {
		this.producer.close();
	}

}
