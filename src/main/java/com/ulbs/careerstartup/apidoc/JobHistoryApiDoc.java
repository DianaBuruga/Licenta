package com.ulbs.careerstartup.apidoc;

import com.ulbs.careerstartup.dto.JobHistoryDTO;
import com.ulbs.careerstartup.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.MalformedURLException;
import java.util.Collection;
import java.util.UUID;

public interface JobHistoryApiDoc {

    @Operation(summary = "Find all job history", tags = {"JobHistory"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful retrieval",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = JobHistoryDTO.class)))),
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
                                    schema = @Schema(implementation = ErrorResponse.class))})}
            ,security = @SecurityRequirement(name = "oauth2")
    )
    Collection<JobHistoryDTO> findAllJobHistories();

    @Operation(summary = "Find job history by id", tags = {"JobHistory"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful retrieval",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = JobHistoryDTO.class))),
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
                                    schema = @Schema(implementation = ErrorResponse.class))})},
            security = @SecurityRequirement(name = "oauth2")
    )
    JobHistoryDTO findJobHistoryById(@PathVariable @Valid UUID id);

    @Operation(summary = "Save job history", tags = {"JobHistory"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful retrieval",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = JobHistoryDTO.class))),
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
                                    schema = @Schema(implementation = ErrorResponse.class))})}
            ,security = @SecurityRequirement(name = "oauth2")
    )
    JobHistoryDTO saveJobHistory(@RequestBody @Valid JobHistoryDTO jobHistoryDTO) throws MalformedURLException;

    @Operation(summary = "Update job history", tags = {"JobHistory"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful retrieval",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = JobHistoryDTO.class))),
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
                                    schema = @Schema(implementation = ErrorResponse.class))})},
            security = @SecurityRequirement(name = "oauth2")
    )
    JobHistoryDTO updateJobHistory(@RequestBody @Valid JobHistoryDTO jobHistoryDTO) throws MalformedURLException;

    @Operation(summary = "Delete job history", tags = {"JobHistory"},
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
    void deleteJobHistory(@PathVariable("id") @Valid UUID id) ;
}
