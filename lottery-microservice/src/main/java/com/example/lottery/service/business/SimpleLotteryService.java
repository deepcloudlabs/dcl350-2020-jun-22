package com.example.lottery.service.business;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import com.example.lottery.service.LotteryService;

// set PATH=c:\DEVEL\stage\opt\curl-7.45.0\bin;%PATH%
// curl localhost:8001/lottery/api/v1/actuator/refresh -X POST -d {} -H "Content-Type: application/json"
@Service
@RefreshScope // Spring Actuator -> REST POST
public class SimpleLotteryService implements LotteryService{
	@Value("${lottery.min}") 
	private int lotteryMin;
	@Value("${lottery.max}") 
	private int lotteryMax;
	@Value("${lottery.size}") 
	private int lotterySize;
	
	public List<Integer> draw() {
		return ThreadLocalRandom.current().ints(lotteryMin, lotteryMax)
				                .distinct()
				                .limit(lotterySize)
				                .sorted()
				                .boxed()
				                .collect(Collectors.toList());
	}
	
	@Override
	public List<List<Integer>> draw(int column) {
		return IntStream.range(0, column)
				        .mapToObj(i -> this.draw())
				        .collect(Collectors.toList());
	}

}
