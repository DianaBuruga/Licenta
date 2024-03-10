package com.ulbs.careerstartup.apidoc;

import com.ulbs.careerstartup.constant.ControllerEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public interface SearchApiDoc {

    @Operation(summary = "Search", description = "Search for entities", tags = {"Search"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Search results"),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            })
    <T> T search(@Parameter(description = "Endpoint where to search") @PathVariable ControllerEnum controller, @Parameter(description = "List of search criteria", required = true) @RequestParam Map<String, String> criteria);
}
