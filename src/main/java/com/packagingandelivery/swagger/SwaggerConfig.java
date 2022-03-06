package com.packagingandelivery.swagger;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	private static final String DEFAULT_CONTACT = "jijo.aj@cognizant.com";
	@SuppressWarnings("deprecation")
	private static final ApiInfo DEFAULT_API_INFO = new ApiInfo("PackagingAndDelivery Microservice",
			"PackagingAndDelivery Microservice allows the following operations:"
					+ "->The microservice should contain a list of packaging and delivery cost detail ."
					+ "Packaging items -> Integral item , Accessory , Protective sheath . Delivery items"
					+ "-> Integral item , Accessory .The microservice should get the component type and count to determine the packaging and delivery charge",
			"1.0", "Can be invoked from ComponentProcessing Microservice", DEFAULT_CONTACT, "Return order Management",
			"http://localhost/returnordermanagement");
	private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<>();

	static {
		DEFAULT_PRODUCES_AND_CONSUMES.add("application/json");
		DEFAULT_PRODUCES_AND_CONSUMES.add("application/xml");
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(DEFAULT_API_INFO).produces(DEFAULT_PRODUCES_AND_CONSUMES)
				.consumes(DEFAULT_PRODUCES_AND_CONSUMES);
	}
}
