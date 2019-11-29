package com.sdn.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdn.entity.JsonEntity;
import com.sdn.producer.KafkaProducer;

@RestController
@RequestMapping("/connect")
public class ProducerController {
	@Autowired
	KafkaProducer producer;

	@PostMapping(value="/producer")
	public ResponseEntity<String> registerStudent(@RequestBody JsonEntity json){
		try {
				producer.sendMessage(new ObjectMapper().writeValueAsString(json));
				return new ResponseEntity<>(null,HttpStatus.OK) ;
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
			}

		}
	}

