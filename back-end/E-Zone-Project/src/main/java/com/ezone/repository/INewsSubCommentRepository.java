package com.ezone.repository;

import com.ezone.entity.NewsSubComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface INewsSubCommentRepository extends JpaRepository<NewsSubComment, Integer>, JpaSpecificationExecutor<NewsSubComment> {
}
