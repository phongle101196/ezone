package com.ezone.repository;

import com.ezone.entity.NewsCommentLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface INewsCommentLikeRepository extends JpaRepository<NewsCommentLike, Integer>, JpaSpecificationExecutor<NewsCommentLike> {
}
