package com.example.lottery.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.lottery.dto.LotteryResponse;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Service
public class LotteryConsumerServiceWithRateLimiter {

	@RateLimiter(name = "lotterySrvLimiter")
	public LotteryResponse getLotteryNumbers() {
		System.err.println("Calling lottery service from LotteryConsumerServiceWithRateLimiter...");
		var rt = new RestTemplate();
		var response = rt.getForObject("http://localhost:8001/lottery/api/v1/numbers?column=10", LotteryResponse.class);
		System.err.println(response);
		return response;
	}
}
