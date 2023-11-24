package com.ezone.specification;

import com.ezone.entity.NewsCommentLike;
import com.ezone.form.filter.FilterForm;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class NewsCommentLikeSpecification {
    private static final String USER_ID = "userId";
    private static final String POST_COMMENT_ID = "postCommentId";

    //Find by User Id and Comment Id (1 result)
    public static Specification<NewsCommentLike> buildWhereUserAndComment(FilterForm form) {
        if (form == null) {
            return null;
        }

        Specification<NewsCommentLike> whereUserId = new CustomSpecification(USER_ID, form.getUserId());
        Specification<NewsCommentLike> wherePostCommentId = new CustomSpecification(POST_COMMENT_ID, form.getPostCommentId());


        return Specification.where(whereUserId).and(wherePostCommentId);
    }

    //Find by Comment Id (many result)
    public static Specification<NewsCommentLike> buildWhereComment(FilterForm form) {
        if (form == null) {
            return null;
        }

        Specification<NewsCommentLike> wherePostCommentId = new CustomSpecification(POST_COMMENT_ID, form.getPostCommentId());


        return Specification.where(wherePostCommentId);
    }


    @AllArgsConstructor
    static class CustomSpecification implements Specification<NewsCommentLike> {
        private String key;
        private Object value;

        @Override
        public Predicate toPredicate(Root<NewsCommentLike> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            if (value == null || value == "") {
                return null;
            }

            switch (key) {
                case USER_ID:
                    return criteriaBuilder.equal(root.get("user").get("id"), value);
                case POST_COMMENT_ID:
                    return criteriaBuilder.equal(root.get("postComment").get("id"), value);
                default:
                    return null;
            }
        }
    }
}
