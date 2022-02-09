package com.ofir.coupons.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ofir.coupons.authorization.TokenManager;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ExpiredTokensRemoval {

	private final TokenManager tokenManager;
	
	@Scheduled(fixedRate = 1000 * 60)
	public void removeExpiredTokens() {
		tokenManager.removeExpiredTokens();
	}
}
