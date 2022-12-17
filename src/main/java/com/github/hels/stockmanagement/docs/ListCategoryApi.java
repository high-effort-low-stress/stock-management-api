package com.github.hels.stockmanagement.docs;

import com.github.hels.stockmanagement.docs.schemas.ListCategorySchema;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;

import java.lang.annotation.*;

@Inherited
@ErrorsDescription
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "Listar as categorias cadastradas")
@ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "Categoria consultada",
                content = @Content(
                        schema = @Schema(implementation = ListCategorySchema.Response.class),
                        mediaType = MediaType.APPLICATION_JSON_VALUE
                )
        )
})
public @interface ListCategoryApi {
}
