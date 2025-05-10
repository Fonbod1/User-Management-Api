package com.KF48.User.Management.UserConfiguration.SwaggerConfiguration.Securityconfiguration1;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfiguration {
    @Bean
    OpenAPI myConfiguration(){
        return new OpenAPI().info(new Info()
                .title("USER MANAGEMENT API")
                .description("This API makes use of the JWT spring security to authenticate a user using  email and password")
                .version("Version 1.00")
        );
    }
}
