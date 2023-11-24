package com.ezone.specification;

import com.ezone.entity.CustomerPayment;
import com.ezone.form.filter.FilterForm;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class CustomerPaymentSpecification {
    private static final String CUSTOMER_ID = "customerId";

    public static Specification<CustomerPayment> buildWhere(FilterForm form) {
        if (form == null) {
            return null;
        }

        Specification<CustomerPayment> whereCustomerId = new CustomSpecification(CUSTOMER_ID, form.getCustomerId());

        return Specification.where(whereCustomerId);
    }


    @AllArgsConstructor
    static class CustomSpecification implements Specification<CustomerPayment> {
        private String key;
        private Object value;

        @Override
        public Predicate toPredicate(Root<CustomerPayment> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            if (value == null || value == "") {
                return null;
            }

            switch (key) {
                case CUSTOMER_ID:
                    return criteriaBuilder.equal(root.get("customer").get("id"), value);
                default:
                    return null;
            }
        }
    }
}
