package br.com.maringanoticias.utils;

import com.fasterxml.classmate.TypeResolver;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

@Configuration
@EnableSwagger2
//@Import(SpringDataRestConfiguration.class)
public class SwaggerConfiguration {

	@Autowired
	private TypeResolver typeResolver;

	//private static final String PATH_MAPPING = "/*";

	private static final String PACKAGE_BASE = "com.gcheck.controller";

	@Value("${info.app.version}")
	private String projectVersion;

	@Value("${info.app.name}")
	private String projectName;

	@Value("${info.app.description}")
	private String projectDescription;

	@Value("${spring.profiles.active}")
	private String activeProfile;

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title(projectName).description(projectDescription + " - Profile: " + activeProfile)
				.version(projectVersion).build();
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.alternateTypeRules(AlternateTypeRules
						.newRule(typeResolver.resolve(Collection.class, Instant.class),
								typeResolver.resolve(Collection.class, Date.class),
								Ordered.HIGHEST_PRECEDENCE)
						)
				.select()
				.apis(RequestHandlerSelectors.basePackage(PACKAGE_BASE))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo())
				.directModelSubstitute(LocalDate.class, String.class).genericModelSubstitutes(ResponseEntity.class)
				.useDefaultResponseMessages(false).globalResponseMessage(RequestMethod.GET, responseMessageForGET())
				.securitySchemes(Arrays.asList(new ApiKey("Token Access", HttpHeaders.AUTHORIZATION, In.HEADER.name())))
				.securityContexts(Arrays.asList(securityContext()));
	}

	private List<ResponseMessage> responseMessageForGET() {
		return new ArrayList<ResponseMessage>() {
			private static final long serialVersionUID = 1L;
			{
				add(new ResponseMessageBuilder().code(500).message("500 message").responseModel(new ModelRef("Error"))
						.build());
				add(new ResponseMessageBuilder().code(403).message("Forbidden!").build());
			}
		};
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.any()).build();
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("read", "write");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference("Token Access", authorizationScopes));
	}
}