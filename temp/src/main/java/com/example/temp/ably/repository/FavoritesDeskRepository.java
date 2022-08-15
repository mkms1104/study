package com.example.temp.ably.repository;

import com.example.temp.ably.domain.FavoritesDesk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoritesDeskRepository extends JpaRepository<FavoritesDesk, Long> {
    List<FavoritesDesk> findByUsersId(Long usersId);
}
