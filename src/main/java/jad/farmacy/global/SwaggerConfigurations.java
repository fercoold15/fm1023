package jad.farmacy.global;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;

public class SwaggerConfigurations {

    public static final String AUTOR_RESPONSE_EXAMPLE = "{\n" +
            "  \"autores\": [\n" +
            "    {\n" +
            "      \"id\": 1,\n" +
            "      \"nombre\": \"Nombre Autor 1\",\n" +
            "      \"activo\": true\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 2,\n" +
            "      \"nombre\": \"Nombre Autor 2\",\n" +
            "      \"activo\": true\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    @Operation(
        description = "Endpoint para obtener todos los autores de la base de datos",
        summary = "El endpoint va a buscar todos los autores en la base de datos que estén activos y cumplan con x",
        responses = {
            @ApiResponse(
                description = "Éxito",
                responseCode = "200",
                content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                        name = "Ejemplo de respuesta",
                        value = AUTOR_RESPONSE_EXAMPLE
                    )
                )
            )
        }
    )
    public void getAll() {
        // Esta anotación solo actúa como contenedor para la configuración de Swagger
    }

    @Operation(
        description = "Endpoint para obtener un autor por ID",
        summary = "El endpoint va a buscar un autor específico en la base de datos por su ID",
        responses = {
            @ApiResponse(
                description = "Éxito",
                responseCode = "200",
                content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                        name = "Ejemplo de respuesta",
                        value = AUTOR_RESPONSE_EXAMPLE
                    )
                )
            )
        }
    )
    public void obtenerAutorPorId() {
        // Esta anotación solo actúa como contenedor para la configuración de Swagger
    }
}
