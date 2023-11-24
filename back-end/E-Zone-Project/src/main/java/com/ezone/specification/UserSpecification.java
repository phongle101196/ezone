package com.ezone.specification;

import com.ezone.entity.Role;
import com.ezone.entity.User;
import com.ezone.form.filter.FilterForm;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class UserSpecification {
    private static final String USERNAME = "username";
    private static final String FULL_NAME = "fullName";
    private static final String ROLE = "role";

    public static Specification<User> buildWhere(FilterForm form) {
        if (form == null) {
            return null;
        }

        Specification<User> whereUsername = new CustomSpecification(USERNAME, form.getSearch());
        Specification<User> whereFullName = new CustomSpecification(FULL_NAME, form.getSearch());
        Specification<User> whereRole = new CustomSpecification(ROLE, form.getRole());

        return Specification.where(whereUsername.or(whereFullName)).and(whereRole);
    }


    @AllArgsConstructor
    static class CustomSpecification implements Specification<User> {
        private String key;
        private Object value;

        @Override
        public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            if (value == null || value == "") {
                return null;
            }

            switch (key) {
                case USERNAME:
                    return criteriaBuilder.like(root.get("username"), "%" + value.toString().trim() + "%");
                case FULL_NAME:
                    return criteriaBuilder.like(root.get("fullName"), "%" + value.toString().trim() + "%");
                case ROLE:
                    return criteriaBuilder.equal(root.get("role"), Role.valueOf(value.toString()));
                default:
                    return null;
            }
        }
    }
}
