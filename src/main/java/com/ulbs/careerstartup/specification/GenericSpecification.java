package com.ulbs.careerstartup.specification;

import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Builder
public class GenericSpecification<T> implements Specification<T> {
    private List<SearchCriteria> criteriaList;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        for (SearchCriteria criteria : criteriaList) {
            switch (criteria.getOperation()) {
                case ">":
                    predicates.add(builder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString()));
                    break;
                case "<":
                    predicates.add(builder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString()));
                    break;
                case "=":
                    if (criteria.getValue() instanceof String) {
                        predicates.add(builder.like(root.get(criteria.getKey()), "%" + criteria.getValue().toString() + "%"));
                    } else {
                        predicates.add(builder.equal(root.get(criteria.getKey()), criteria.getValue()));
                    }
                    break;
                default:
                    break;
            }
        }
        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
