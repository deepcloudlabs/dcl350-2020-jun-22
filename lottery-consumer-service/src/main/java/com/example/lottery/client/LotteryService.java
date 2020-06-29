package com.example.lottery.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.lottery.dto.LotteryResponse;

@FeignClient(name = "lottery", fallback = FallbackLotteryService.class)
public interface LotteryService {
	@GetMapping("/lottery/api/v1/numbers")
	LotteryResponse getLotteryNumbers(@RequestParam int column);
}
