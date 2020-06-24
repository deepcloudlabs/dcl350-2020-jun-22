package com.example.lottery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Configuration
public class RetryTemplateConfig {
	@Bean
	public RetryTemplate retryTemplate() {
		RetryTemplate retryTemplate = new RetryTemplate();

		ExponentialBackOffPolicy exponentialBackOffPolicy = new ExponentialBackOffPolicy();
		exponentialBackOffPolicy.setInitialInterval(1000L);
		retryTemplate.setBackOffPolicy(exponentialBackOffPolicy);

		SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
		retryPolicy.setMaxAttempts(3);
		retryTemplate.setRetryPolicy(retryPolicy);
		return retryTemplate;
	}
}
