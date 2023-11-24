package com.ezone.specification;

import com.ezone.entity.NewsPost;
import com.ezone.form.filter.FilterForm;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class NewsPostSpecification {
    private static final String TITLE = "title";
    private static final String TOPIC_ID = "topicId";
    private static final String USER_ID = "userId";

    public static Specification<NewsPost> buildWhere(FilterForm form) {
        if (form == null) {
            return null;
        }

        Specification<NewsPost> whereTitle = new CustomSpecification(TITLE, form.getSearch());
        Specification<NewsPost> whereTopicId = new CustomSpecification(TOPIC_ID, form.getTopicId());
        Specification<NewsPost> whereUserId = new CustomSpecification(USER_ID, form.getUserId());

        return Specification.where(whereTitle.and(whereTopicId).and(whereUserId));
    }


    @AllArgsConstructor
    static class CustomSpecification implements Specification<NewsPost> {
        private String key;
        private Object value;

        @Override
        public Predicate toPredicate(Root<NewsPost> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            if (value == null || value == "") {
                return null;
            }

            switch (key) {
                case TITLE:
                    return criteriaBuilder.like(root.get("title"), "%" + value.toString().trim() + "%");
                case TOPIC_ID:
                    return criteriaBuilder.equal(root.get("topic").get("id"), value);
                case USER_ID:
                    return criteriaBuilder.equal(root.get("user").get("id"), value);
                default:
                    return null;
            }
        }
    }
}
