package com.example.lottery.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.lottery.dto.LotteryResponse;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Service
public class LotteryConsumerServiceWithBulkHead {
	@Bulkhead(name = "lotterySrvBulkHead", fallbackMethod = "getFallbackEmployees", type = Bulkhead.Type.THREADPOOL)
	public LotteryResponse getNumbers() {
		System.err.println("Calling lottery service from LotteryConsumerServiceWithBulkHead...");
		RestTemplate rt = new RestTemplate();
		return rt.getForObject("http://localhost:8001/lottery/api/v1/numbers?column=10", LotteryResponse.class);
	}

	public LotteryResponse getLotteryNumbersFallback(Exception e) {
		System.err.println("getLotteryNumbersFallback()");
		return new LotteryResponse(
				IntStream.range(0, 5).mapToObj(i -> List.of(10, 20, 25, 30, 40, 45)).collect(Collectors.toList()));
	}
}