package com.ulbs.careerstartup.service;

import com.ulbs.careerstartup.constant.ServiceEnum;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.ulbs.careerstartup.constant.Constants.FIND_BY_CRITERIA;

@Service
@AllArgsConstructor
public class SearchService {

    private static final String CRITERIA_REGEX = "(?=[><])|(?<=[><])";
    private static final int CRITERIA_PARTS = 3;
    private final ApplicationContext context;

    public <T> T search(String endpoint, Map<String, String> criteria) {
        return callMethodByName(ServiceEnum.getByValue(endpoint).toString(), parseCriteria(criteria));
    }

    private List<SearchCriteria> parseCriteria(Map<String, String> criteriaMap) {
        List<SearchCriteria> criteriaList = new ArrayList<>();

        for (Map.Entry<String, String> entry : criteriaMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if ((key.contains(">") || key.contains("<")) && value.isEmpty()) {
                String[] parts = key.split(CRITERIA_REGEX, 3);
                if (parts.length == CRITERIA_PARTS) {
                    String field = parts[0];
                    String operator = parts[1];
                    String criterionValue = parts[2];
                    criteriaList.add(new SearchCriteria(field, operator, criterionValue));
                }
            } else if(!value.isEmpty()) {
                criteriaList.add(new SearchCriteria(key, "=", value));
            }
        }

        return criteriaList;
    }

    @SneakyThrows
    @SuppressWarnings("unchecked")
    public <T> T callMethodByName(String className, List<SearchCriteria> criteria) {
        Object serviceBean = context.getBean(Class.forName(className));
        Method method = serviceBean.getClass().getMethod(FIND_BY_CRITERIA, List.class);
        return (T) method.invoke(serviceBean, criteria);
    }
}
