package com.cashmovie.movielibrary.services;

import com.auth0.AuthenticationController;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.JwkProviderBuilder;
import com.cashmovie.movielibrary.controllers.LogoutController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.io.UnsupportedEncodingException;
@EnableWebSecurity
@Configuration
public class AuthConfig extends WebSecurityConfigurerAdapter {

  private String domain;
  private String clientId;
  private String clientSecret;
  
  @Autowired
  LogoutController logoutController;
  
  public AuthConfig() {
    domain = System.getenv("AUTH0_DOMAIN");
    clientId = System.getenv("AUTH0_CLIENT_ID");
    clientSecret = System.getenv("AUTH0_CLIENT_SECRET");
  }
  
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();
    http
      .authorizeRequests()
      //.antMatchers("/callback", "/login", "/api/videos/home").permitAll()
      //.anyRequest().authenticated()
      .antMatchers("/api/videos/upload").authenticated()
      .antMatchers("/**").permitAll()
      .and()
      .formLogin()
      .loginPage("/login")
      .and()
      .logout()
      .logoutSuccessHandler(logoutController).permitAll();
  }
  
  @Bean
  public AuthenticationController authenticationController() throws UnsupportedEncodingException {
    JwkProvider jwkProvider = new JwkProviderBuilder(domain).build();
    return AuthenticationController.newBuilder(domain, clientId, clientSecret)
                                   .withJwkProvider(jwkProvider)
                                   .build();
  }
  
  public String getDomain() {
    return domain;
  }
  
  public String getClientId() {
    return clientId;
  }
}