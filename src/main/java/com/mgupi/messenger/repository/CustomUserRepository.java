package com.mgupi.messenger.repository;

import com.mgupi.messenger.entity.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomUserRepository extends JpaRepository<CustomUser, Integer> {
    CustomUser findByUsername(String username);
}
