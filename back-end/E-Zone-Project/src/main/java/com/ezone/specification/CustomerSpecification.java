package com.ezone.specification;

import com.ezone.entity.Customer;
import com.ezone.form.filter.FilterForm;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class CustomerSpecification {
    private static final String FULL_NAME = "fullName";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final String EMAIL = "email";

    public static Specification<Customer> buildWhere(FilterForm form) {
        if (form == null) {
            return null;
        }

        Specification<Customer> whereFullName = new CustomSpecification(FULL_NAME, form.getSearch());
        Specification<Customer> wherePhoneNumber = new CustomSpecification(PHONE_NUMBER, form.getSearch());
        Specification<Customer> whereEmail = new CustomSpecification(EMAIL, form.getSearch());

        return Specification.where(whereFullName.or(wherePhoneNumber).or(whereEmail));
    }

    @AllArgsConstructor
    static class CustomSpecification implements Specification<Customer> {

        private String key;
        private Object value;

        @Override
        public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            if (value == null || value == "") {
                return null;
            }

            switch (key) {
                case FULL_NAME:
                    return criteriaBuilder.like(root.get("user").get("fullName"), "%" + value.toString().trim() + "%");
                case PHONE_NUMBER:
                    return criteriaBuilder.like(root.get("user").get("phoneNumber"), "%" + value.toString().trim() + "%");
                case EMAIL:
                    return criteriaBuilder.like(root.get("user").get("email"), "%" + value.toString().trim() + "%");
                default:
                    return null;
            }

        }
    }
}
