package com.ecc.aipao98.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author sunyc
 * @create 2022-07-07 8:48
 */
    /**
     * Swagger2配置类
     * 在与spring boot集成时，放在与Application.java同级的目录下。
     * 通过@Configuration注解，让Spring来加载该类配置。
     * 再通过@EnableSwagger2注解来启用Swagger2。
     */
@Configuration
@EnableSwagger2
public class Swagger12 {
        //读取配置文件中的enable，true为显示，false为隐藏


        @Bean
        public Docket createDocke(){
            return new Docket(DocumentationType.SWAGGER_2)
                    //进入swagger-ui的信息
                    .apiInfo(apiInfo())
                    .select()
                    //暴露所有controller类的所在的包路径
                    .apis(RequestHandlerSelectors.basePackage("com.ecc.aipao98.controller"))
                    .paths(PathSelectors.any())
                    .build()
                    .enable(true);
        }
        //进入swagger-ui的信息
        private ApiInfo apiInfo(){
            return new ApiInfoBuilder()
                    //该项目的名字
                    .title("Spring Boot 2.x教程")
                    //该项目的描述
                    .description("spring boot2.x 描述")
                    .version("1.0")
                    .build();
        }



}
