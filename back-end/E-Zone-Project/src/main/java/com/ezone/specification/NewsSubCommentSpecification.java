package com.ezone.specification;

import com.ezone.entity.NewsComment;
import com.ezone.entity.NewsSubComment;
import com.ezone.form.filter.FilterForm;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class NewsSubCommentSpecification {

    private static final String POST_COMMENT_ID = "postCommentId";

    public static Specification<NewsSubComment> buildWhere(FilterForm form) {
        if (form == null) {
            return null;
        }

        Specification<NewsSubComment> wherePostCommentId = new CustomSpecification(POST_COMMENT_ID, form.getPostCommentId());

        return Specification.where(wherePostCommentId);
    }


    @AllArgsConstructor
    static class CustomSpecification implements Specification<NewsSubComment> {
        private String key;
        private Object value;

        @Override
        public Predicate toPredicate(Root<NewsSubComment> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            if (value == null || value == "") {
                return null;
            }

            switch (key) {
                case POST_COMMENT_ID:
                    return criteriaBuilder.equal(root.get("postComment").get("id"), value);
                default:
                    return null;
            }
        }
    }
}
