package com.ezone.repository;

import com.ezone.entity.NewsPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface INewsPostRepository extends JpaRepository<NewsPost, Integer>, JpaSpecificationExecutor<NewsPost> {
}
