package com.game.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger文档的配置类
 */
@Configuration
public class SwaggerConf {

    @Bean
    public Docket getDocket() {

        //创建 ApiInfo对象，为文档的信息进行配置
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("后台接口文档")
                .version("1.0.0")
                .description("后台系统接口文档，里面详细记录了项目后台的接口信息。")
                .build();

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .groupName("race_project")
                .select()
                    .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.game.controller"))
                .build();
    }
}
