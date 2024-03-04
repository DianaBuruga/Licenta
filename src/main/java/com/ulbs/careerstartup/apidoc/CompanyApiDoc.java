package com.ulbs.careerstartup.apidoc;

import com.ulbs.careerstartup.dto.CompanyDTO;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface CompanyApiDoc {

    @Operation(summary = "Find all companies", tags = {"Company"},
        responses = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval",
                content = @Content(schema = @Schema(implementation = CompanyDTO.class))),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "403", description = "Forbidden"),
        @ApiResponse(responseCode = "404", description = "Not Found"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    ResponseEntity<Collection<CompanyDTO>> findAllCompanies();

    @Operation(summary = "Find company by id", tags = {"Company"},
        responses = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval",
                content = @Content(schema = @Schema(implementation = CompanyDTO.class))),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "403", description = "Forbidden"),
        @ApiResponse(responseCode = "404", description = "Not Found"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    ResponseEntity<CompanyDTO> findById(@Parameter(description = "Id of the company that will be received", required = true) @PathVariable UUID id);

    @Operation(summary = "Save company", tags = {"Company"},
        responses = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval",
                content = @Content(schema = @Schema(implementation = CompanyDTO.class))),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "403", description = "Forbidden"),
        @ApiResponse(responseCode = "404", description = "Not Found"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    ResponseEntity<CompanyDTO> saveCompany(@Parameter(description = "Company that will be saved", required = true) CompanyDTO companyDTO);

    @Operation(summary = "Update company", tags = {"Company"},
        responses = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval",
                content = @Content(schema = @Schema(implementation = CompanyDTO.class))),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "403", description = "Forbidden"),
        @ApiResponse(responseCode = "404", description = "Not Found"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    ResponseEntity<CompanyDTO> updateCompany(@Parameter(description = "Company that will be updated", required = true) CompanyDTO companyDTO);

    @Operation(summary = "Delete company", tags = {"Company"},
        responses = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval",
                content = @Content(schema = @Schema(implementation = CompanyDTO.class))),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "403", description = "Forbidden"),
        @ApiResponse(responseCode = "404", description = "Not Found"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    ResponseEntity<Void> deleteCompany(@Parameter(description = "Company that will be deleted", required = true) CompanyDTO companyDTO);

    @Operation(summary = "Find companies by criteria", tags = {"Company"},
        responses = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval",
                content = @Content(schema = @Schema(implementation = CompanyDTO.class))),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "403", description = "Forbidden"),
        @ApiResponse(responseCode = "404", description = "Not Found"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    ResponseEntity<Collection<CompanyDTO>> findCompaniesByCriteria(@Parameter(description = "List of search criteria", required = true) List<SearchCriteria> criteria);
}
