package com.example.lottery.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
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
public class LotteryConsumerServiceLoadBalancing1 {
	private static final String LOTTERY_SERVICE_URL = "http://%s:%d/lottery/api/v1/numbers?column=10";

	@Autowired
	private DiscoveryClient discoveryClient;
	private List<ServiceInstance> servers;
	private AtomicInteger counter = new AtomicInteger(0);
	private RestTemplate restTemplate;

	@PostConstruct
	public void loadInstanceList() {
		servers = discoveryClient.getInstances("lottery");
		servers.forEach(System.out::println);
		this.restTemplate = new RestTemplate();
	}

	@Scheduled(fixedRate = 3_000)
	public void callLotteryService() {
		var index = counter.getAndIncrement() % servers.size();
		var server = servers.get(index);
		var url = String.format(LOTTERY_SERVICE_URL, server.getHost(), server.getPort());
		var response = restTemplate.getForObject(url, LotteryResponse.class);
		System.out.println(response);
	}
}
