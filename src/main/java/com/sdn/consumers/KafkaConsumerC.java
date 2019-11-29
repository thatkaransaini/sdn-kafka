package com.sdn.consumers;

import java.util.Arrays;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdn.entity.JsonEntity;

public class KafkaConsumerC {
	@Autowired
	Environment env;
	
	
	@KafkaListener(id = "consumerC", topics = {"${kafka.topic}"} )
	 public void onMessage(ConsumerRecord<?, ?> record) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonEntity json =	objectMapper.readValue(record.value().toString(), JsonEntity.class);
		
			RestTemplate restclient = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
            HttpEntity<JsonEntity> httpjson = new HttpEntity<>(json,headers);
			 try{
				 restclient.postForObject(env.getProperty("web.api"), httpjson, JsonEntity.class);
			 }
			 catch(Exception E) {
				 System.out.println("Consumer C: Just hit the API");
			 }
	
}

}
