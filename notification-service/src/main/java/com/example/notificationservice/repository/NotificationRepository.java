package com.example.notificationservice.repository;

import com.example.notificationservice.domain.NotificationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends CrudRepository<NotificationEntity,Integer> {

    @Override
    List<NotificationEntity> findAll();
}
