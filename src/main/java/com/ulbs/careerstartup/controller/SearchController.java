package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.apidoc.SearchApiDoc;
import com.ulbs.careerstartup.service.SearchService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/search")
@Tag(name = "Search", description = "The Search API")
public class SearchController implements SearchApiDoc {

    private SearchService searchService;

    @GetMapping("/{endpoint}")
    public <T> T search(@PathVariable String endpoint, @RequestParam Map<String, String> criteria) {
        return searchService.search(endpoint, criteria);
    }
}
