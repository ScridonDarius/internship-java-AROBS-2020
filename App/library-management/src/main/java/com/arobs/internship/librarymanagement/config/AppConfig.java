package com.arobs.internship.librarymanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ComponentScan("com.arobs.internship")
@PropertySource("classpath:application.properties")
public class AppConfig {

//    @Autowired
//    Environment environment;
//
//    private final String URL = "url";
//    private final String USER = "dbuser";
//    private final String DRIVER = "driver";
//    private final String PASSWORD = "dbpassword";
//
//    //@Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
//        driverManagerDataSource.setUrl(environment.getProperty(URL));
//        driverManagerDataSource.setUsername(environment.getProperty(USER));
//        driverManagerDataSource.setPassword(environment.getProperty(PASSWORD));
//        driverManagerDataSource.setDriverClassName(environment.getProperty(DRIVER));
//        return driverManagerDataSource;
//    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.arobs.internship.librarymanagement.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
