package com.luv2code.ecommerce.config;

import java.beans.Customizer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;

import com.okta.spring.boot.oauth.Okta;

@Configuration
public class SecurityConfiguration {

	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		// protect endpoint /api/orders
		// -> lambda exprsn using spring security lambda dsl
		http.authorizeHttpRequests(requests -> requests.
				requestMatchers("/api/orders/**")// apply to this current path																			// and sub paths
				.authenticated().anyRequest().permitAll())
				.oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer
						.jwt(org.springframework.security.config.Customizer.withDefaults()));

		// .jwt to provide jwt encoded token support        

		// + CORS filters
		http.cors(org.springframework.security.config.Customizer.withDefaults());

		// + content negotiation strategy
		//setup contnt negotiation strtgy to support okta sending back friendly response.      
		http.setSharedObject(ContentNegotiationStrategy.class, new HeaderContentNegotiationStrategy());

		// + non-empty response body for 401 (more friendly), force a non empty response
		// body for 401 to make response more friendly
		
		Okta.configureResourceServer401ResponseBody(http);

		// we are not using Cookies for session tracking >> disable CSRF
		http.csrf(AbstractHttpConfigurer::disable);
        http.csrf(csrf -> csrf.disable());

		
		return http.build();// http security supports the builder design pattern, hence build it to return
							// the instance
	
		
		
		//protect end point /api/orders
//		   
//		   System.out.println("reached 1");
//		   
//		   http.authorizeHttpRequests(configurer->
//		               configurer
//		                  .requestMatchers("/api/orders/**")
//		                  .authenticated()
//		                     .anyRequest().permitAll())
//		         .oauth2ResourceServer()
//		         .jwt();
//		   
//		   http.cors();
//		   
//		   //add content negotiation strategy
//		   
//		   http.setSharedObject(ContentNegotiationStrategy.class, new HeaderContentNegotiationStrategy());
//		   
//		   //force a non-empty response body for 401's to make the response user friendly
//		 
//		   Okta.configureResourceServer401ResponseBody(http);
//		   
//		   System.out.println("reached 2");
//		   http.csrf(AbstractHttpConfigurer::disable);
//		   
//		   return http.build();
	
	}

}
