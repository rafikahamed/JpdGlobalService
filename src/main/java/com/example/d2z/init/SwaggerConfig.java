package com.example.d2z.init;

import static springfox.documentation.builders.PathSelectors.regex;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {  
	
//    @Bean
//    public Docket api() { 
//        return new Docket(DocumentationType.SWAGGER_2)  
//          .select()                                  
//          .apis(RequestHandlerSelectors.basePackage("com.example.controller"))              
//          .paths(PathSelectors.any())                          
//          .build();                                           
//    }
	
	@Bean
	public Docket merchantApi() {
	        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().paths(regex("/v1/logistics/.*"))
	                .build();
	}
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Logistics Dashboard").description("Logistics report")
                .contact("RAFIK AHAMED").version("2.0").build();
    }
}