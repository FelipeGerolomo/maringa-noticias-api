package br.com.maringanoticias.utils.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	private static final String RESOURCE_ID = "modelo-server-rest-api";
	private static final String SECURED_READ_SCOPE = "#oauth2.hasScope('read')";
	private static final String SECURED_WRITE_SCOPE = "#oauth2.hasScope('write')";
	private static final String[] AUTH_WHITELIST = { 
			"/swagger-resources",
			"/swagger-resources/**",
			"/v2/api-docs",
			"/swagger-ui.html",
			"/webjars/**",
			"/usuario/**",
			"/perfil/**",
			"/news/**",
			"/source-news/**",
			"/weather/**",
			"/news-log/**",
			"/device-log/**",
			"/crawler/**",
	};

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.cors()
			.and().headers().frameOptions().disable()
			.and().requestMatchers()
			.and().authorizeRequests()
			.antMatchers(AUTH_WHITELIST).permitAll()
				.antMatchers(HttpMethod.PUT, "/**").access(SECURED_WRITE_SCOPE)
				.antMatchers(HttpMethod.POST, "/**").access(SECURED_WRITE_SCOPE)
				.antMatchers(HttpMethod.DELETE, "/**").access(SECURED_WRITE_SCOPE)
				.anyRequest().access(SECURED_READ_SCOPE);

	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(RESOURCE_ID);
	}
}
