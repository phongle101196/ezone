package com.ezone.specification;

import com.ezone.entity.Order;
import com.ezone.entity.OrderStatus;
import com.ezone.form.filter.FilterForm;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;

public class OrderSpecification {
    private static final String USER_ID = "userId";
    private static final String EMAIL = "email";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final String STATUS = "status";
    private static final String FROM_DATE = "fromDate";
    private static final String TO_DATE = "toDate";

    public static Specification<Order> buildWhere(FilterForm form) {
        if (form == null) {
            return null;
        }

        Specification<Order> whereCustomerId = new CustomSpecification(USER_ID, form.getUserId());
        Specification<Order> whereEmail = new CustomSpecification(EMAIL, form.getEmail());
        Specification<Order> wherePhoneNumber = new CustomSpecification(PHONE_NUMBER, form.getPhoneNumber());
        Specification<Order> whereOrderStatus = new CustomSpecification(STATUS, form.getStatus());
        Specification<Order> whereFromDate = new CustomSpecification(FROM_DATE, form.getFromDate());
        Specification<Order> whereToDate = new CustomSpecification(TO_DATE, form.getToDate());

        return Specification.where(whereCustomerId).and(whereEmail).and(wherePhoneNumber).and(whereOrderStatus).and(whereFromDate.and(whereToDate));
    }


    @AllArgsConstructor
    static class CustomSpecification implements Specification<Order> {
        private String key;
        private Object value;

        @Override
        public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            if (value == null || value == "") {
                return null;
            }

            switch (key) {
                case USER_ID:
                    return criteriaBuilder.equal(root.get("user").get("id"), value);
                case EMAIL:
                return criteriaBuilder.equal(root.get("user").get("email"), value);
                case PHONE_NUMBER:
                return criteriaBuilder.equal(root.get("user").get("phoneNumber"), value);
                case STATUS:
                    return criteriaBuilder.equal(root.get("status"), OrderStatus.valueOf(value.toString()));
                case TO_DATE:
                    return criteriaBuilder.lessThanOrEqualTo(root.get("createdDate").as(Date.class), (Date) value);
                case FROM_DATE:
                    return criteriaBuilder.greaterThanOrEqualTo(root.get("createdDate").as(Date.class), (Date) value);
                default:
                    return null;
            }
        }
    }
}
