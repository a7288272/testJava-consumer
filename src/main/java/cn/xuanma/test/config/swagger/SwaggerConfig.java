package cn.xuanma.test.config.swagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: wangshuan
 * @Date: 2022-9-8 10:34:22
 * @Description:
 */
@EnableSwagger2
@Configuration
@EnableConfigurationProperties(SwaggerPropertics.class)
@ConditionalOnProperty(prefix = "test.swagger", name = "enable", havingValue = "true")
public class SwaggerConfig {
    @Autowired
    private SwaggerPropertics swaggerPropertics;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .groupName(swaggerPropertics.getTitle())
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(swaggerPropertics.getBasePackage()))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(swaggerPropertics.getTitle())
                .description(swaggerPropertics.getDescription())
                .contact(new Contact("联系人", "test.cn", "test@test.com"))
                .version(swaggerPropertics.getVersion()).build();
    }

    @Bean
    public Docket openApi() {
        return new Docket(DocumentationType.OAS_30)
                .groupName(swaggerPropertics.getTitle() + "对外接口")
                .apiInfo(apiInfo1()).select()
                .apis(RequestHandlerSelectors.basePackage(swaggerPropertics.getBaseApiPackage()))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo1() {
        return new ApiInfoBuilder().title(swaggerPropertics.getTitle() + "对外接口")
                .description(swaggerPropertics.getDescription())
                .contact(new Contact("联系人", "test.cn", "test@test.com"))
                .version(swaggerPropertics.getVersion()).build();
    }
}
