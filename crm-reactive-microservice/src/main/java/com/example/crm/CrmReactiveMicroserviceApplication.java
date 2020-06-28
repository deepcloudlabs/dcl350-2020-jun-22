package com.example.crm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

import com.example.crm.repository.CustomerReactiveRepository;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@SpringBootApplication
@EnableWebFlux
@EnableReactiveMongoRepositories
public class CrmReactiveMicroserviceApplication implements ApplicationRunner {
	@Autowired private CustomerReactiveRepository customerReactiveRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CrmReactiveMicroserviceApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		customerReactiveRepository.findAllFlux(PageRequest.of(0, 10))
		                 .subscribe(System.err::println);
		
	}

}
