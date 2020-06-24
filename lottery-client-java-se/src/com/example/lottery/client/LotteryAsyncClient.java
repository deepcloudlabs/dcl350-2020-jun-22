package com.example.lottery.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public class LotteryAsyncClient {

	private static final String URL = "http://localhost:8001/lottery/api/v1/numbers?column=10";

	public static void main(String[] args) throws IOException, InterruptedException {
		var counter = new AtomicInteger(0);
		var client = HttpClient.newHttpClient();
		var request = HttpRequest.newBuilder().uri(URI.create(URL)).header("Accept", "application/json").build();

		var start = System.currentTimeMillis();
		for (var i = 0; i < 1_000; ++i) {
			// asynchronous call
			client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body)
					.thenAccept(numbers -> {
						// System.out.println(Thread.currentThread().getName());
						var value = counter.incrementAndGet();
						if (value == 1_000) {
							var stop = System.currentTimeMillis();
							System.err.println("Duration: " + (stop - start) + " ms.");
						}
					}); // functional programming
			// System.out.println(numbers);
		}
		TimeUnit.SECONDS.sleep(10);
	}

}
