package com.ezone.repository;

import com.ezone.entity.NewsComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface INewsCommentRepository extends JpaRepository<NewsComment, Integer>, JpaSpecificationExecutor<NewsComment> {
    List<NewsComment> findByPostId(int postId);
}
