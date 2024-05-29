package com.ulbs.careerstartup.apidoc;

import com.ulbs.careerstartup.dto.ReviewDTO;
import com.ulbs.careerstartup.exception.ErrorResponse;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface ReviewApiDoc {

    @Operation(summary = "Find all reviews", tags = {"Review"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful retrieval",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = ReviewDTO.class)))),
                    @ApiResponse(responseCode = "400", description = "Bad Request",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorResponse.class))}),
                    @ApiResponse(responseCode = "401", description = "Unauthorized",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorResponse.class))}),
                    @ApiResponse(responseCode = "403", description = "Forbidden",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorResponse.class))}),
                    @ApiResponse(responseCode = "404", description = "Not Found",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorResponse.class))}),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorResponse.class))})
            }
    )
    Collection<ReviewDTO> findAllReviews();

    @Operation(summary = "Find review by id", tags = {"Review"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful retrieval",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ReviewDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorResponse.class))}),
                    @ApiResponse(responseCode = "401", description = "Unauthorized",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorResponse.class))}),
                    @ApiResponse(responseCode = "403", description = "Forbidden",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorResponse.class))}),
                    @ApiResponse(responseCode = "404", description = "Not Found",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorResponse.class))}),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorResponse.class))})
            }
    )
    ReviewDTO findReviewById(@Parameter(description = "Id of the review that will be received", required = true) @Valid @PathVariable UUID id);

    @Operation(summary = "Find review by criteria", tags = {"Review"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful retrieval",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = ReviewDTO.class)))),
                    @ApiResponse(responseCode = "400", description = "Bad Request",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorResponse.class))}),
                    @ApiResponse(responseCode = "401", description = "Unauthorized",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorResponse.class))}),
                    @ApiResponse(responseCode = "403", description = "Forbidden",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorResponse.class))}),
                    @ApiResponse(responseCode = "404", description = "Not Found",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorResponse.class))}),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorResponse.class))})
            },
            security = @SecurityRequirement(name = "oauth2")
    )
    Collection<ReviewDTO> findByCriteria(@Parameter(description = "List of search criteria", required = true) @Valid @RequestBody List<SearchCriteria> criteria);

    @Operation(summary = "Save review", tags = {"Review"},
            responses = {
                    @ApiResponse(responseCode = "201", description = "Successfully created",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ReviewDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorResponse.class))}),
                    @ApiResponse(responseCode = "401", description = "Unauthorized",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorResponse.class))}),
                    @ApiResponse(responseCode = "403", description = "Forbidden",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorResponse.class))}),
                    @ApiResponse(responseCode = "404", description = "Not Found",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorResponse.class))}),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorResponse.class))})
            },
            security = @SecurityRequirement(name = "oauth2")
    )
    ReviewDTO saveReview(@Parameter(description = "Review that will be saved", required = true) @Valid @RequestBody ReviewDTO reviewDTO);

    @Operation(summary = "Update review", tags = {"Review"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully updated",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ReviewDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorResponse.class))}),
                    @ApiResponse(responseCode = "401", description = "Unauthorized",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorResponse.class))}),
                    @ApiResponse(responseCode = "403", description = "Forbidden",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorResponse.class))}),
                    @ApiResponse(responseCode = "404", description = "Not Found",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorResponse.class))}),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorResponse.class))})
            },
            security = @SecurityRequirement(name = "oauth2")
    )
    ReviewDTO updateReview(@Parameter(description = "Review that will be updated", required = true) @Valid @RequestBody ReviewDTO reviewDTO);

    @Operation(summary = "Delete review", tags = {"Review"},
            responses = {
                    @ApiResponse(responseCode = "204", description = "Successfully deleted"),
                    @ApiResponse(responseCode = "400", description = "Bad Request",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorResponse.class))}),
                    @ApiResponse(responseCode = "401", description = "Unauthorized",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorResponse.class))}),
                    @ApiResponse(responseCode = "403", description = "Forbidden",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorResponse.class))}),
                    @ApiResponse(responseCode = "404", description = "Not Found",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorResponse.class))}),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorResponse.class))})
            },
            security = @SecurityRequirement(name = "oauth2")
    )
    void deleteReview(@Parameter(description = "Review that will be deleted", required = true) @Valid @RequestBody ReviewDTO reviewDTO);
}
