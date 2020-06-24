package com.example.lottery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.lottery.dto.LotteryResponse;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Service
public class LotteryConsumerServiceLoadBalancing3 {

	private static final String LOTTERY_SERVICE_URL = "//lottery/lottery/api/v1/numbers?column=10";
	@Autowired
	private RestTemplate restTemplate;

	@Scheduled(fixedRate = 3_000)
	public void callLotteryService() {
		LotteryResponse response = restTemplate.getForObject(LOTTERY_SERVICE_URL, LotteryResponse.class);
		System.out.println(response);
	}
}
