package com.example.temp.ably.service;

import com.example.temp.ably.domain.Favorites;
import com.example.temp.ably.domain.FavoritesDesk;
import com.example.temp.ably.domain.Products;
import com.example.temp.ably.domain.Users;
import com.example.temp.ably.repository.FavoritesDeskRepository;
import com.example.temp.ably.repository.FavoritesRepository;
import com.example.temp.ably.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class FavoriteDeskService {
    private final FavoritesDeskRepository favoritesDeskRepository;
    private final FavoritesRepository favoritesRepository;
    private final ProductsRepository productsRepository;

    public List<FavoritesDesk> searchFavoritesDesks(Long usersId) {
        return favoritesDeskRepository.findByUsersId(usersId);
    }

    @Transactional
    public Long createFavoritesDesk(Long usersId, String name) {
        FavoritesDesk favoritesDesk = new FavoritesDesk();
        favoritesDesk.setName(name);
        favoritesDesk.setUsersId(usersId);
        FavoritesDesk saved = favoritesDeskRepository.save(favoritesDesk);
        return saved.getId();
    }

    @Transactional
    public void deleteFavoritesDesk(Long usersId, Long favoritesDeskId) {
        Optional<FavoritesDesk> optionalFavoritesDesk = favoritesDeskRepository.findById(favoritesDeskId);
        FavoritesDesk findFavoritesDesk = optionalFavoritesDesk.orElseThrow();
        Assert.isTrue(Objects.equals(usersId, findFavoritesDesk.getUsersId()), "올바르지 않은 userId");
        favoritesDeskRepository.deleteById(favoritesDeskId);
    }

    public List<Products> searchProducts(Long favoritesDeskId) {
        List<Favorites> favorites = favoritesRepository.findByFavoritesDeskId(favoritesDeskId);
        List<Long> productIds = favorites.stream().map(o -> o.getProductsId()).collect(Collectors.toList());
        return productsRepository.findAllById(productIds);
    }

    @Transactional
    public Long addProducts(Long favoritesDeskId, Long productsId) {
        Favorites favorites = new Favorites();
        favorites.setFavoritesDeskId(favoritesDeskId);
        favorites.setProductsId(productsId);
        Favorites saved = favoritesRepository.save(favorites);
        return saved.getId();
    }

    @Transactional
    public void deleteProducts(Long favoritesDeskId, Long productsId) {
        favoritesRepository.deleteByFavoritesDeskIdAndProductsId(favoritesDeskId, productsId);
    }
}
