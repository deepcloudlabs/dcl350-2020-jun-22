package com.example.lottery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.lottery.client.LotteryService;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Service
public class LotteryConsumerServiceLoadBalancing4 {

	@Autowired
	private LotteryService lotteryService;

	@Scheduled(fixedRate = 3_000)
	public void callLotteryService() {
		System.out.println(lotteryService.getLotteryNumbers(5));
	}
}
