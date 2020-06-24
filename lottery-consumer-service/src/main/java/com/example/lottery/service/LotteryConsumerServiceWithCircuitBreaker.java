package com.example.lottery.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.lottery.dto.LotteryResponse;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Service
public class LotteryConsumerServiceWithCircuitBreaker {
	// integration client -> http -> Rest API (server) 
	//                               tomcat -> http/websocket (synchronous)               -> (thread pool)
	                                                                // capacity
	// reactive system               jetty -> http/websokcet  (event queue, asynchronous) -> (thread pool)
	// Fixed Thread Pool   -> fixed (30) 
	// Cached Thread Pool  -> variable 
	@CircuitBreaker(name="lotterySrvCircuitBreaker", 
			fallbackMethod = "getLotteryNumbersFallback")
	public LotteryResponse getNumbers() {
		System.err.println("Calling lottery service from LotteryClientWithCircuitBreaker...");
		RestTemplate rt = new RestTemplate();
		return rt.getForObject("http://localhost:8001/lottery/api/v1/numbers?column=10", LotteryResponse.class);
	}
	
	public LotteryResponse getLotteryNumbersFallback(Exception e) {
		System.err.println("getLotteryNumbersFallback()");
		return new LotteryResponse(IntStream.range(0, 5).mapToObj(i -> List.of(1,2,3,4,5,6)).collect(Collectors.toList()));
	}
}