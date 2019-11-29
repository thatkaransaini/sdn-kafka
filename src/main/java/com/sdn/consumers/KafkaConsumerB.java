package com.sdn.consumers;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdn.entity.JsonEntity;
import com.sdn.repos.ElasticRepo;

public class KafkaConsumerB {
	@Autowired
	private ElasticRepo action;
	@KafkaListener(id = "consumerB", topics = {"${kafka.topic}"} )
	 public void onMessage(ConsumerRecord<?, ?> record) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonEntity json =	objectMapper.readValue(record.value().toString(), JsonEntity.class);
		try{
			action.save(json);
		}
		catch(Exception E) {
			System.out.println("Consumer B : Could not push the data to the Elastic Server! Is it alive?");
		}
		 
	
}
}
