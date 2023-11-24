package com.ezone.specification;

import com.ezone.entity.NewsComment;
import com.ezone.form.filter.FilterForm;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class NewsCommentSpecification {
    private static final String POST_ID = "postId";

    public static Specification<NewsComment> buildWhere(FilterForm form) {
        if (form == null) {
            return null;
        }

        Specification<NewsComment> wherePostId = new CustomSpecification(POST_ID, form.getPostId());

        return Specification.where(wherePostId);
    }


    @AllArgsConstructor
    static class CustomSpecification implements Specification<NewsComment> {
        private String key;
        private Object value;

        @Override
        public Predicate toPredicate(Root<NewsComment> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            if (value == null || value == "") {
                return null;
            }

            switch (key) {
                case POST_ID:
                    return criteriaBuilder.equal(root.get("post").get("id"), value);
                default:
                    return null;
            }
        }
    }
}
