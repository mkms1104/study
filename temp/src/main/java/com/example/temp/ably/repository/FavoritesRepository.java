package com.example.temp.ably.repository;

import com.example.temp.ably.domain.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoritesRepository extends JpaRepository<Favorites, Long> {
    List<Favorites> findByFavoritesDeskId(Long favoritesDeskId);
    void deleteByFavoritesDeskIdAndProductsId(Long favoritesDeskId, Long productsId);
}
