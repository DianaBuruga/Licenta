package com.ulbs.careerstartup.apidoc;

import com.ulbs.careerstartup.dto.CourseDTO;
import com.ulbs.careerstartup.exception.ErrorResponse;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface CourseApiDoc {

    @Operation(summary = "Find all courses", tags = {"Course"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful retrieval",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CourseDTO.class))),
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
    Collection<CourseDTO> findAllCourses();

    @Operation(summary = "Find course by id", tags = {"Course"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful retrieval",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CourseDTO.class))),
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
    CourseDTO findCourseById(@Parameter(description = "Id of Course that will be received", required = true) @Valid @PathVariable UUID id);

    @Operation(summary = "Find course by criteria", tags = {"Course"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful retrieval",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CourseDTO.class))),
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
    Collection<CourseDTO> findByCriteria(@Parameter(description = "List of search criteria", required = true) @Valid @RequestParam List<SearchCriteria> criteria);

    @Operation(summary = "Save course", tags = {"Course"},
            responses = {
                    @ApiResponse(responseCode = "201", description = "Successfully created",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CourseDTO.class))),
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
    CourseDTO saveCourse(@Parameter(description = "Course that will be saved", required = true) @Valid @RequestBody CourseDTO courseDTO);

    @Operation(summary = "Update course", tags = {"Course"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully updated",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CourseDTO.class))),
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
    CourseDTO updateCourse(@Parameter(description = "Course that will be updated", required = true) @Valid @RequestBody CourseDTO courseDTO);

    @Operation(summary = "Delete course", tags = {"Course"},
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
            }
    )
    void deleteCourse(@Parameter(description = "Course that will be deleted", required = true) @Valid @RequestBody CourseDTO courseDTO);


}
