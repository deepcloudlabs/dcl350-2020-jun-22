package com.example.lottery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import com.example.lottery.dto.LotteryResponse;

//@Service
public class LotteryConsumerServiceWithRetryTemplate {
	@Autowired
	private RetryTemplate retryTemplate;

	@Scheduled(fixedRate = 12_000)
	public void doSomething() {
		RetryCallback<LotteryResponse, Exception> retryCallback = context -> {
			System.err.println("Calling lottery service from LotteryConsumerServiceWithRetryTemplate...");
			var rt = new RestTemplate();
			var response = rt.getForObject("http://localhost:8001/lottery/api/v1/numbers?column=10",
					LotteryResponse.class);
			System.err.println(response);
			return response;
		};
		RecoveryCallback<LotteryResponse> recoveryCallback = context -> {
			System.err.println("Calling recoveryCallback from LotteryConsumerServiceWithRetryTemplate...");
			return new LotteryResponse(List.of(List.of(5, 10, 15, 20, 25, 30)));
		};
		try {
			var response = retryTemplate.execute(retryCallback, recoveryCallback);
			System.out.println(response);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

}
