package com.winner.commons.sample.config;


import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * .
 *
 * @Classname Knife4jOpenApiConfig
 * @Description winner
 * @Version 1.0.0
 * @Author chanlong
 * @Date 2021/3/5 下午1:25
 */
@Configuration
@EnableKnife4j
@EnableSwagger2WebMvc
public class Knife4jConfig {

    @Bean
    public Docket defaultApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                                                            .groupName("v1")
                                                            .select()
                                                            .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                                                            .paths(PathSelectors.any())
                                                            .build();
        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("专属服务 RESTful API")
                                   .description("API 描述")
                                   .version("1.0")
                                   .build();
    }
}
