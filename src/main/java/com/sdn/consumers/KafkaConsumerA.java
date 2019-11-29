package com.sdn.consumers;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

public class KafkaConsumerA {

	
	 @KafkaListener(id = "consumer", topics = {"${kafka.topic}"} )
	 public void onMessage(ConsumerRecord<?, ?> record) {
		 
		 System.out.println("Consumer A :" + record.value());
		 
	 }
}
