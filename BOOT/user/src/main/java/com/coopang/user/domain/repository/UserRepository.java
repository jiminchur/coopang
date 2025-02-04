package com.coopang.user.domain.repository;

import com.coopang.user.domain.entity.user.UserEntity;
import com.coopang.user.application.request.UserSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    boolean existsByEmail(String email);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findById(UUID userId);

    Optional<UserEntity> findByUserIdAndIsDeletedFalseAndIsBlockFalse(UUID userId);

    Page<UserEntity> search(UserSearchCondition condition, Pageable pageable);
    List<UserEntity> findUserList(UserSearchCondition condition);

    Page<UserEntity> findAll(Pageable pageable);
}