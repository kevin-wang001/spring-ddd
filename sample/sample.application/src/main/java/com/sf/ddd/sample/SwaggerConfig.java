package com.sf.ddd.sample;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 类SwaggerConfig.java的实现描述：TODO 类实现描述
 * 
 * @author lizhuo 2020年7月22日 下午7:04:26
 */
@Profile({ "dev", "sit" })
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createOrderServiceApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("OrderService").apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.basePackage("com.sf.ddd.order.controller")).paths(PathSelectors.any()).build();
    }

    @Bean
    public Docket createUserServiceApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("UserService").apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.basePackage("com.sf.ddd.user.controller")).paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("DDD Sample API Document").description("DDD Sample API Document").termsOfServiceUrl("http://localhost:9003/ddd-sample/swagger-ui.html").version("1.0").build();
    }
}
