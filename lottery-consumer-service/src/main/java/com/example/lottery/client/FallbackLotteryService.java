package com.example.lottery.client;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.example.lottery.dto.LotteryResponse;

@Service
public class FallbackLotteryService implements LotteryService {

	@Override
	public LotteryResponse getLotteryNumbers(int column) {
		return new LotteryResponse(
				IntStream.range(0, column).mapToObj(i -> List.of(1, 2, 3, 4, 5, 6)).collect(Collectors.toList()));
	}

}
