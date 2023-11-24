package com.ezone.repository;

import com.ezone.entity.NewsPostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface INewsPostLikeRepository extends JpaRepository<NewsPostLike, Integer>, JpaSpecificationExecutor<NewsPostLike> {
}
