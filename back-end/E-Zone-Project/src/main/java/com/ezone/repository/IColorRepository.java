package com.ezone.repository;

import com.ezone.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IColorRepository extends JpaRepository<Color, Integer>, JpaSpecificationExecutor<Color> {
}
