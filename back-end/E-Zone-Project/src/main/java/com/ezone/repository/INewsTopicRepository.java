package com.ezone.repository;

import com.ezone.entity.NewsTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface INewsTopicRepository extends JpaRepository<NewsTopic, Integer>, JpaSpecificationExecutor<NewsTopic> {
}
