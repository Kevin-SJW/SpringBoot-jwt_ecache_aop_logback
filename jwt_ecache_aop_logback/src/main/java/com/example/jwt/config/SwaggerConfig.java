package com.example.jwt.config;

import com.example.jwt.domain.system.*;
import com.fasterxml.classmate.GenericType;
import com.fasterxml.jackson.datatype.hibernate5.PersistentCollectionSerializer;
import com.google.common.base.Predicate;
import io.swagger.annotations.ApiOperation;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.config.annotation.*;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRules;
//import springfox.documentation.schema.WildcardType;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;
import java.util.Map;
import com.fasterxml.classmate.TypeResolver;

import static org.glassfish.jersey.internal.guava.Lists.newArrayList;
import static springfox.documentation.schema.AlternateTypeRules.newRule;

@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig implements WebMvcConfigurer {

    @Autowired
    private TypeResolver typeResolver;


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        WebMvcConfigurer.super.addViewControllers(registry);
        registry.addViewController("/").setViewName("home");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
//                .alternateTypeRules(
//                        AlternateTypeRules.newRule(
//                                typeResolver.resolve(Department.class,
//                                        typeResolver.resolve(Position.class, Menu.class, typeResolver.resolve(Authority.class, User.class))),
//                                typeResolver.resolve(Department.class, WildcardType.class), Ordered.HIGHEST_PRECEDENCE))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.jwt.rest.system"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Jwt后台接口文档")
                .description("")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }
}



