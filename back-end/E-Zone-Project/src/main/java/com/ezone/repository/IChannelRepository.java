package com.ezone.repository;

import com.ezone.entity.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IChannelRepository extends JpaRepository<Channel, Integer>, JpaSpecificationExecutor<Channel> {
}
