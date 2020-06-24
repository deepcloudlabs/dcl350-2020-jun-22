package com.example.lottery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.lottery.dto.LotteryResponse;
import com.example.lottery.service.LotteryService;

@RestController
@RequestScope
@RequestMapping("numbers")
@CrossOrigin
public class LotteryRestController {
    @Autowired
	private LotteryService lotteryService;

	// GET http://localhost:8001/lottery/api/v1/numbers?column=10
	@GetMapping(params = {"column"})
	public LotteryResponse getNumbers(@RequestParam int column){
		return new LotteryResponse(lotteryService.draw(column));
	}
}
