package alura.com.ecommerce;

import java.io.Closeable;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

class KafkaService<T> implements Closeable{

	private KafkaConsumer<String, T> consumer;
	private ConsumerFunction<T> parse;
	
	KafkaService(String groupID, String topico, ConsumerFunction<T> parse, Class<T> type) {
		this.parse = parse;
		this.consumer = new KafkaConsumer<String, T>(properties(type,groupID));
		consumer.subscribe(Collections.singletonList(topico));
	
	}

	void run() {
		while(true) {
			ConsumerRecords<String, T> records = consumer.poll(Duration.ofMillis(100));
		
			if(!records.isEmpty()) {
				for (ConsumerRecord<String, T> record : records) {
					parse.consume(record);
				}	
			}
		}
	}
	
	private Properties properties(Class<T> type, String groupID) {
		
		Properties properties = new Properties();
			
		properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "diego-ubunto:9092");
		properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, GsonDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupID);
		properties.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "1");
		properties.setProperty(GsonDeserializer.TYPE_CONFIG, type.getName());
		
		return properties ;
	}

	@Override
	public void close(){
			consumer.close();
	}

	
}
