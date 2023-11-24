package com.ezone.specification;

import com.ezone.entity.Manufactory;
import com.ezone.form.filter.FilterForm;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ManufactorySpecification {
    private static final String CATEGORY_ID = "categoryId";

    public static Specification<Manufactory> buildWhere(FilterForm form) {
        if (form == null) {
            return null;
        }

        Specification<Manufactory> whereCategoryId = new CustomSpecification(CATEGORY_ID, form.getCategoryId());

        return Specification.where(whereCategoryId);
    }


    @AllArgsConstructor
    static class CustomSpecification implements Specification<Manufactory> {
        private String key;
        private Object value;

        @Override
        public Predicate toPredicate(Root<Manufactory> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            if (value == null || value == "") {
                return null;
            }

            switch (key) {
                case CATEGORY_ID:
                    return criteriaBuilder.equal(root.get("category").get("id"), value);
                default:
                    return null;
            }
        }
    }
}
