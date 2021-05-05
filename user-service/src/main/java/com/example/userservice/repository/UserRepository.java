package com.example.userservice.repository;

import com.example.userservice.domain.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserEntity,Integer> {

    @Override
    List<UserEntity> findAll();
}
