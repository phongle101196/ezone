package com.ezone.specification;

import com.ezone.entity.ProductComment;
import com.ezone.form.filter.FilterForm;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ProductCommentSpecification {
    private static final String PRODUCT_ID = "productId";

    public static Specification<ProductComment> buildWhere(FilterForm form) {
        if (form == null) {
            return null;
        }

        Specification<ProductComment> wherePostId = new CustomSpecification(PRODUCT_ID, form.getProductId());

        return Specification.where(wherePostId);
    }

    @AllArgsConstructor
    static class CustomSpecification implements Specification<ProductComment> {
        private String key;
        private Object value;

        @Override
        public Predicate toPredicate(Root<ProductComment> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            if (value == null || value == "") {
                return null;
            }

            switch (key) {
                case PRODUCT_ID:
                    return criteriaBuilder.equal(root.get("product").get("id"), Integer.parseInt(value.toString()));
                default:
                    return null;
            }
        }
    }
}
