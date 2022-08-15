package com.example.temp.ably.repository;

import com.example.temp.ably.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
}
