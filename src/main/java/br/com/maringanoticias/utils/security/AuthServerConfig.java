package br.com.maringanoticias.utils.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.web.filter.CorsFilter;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import(ServerSecurityConfig.class)
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CorsFilter corsFilter;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder oauthClientPasswordEncoder;

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
		endpoints.tokenStore(tokenStore())
				.authenticationManager(authenticationManager)
				.userDetailsService(userDetailsService);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
		oauthServer.tokenKeyAccess("permitAll()")
				.checkTokenAccess("isAuthenticated()")
				.passwordEncoder(oauthClientPasswordEncoder)
				.addTokenEndpointAuthenticationFilter(corsFilter);
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients)
			throws Exception {
		clients.jdbc(dataSource);
	}


	@Bean
	public OAuth2AccessDeniedHandler oauthAccessDeniedHandler() {
		return new OAuth2AccessDeniedHandler();
	}


	@Bean
	public JdbcTokenStore tokenStore() {
		return new JdbcTokenStore(dataSource);
	}
}