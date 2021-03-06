package com.example.lottery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Service
public class SimpleDemoService {
	@Autowired
	private LotteryConsumerServiceWithCircuitBreaker srv;
	@Autowired
	private StudyRetryStrategy retryStrategy;

	@Scheduled(fixedRate = 2_000)
	public void doSomething() {
		try {
			System.out.println(srv.getNumbers());
		} catch (Exception e) {
			System.err.println(
					String.format("Exception (%s) in doSomething(): %s", e.getClass().getName(), e.getMessage()));
		}
	}

	@Scheduled(fixedRate = 5_000)
	public void doSomethingElse() {
		try {
			System.out.println(retryStrategy.getNumbers());
		} catch (Exception e) {
			System.err.println(
					String.format("Exception (%s) in doSomething(): %s", e.getClass().getName(), e.getMessage()));
		}
	}
}
