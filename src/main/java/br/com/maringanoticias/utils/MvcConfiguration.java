package br.com.maringanoticias.utils;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.util.Arrays;


@EnableWebMvc
@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = { "br.com.maringanoticias.domain.*" })
@EnableJpaRepositories("br.com.maringanoticias.domain.*")
@ComponentScan({ "br.com.maringanoticias.*" })
@EnableJpaAuditing
public class MvcConfiguration implements WebMvcConfigurer {

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(false);
		config.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
		config.setAllowedOrigins(Arrays.asList("*"));
		config.setMaxAge(3600L);

		source.registerCorsConfiguration("/**", config);

		return new CorsFilter(source);
	}

	private DatabasePopulator databasePopulator() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		// populator.addScript(schemaScript);
		return populator;
	}

	// @Value("classpath:import.sql")
	// private Resource importScript;

	@Bean
	public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
		DataSourceInitializer initializer = new DataSourceInitializer();
		initializer.setDataSource(dataSource);
		initializer.setDatabasePopulator(databasePopulator());
		return initializer;
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
		return modelMapper;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**")
				.addResourceLocations("classpath:/static/");
	}
}
