package com.example.lottery.service;

import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.lottery.dto.LotteryResponse;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.LoadBalancerBuilder;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.Server;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Service
public class LotteryConsumerServiceLoadBalancing2 {
	private static final String LOTTERY_SERVICE_URL = "http://%s:%d/lottery/api/v1/numbers?column=10";
	@Autowired
	private DiscoveryClient discoveryClient;
	private BaseLoadBalancer loadBalancer;
	private RestTemplate restTemplate;

	@PostConstruct
	public void init() {
		var servers = discoveryClient.getInstances("lottery").stream()
				.map(instance -> new Server(instance.getHost(), instance.getPort())).collect(Collectors.toList());
		IRule roundRobinRule = new RoundRobinRule();
		loadBalancer = LoadBalancerBuilder.newBuilder().withRule(roundRobinRule)
				.buildFixedServerListLoadBalancer(servers);
		this.restTemplate = new RestTemplate();
	}

	@Scheduled(fixedRate = 3_000)
	public void callLotteryService() {
		var server = loadBalancer.chooseServer();
		String url = String.format(LOTTERY_SERVICE_URL, server.getHost(), server.getPort());
		LotteryResponse response = restTemplate.getForObject(url, LotteryResponse.class);
		System.out.println(response);
	}
}
