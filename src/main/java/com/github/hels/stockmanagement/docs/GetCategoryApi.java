package com.github.hels.stockmanagement.docs;

import com.github.hels.stockmanagement.docs.schemas.GetCategorySchema;
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
@Operation(summary = "Consulta de uma categoria cadastrada")
@RequestBody(
        description = "Consultar categoria cadastrada",
        content = @Content(
                schema = @Schema(implementation = GetCategorySchema.Response.class),
                mediaType = MediaType.APPLICATION_JSON_VALUE
        )
)
@ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "Categoria consultada",
                content = @Content(
                        schema = @Schema(implementation = GetCategorySchema.Response.class),
                        mediaType = MediaType.APPLICATION_JSON_VALUE
                )
        ),
        @ApiResponse(
                responseCode = "204",
                description = "Categoria inexistente",
                content = @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE
                )
        )
})
public @interface GetCategoryApi {
}
