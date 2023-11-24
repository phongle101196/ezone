package com.ezone.specification;

import com.ezone.entity.CustomerPayment;
import com.ezone.entity.ProductSubComment;
import com.ezone.form.filter.FilterForm;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ProductSubCommentSpecification {
    private static final String COMMENT_ID = "customerId";

    public static Specification<ProductSubComment> buildWhere(FilterForm form) {
        if (form == null) {
            return null;
        }

        Specification<ProductSubComment> whereCommentId = new CustomSpecification(COMMENT_ID, form.getProductCommentId());

        return Specification.where(whereCommentId);
    }


    @AllArgsConstructor
    static class CustomSpecification implements Specification<ProductSubComment> {
        private String key;
        private Object value;

        @Override
        public Predicate toPredicate(Root<ProductSubComment> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            if (value == null || value == "") {
                return null;
            }

            switch (key) {
                case COMMENT_ID:
                    return criteriaBuilder.equal(root.get("productComment").get("id"), Integer.parseInt(value.toString()));
                default:
                    return null;
            }
        }
    }
}
