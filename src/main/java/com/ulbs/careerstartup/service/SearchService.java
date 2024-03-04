package com.ulbs.careerstartup.service;

import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class SearchService {
    private RestTemplate restTemplate;
    private static HttpServletRequest request;

    public List<SearchCriteria> search(String entity, Map<String, String> params) {
        List<SearchCriteria> criteria = parseCriteria(params);
        String url = request.getRequestURL().toString() + entity + "/";
        ParameterizedTypeReference<List<SearchCriteria>> responseType = new ParameterizedTypeReference<>() {
        };
        return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(criteria), responseType).getBody();
    }

    private List<SearchCriteria> parseCriteria(Map<String, String> params) {
        List<SearchCriteria> criteria = new ArrayList<>();
        params.forEach((key, value) -> {
            String operation = "=";
            if (value.startsWith(">") || value.startsWith("<")) {
                operation = value.substring(0, 1);
                value = value.substring(1);
            }
            criteria.add(new SearchCriteria(key, operation, value));
        });
        return criteria;
    }

}
