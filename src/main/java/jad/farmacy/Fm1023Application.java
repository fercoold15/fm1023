package jad.farmacy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;


@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        contact = @Contact(
            name = "Fernando Maldonado",
            email = "fernandomaldonado751@gmail.com",
			url = "https://www.linkedin.com/in/fercoold/"
        ),
        description = "API CREADA PARA FARMACIA PAZ",
        title="API FARMACY",
        version = "0.1.1"
    )
)
@SecurityScheme(
    name = "bearerAuth",
    description = "autenticacion por token",
    type = SecuritySchemeType.HTTP,
    scheme="bearer",
    bearerFormat="JWT",
    in = SecuritySchemeIn.HEADER
)
public class Fm1023Application {

	public static void main(String[] args) {
		SpringApplication.run(Fm1023Application.class, args);
	}

}
