package com.cirtual.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Class to setup the global security configuration parameters.
 * Provides the password encoder BCryptPasswordEncoder
 * @author ashutosh
 *
 */
@Configuration
class SecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}