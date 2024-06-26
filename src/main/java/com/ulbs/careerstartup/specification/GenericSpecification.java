package com.ulbs.careerstartup.specification;

import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Builder
public class GenericSpecification<T> implements Specification<T> {
    private List<SearchCriteria> criteriaList;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();

        for (SearchCriteria criteria : criteriaList) {
            switch (criteria.getOperation()) {
                case ">" -> predicates.add(handleGreaterThanForDifferentDataTypes(criteria, builder, root));
                case "<" -> predicates.add(handleLessThanForDifferentDataTypes(criteria, builder, root));
                case "=" -> predicates.add(handleEqualsForDifferentDataTypes(criteria, builder, root));
                default -> throw new IllegalArgumentException("Invalid operation: " + criteria.getOperation());
            }
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }

    private Predicate handleEqualsForDifferentDataTypes(SearchCriteria criteria, CriteriaBuilder builder, Root<T> root) {
        Predicate predicate;
        String[] keys = criteria.getKey().split("\\.");
        String value = criteria.getValue().toString();
        int length = keys.length;

        if (length > 1) {
            predicate = getPredicateAfterJoin(builder, root, keys, value, length);
        } else {
            predicate = buildPredicate(root.get(criteria.getKey()), value, builder);
        }

        return predicate;
    }

    private Predicate buildPredicate(Path<Object> path, String value, CriteriaBuilder builder) {
        Predicate predicate;

        try {
            predicate = builder.equal(path, UUID.fromString(value));
        } catch (IllegalArgumentException e) {
            try {
                predicate = builder.equal(path, Long.parseLong(value));
            } catch (IllegalArgumentException ex) {
                predicate = builder.like(path.as(String.class), "%" + value + "%");
            }
        }
        return predicate;
    }

    private Predicate handleGreaterThanForDifferentDataTypes(SearchCriteria criteria, CriteriaBuilder builder, Root<T> root) {
        Predicate predicate;
        String[] keys = criteria.getKey().split("\\.");
        String value = criteria.getValue().toString();
        int length = keys.length;

        if (length > 1) {
            predicate = getPredicateGreaterThanAfterJoin(builder, root, keys, value, length);
        } else {
            try {
                predicate = builder.greaterThanOrEqualTo(root.get(criteria.getKey()), Long.parseLong(value));
            } catch (IllegalArgumentException e) {
                predicate = builder.like(root.get(criteria.getKey()), "%" + value + "%");
            }
        }

        return predicate;
    }

    private Predicate getPredicateAfterJoin(CriteriaBuilder builder, Root<T> root, String[] keys, String value, int length) {
        Predicate predicate;
        Join<Object, Object> join = root.join(keys[0]);

        for (int i = 1; i < length - 1; i++) {
            join = join.join(keys[i]);
        }
        predicate = buildPredicate(join.get(keys[length - 1]), value, builder);
        return predicate;
    }

    private Predicate getPredicateLessThanAfterJoin(CriteriaBuilder builder, Root<T> root, String[] keys, String value, int length) {
        Predicate predicate;
        Join<Object, Object> join = root.join(keys[0]);

        for (int i = 1; i < length - 1; i++) {
            join = join.join(keys[i]);
        }
        try {
            predicate = builder.lessThanOrEqualTo(join.get(keys[length - 1]), Long.parseLong(value));
        } catch (IllegalArgumentException ex) {
            predicate = builder.like(join.get(keys[length - 1]).as(String.class), "%" + value + "%");
        }
        return predicate;
    }

    private Predicate getPredicateGreaterThanAfterJoin(CriteriaBuilder builder, Root<T> root, String[] keys, String value, int length) {
        Predicate predicate;
        Join<Object, Object> join = root.join(keys[0]);

        for (int i = 1; i < length - 1; i++) {
            join = join.join(keys[i]);
        }
        try {
            predicate = builder.greaterThanOrEqualTo(join.get(keys[length - 1]), Long.parseLong(value));
        } catch (IllegalArgumentException ex) {
            predicate = builder.like(join.get(keys[length - 1]).as(String.class), "%" + value + "%");
        }
        return predicate;
    }
    private Predicate handleLessThanForDifferentDataTypes(SearchCriteria criteria, CriteriaBuilder builder, Root<T> root) {
        Predicate predicate;
        String[] keys = criteria.getKey().split("\\.");
        String value = criteria.getValue().toString();
        int length = keys.length;

        if (length > 1) {
            predicate = getPredicateLessThanAfterJoin(builder, root, keys, value, length);
        } else {
            try {
                predicate = builder.lessThanOrEqualTo(root.get(criteria.getKey()), Long.parseLong(value));
            } catch (IllegalArgumentException e) {
                predicate = builder.like(root.get(criteria.getKey()), "%" + value + "%");
            }
        }

        return predicate;
    }

}
