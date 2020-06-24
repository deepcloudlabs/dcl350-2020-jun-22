package com.example.lottery.service;

import java.net.SocketException;
import java.net.SocketTimeoutException;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.lottery.dto.LotteryResponse;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Service
public class StudyRetryStrategy {
	
	@Retryable(value= {SocketTimeoutException.class, SocketException.class},
			maxAttempts = 3, backoff=@Backoff(delay = 3_000))
	public LotteryResponse getNumbers() {
		System.err.println("Calling lottery service from StudyRetryStrategy...");
		RestTemplate rt = new RestTemplate();
		return rt.getForObject("http://localhost:8001/lottery/api/v1/numbers?column=10", LotteryResponse.class);
	}
}
