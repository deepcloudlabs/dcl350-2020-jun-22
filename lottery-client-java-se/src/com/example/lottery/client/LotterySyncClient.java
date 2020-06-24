package com.example.lottery.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.stream.IntStream;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public class LotterySyncClient {

	private static final String URL = "http://localhost:8001/lottery/api/v1/numbers?column=10";

	public static void main(String[] args) throws IOException, InterruptedException {
		var client = HttpClient.newHttpClient();
		var request = HttpRequest.newBuilder().uri(URI.create(URL)).header("Accept", "application/json").build();
		var start = System.currentTimeMillis();
		IntStream.range(0, 1_000).parallel().forEach(i -> {
			// synchronous call
			try {
				// System.out.println(Thread.currentThread().getName());
				client.send(request, HttpResponse.BodyHandlers.ofString()).body();
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
			// System.out.println(numbers);
		});
		var stop = System.currentTimeMillis();
		System.err.println("Duration: " + (stop - start) + " ms.");
	}

}
