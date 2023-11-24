package com.ezone.specification;

import com.ezone.entity.NewsPostLike;
import com.ezone.form.filter.FilterForm;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class NewsPostLikeSpecification {

    private static final String USER_ID = "userId";
    private static final String POST_ID = "postId";

    //Find by User Id and Post Id (1 result)
    public static Specification<NewsPostLike> buildWhereUserAndPost(FilterForm form) {
        if (form == null) {
            return null;
        }

        Specification<NewsPostLike> whereUserId = new CustomSpecification(USER_ID, form.getUserId());
        Specification<NewsPostLike> wherePostId = new CustomSpecification(POST_ID, form.getPostId());

        return Specification.where(whereUserId).and(wherePostId);
    }

    //Find by Post Id (many result)
    public static Specification<NewsPostLike> buildWherePost(FilterForm form) {
        if (form == null) {
            return null;
        }

        Specification<NewsPostLike> wherePostCommentId = new CustomSpecification(POST_ID, form.getPostCommentId());

        return Specification.where(wherePostCommentId);
    }


    @AllArgsConstructor
    static class CustomSpecification implements Specification<NewsPostLike> {
        private String key;
        private Object value;

        @Override
        public Predicate toPredicate(Root<NewsPostLike> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            if (value == null || value == "") {
                return null;
            }

            switch (key) {
                case USER_ID:
                    return criteriaBuilder.equal(root.get("user").get("id"), value);
                case POST_ID:
                    return criteriaBuilder.equal(root.get("post").get("id"), value);
                default:
                    return null;
            }
        }
    }
}
