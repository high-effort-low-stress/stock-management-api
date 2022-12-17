package com.github.hels.stockmanagement.docs;

import com.github.hels.stockmanagement.docs.schemas.CreateProductSchema;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;

import java.lang.annotation.*;

@Inherited
@ErrorsDescription
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "Criação de um novo produto")
@RequestBody(
        description = "Dados a cadastrar",
        content = @Content(
                schema = @Schema(implementation = CreateProductSchema.Request.class),
                mediaType = MediaType.APPLICATION_JSON_VALUE
        )
)
@ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "Cadastrado com sucesso",
                content = @Content(
                        schema = @Schema(implementation = CreateProductSchema.Response.class),
                        mediaType = MediaType.APPLICATION_JSON_VALUE
                )
        )
})
public @interface CreateProductApi {
}
