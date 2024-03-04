package com.ulbs.careerstartup.apidoc;

import com.ulbs.careerstartup.dto.BibliographyDTO;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface BibliographyApiDoc {

    @Operation(summary = "Find all bibliographies", tags = {"Bibliography"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful retrieval",
                            content = @Content(schema = @Schema(implementation = BibliographyDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Not Found"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    ResponseEntity<Collection<BibliographyDTO>> findAllBibliography();

    @Operation(summary = "Find bibliographies by skill ids",tags = {"Bibliography"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful retrieval",
                            content = @Content(schema = @Schema(implementation = BibliographyDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Not Found"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    Collection<BibliographyDTO> findBibliographiesBySkillIds(@Parameter(description = "List of skills ids", required = true) @PathVariable List<UUID> skillIds);

    @Operation(summary = "Find bibliographies by criteria",tags = {"Bibliography"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful retrieval",
                            content = @Content(schema = @Schema(implementation = BibliographyDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Not Found"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    ResponseEntity<Collection<BibliographyDTO>> findBibliographiesByCriteria(@Parameter(description = "List of search criteria", required = true) @RequestParam List<SearchCriteria> criteria);

    @Operation(summary = "Save bibliography", tags = {"Bibliography"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful save",
                            content = @Content(schema = @Schema(implementation = BibliographyDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Not Found"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    ResponseEntity<BibliographyDTO> saveBibliography(@Parameter(description = "Bibliography that will be saved", required = true) @RequestBody BibliographyDTO bibliographyDTO);

    @Operation(summary = "Update bibliography", tags = {"Bibliography"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful update",
                            content = @Content(schema = @Schema(implementation = BibliographyDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Not Found"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    ResponseEntity<BibliographyDTO> updateBibliography(@Parameter(description = "Bibliography that will be updated", required = true) @RequestBody BibliographyDTO bibliographyDTO);

    @Operation(summary = "Delete bibliography", tags = {"Bibliography"},
            responses = {
                    @ApiResponse(responseCode = "204", description = "Successful delete",
                            content = @Content(schema = @Schema(implementation = BibliographyDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Not Found"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    ResponseEntity<Void> deleteBibliography(@Parameter(description = "Bibliography that will be deleted", required = true) @RequestBody BibliographyDTO bibliographyDTO);
}
