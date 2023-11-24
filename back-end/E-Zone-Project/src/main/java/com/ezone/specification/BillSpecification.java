package com.ezone.specification;

import com.ezone.entity.Bill;
import com.ezone.form.filter.FilterForm;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;

public class BillSpecification {
    private static final String ORDER_USER_ID = "orderUserId";
    private static final String USER_ID = "userId";
    private static final String FROM_DATE = "fromDate";
    private static final String TO_DATE = "toDate";

    public static Specification<Bill> buildWhere(FilterForm form) {
        if (form == null) {
            return null;
        }

        Specification<Bill> whereOrderUserId = new CustomSpecification(ORDER_USER_ID, form.getOrderUserId());
        Specification<Bill> whereUserId = new CustomSpecification(USER_ID, form.getUserId());
        Specification<Bill> whereFromDate = new CustomSpecification(FROM_DATE, form.getFromDate());
        Specification<Bill> whereToDate = new CustomSpecification(TO_DATE, form.getToDate());

        return Specification.where(whereOrderUserId).and(whereUserId).and(whereFromDate.and(whereToDate));
    }


    @AllArgsConstructor
    static class CustomSpecification implements Specification<Bill> {
        private String key;
        private Object value;

        @Override
        public Predicate toPredicate(Root<Bill> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            if (value == null || value == "") {
                return null;
            }

            switch (key) {
                case ORDER_USER_ID:
                    return criteriaBuilder.equal(root.get("order").get("user").get("id"), value);
                case USER_ID:
                    return criteriaBuilder.equal(root.get("user").get("id"), value);
                case FROM_DATE:
                    return criteriaBuilder.lessThanOrEqualTo(root.get("createdDate").as(Date.class), (Date) value);
                case TO_DATE:
                    return criteriaBuilder.greaterThanOrEqualTo(root.get("createdDate").as(Date.class), (Date) value);
                default:
                    return null;
            }
        }
    }
}
