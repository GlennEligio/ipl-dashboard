package com.glenneligio.ipldashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@SpringBootApplication
public class IplDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(IplDashboardApplication.class, args);
	}

//	@Bean
//	public CorsConfigurationSource corsConfiguration() {
//		CorsConfiguration corsConfig = new CorsConfiguration();
//		corsConfig.applyPermitDefaultValues();
//		corsConfig.addAllowedOriginPattern(CorsConfiguration.ALL);
//		corsConfig.addAllowedHeader(CorsConfiguration.ALL);
//		corsConfig.addAllowedMethod(CorsConfiguration.ALL);
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", corsConfig);
//		return source;
//	}
}
