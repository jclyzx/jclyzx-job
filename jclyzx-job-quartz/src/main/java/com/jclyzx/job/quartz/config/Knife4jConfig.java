package com.jclyzx.job.quartz.config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Component
@EnableSwagger2WebMvc
public class Knife4jConfig {
	
	private final OpenApiExtensionResolver openApiExtensionResolver;

    @Autowired
    public Knife4jConfig(OpenApiExtensionResolver openApiExtensionResolver) {
        this.openApiExtensionResolver = openApiExtensionResolver;
    }
    
    @Bean(value = "defaultApi1")
    public Docket createRestApi() {
    		String groupName = "定时任务分组";
	    	List<Predicate<RequestHandler>> selector = new ArrayList<>();
			selector.add(RequestHandlerSelectors.basePackage("com.jclyzx.job.quartz.job.datasource.controller"));
	        return new Docket(DocumentationType.SWAGGER_2)
	                .useDefaultResponseMessages(false)
	                .apiInfo(apiInfo())
	                .groupName(groupName)
	                .select()
	                .apis(new OrPredicate<RequestHandler>(selector))
	                .paths(PathSelectors.any())
	                .build()
	                .extensions(openApiExtensionResolver.buildExtensions(groupName));

    }
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("定时任务")
                .description("定时任务1")
                .contact(new Contact("jclyzx", null, null))
                .version("1.0")
                .build();
    }

    private static class OrPredicate<T> implements Predicate<T>, Serializable {
    	private static final long serialVersionUID = 0;
		private final List<? extends Predicate<? super T>> components;
		private OrPredicate(List<? extends Predicate<? super T>> components) {this.components = components;}
		@Override
		public boolean test(T t) {
			for (int i = 0; i < components.size(); i++) 
				if (components.get(i).test(t)) return true;
			return false;
		}
		@Override
		public int hashCode() {return components.hashCode() + 0x053c91cf;}
		@Override
		public boolean equals(Object obj) {
			if (obj instanceof OrPredicate) return components.equals(((OrPredicate<?>) obj).components);
			return false;
		}
	}
}
