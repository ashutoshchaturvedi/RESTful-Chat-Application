/*package com.cirtual.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cirtual.entity.User;
import com.cirtual.repository.UserRepository;

@Configuration
class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

  @Autowired
  UserRepository accountRepository;

  @Override
  public void init(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService());
  }

  @Bean
  UserDetailsService userDetailsService() {
    return new UserDetailsService() {

      @Override
      public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = accountRepository.findByEmail(email);
        if(user != null) {
        	System.out.println("user is not null");
        	System.out.println(user.getPassword());
        	return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), true, true, true, true,
                    AuthorityUtils.createAuthorityList("USER"));
        } else {
          throw new UsernameNotFoundException("could not find the user '"
                  + email + "'");
        }
      }
    };
  }
}*/