package com.example.temp.ably.api;

import com.example.temp.ably.domain.FavoritesDesk;
import com.example.temp.ably.domain.Products;
import com.example.temp.ably.domain.Users;
import com.example.temp.ably.service.FavoriteDeskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class FavoritesDeskApi {
    private final FavoriteDeskService favoriteDeskService;

    // 유저의 모든 찜 서랍 조회
    @GetMapping("api/v1/ably/{users_id}/favorites-desk")
    public List<FavoritesDesk> searchFavoritesDesks(@PathVariable("users_id") Long usersId) {
        return favoriteDeskService.searchFavoritesDesks(usersId);
    }

    // 찜 서랍 생성
    @PostMapping("api/v1/ably/{users_id}/favoritesDesk")
    public Long createFavoritesDesk(@PathVariable("users_id") Long usersId, String name) {
        return favoriteDeskService.createFavoritesDesk(usersId, name);
    }

    // 찜 서랍 삭제
    @DeleteMapping("api/v1/ably/{users_id}/favoritesDesk{favorites_desk_id}")
    public void deleteFavoritesDesk(@PathVariable("users_id") Long usersId,
                                    @PathVariable("favorites_desk_id") Long favoritesDeskId) {

        favoriteDeskService.deleteFavoritesDesk(usersId, favoritesDeskId);
    }

    // 특정 찜 서랍의 모든 상품 조회
    @GetMapping("api/v1/ably/{users_id}/favorites-desk/{favorites_desk_id}/products")
    public List<Products> searchProducts(@PathVariable("users_id") Long usersId,
                                   @PathVariable("favorites_desk_id") Long favoritesDeskId) {

        return favoriteDeskService.searchProducts(favoritesDeskId);
    }

    // 특정 찜 서랍에 상품 추가
    @PostMapping("api/v1/ably/{users_id}/favorites-desk/{favorites_desk_id}/products/{products_id}")
    public Long addProducts(
            @PathVariable("users_id") Long usersId,
            @PathVariable("favorites_desk_id") Long favoritesDeskId,
            @PathVariable("products_id") Long productsId) {

        return favoriteDeskService.addProducts(favoritesDeskId, productsId);
    }

    // 찜 서랍 내 특정 상품 삭제
    @DeleteMapping("api/v1/ably/{users_id}/favorites-desk/{favorites_desk_id}/products/{products_id}")
    public void deleteProducts(
            @PathVariable("users_id") Long usersId,
            @PathVariable("favorites_desk_id") Long favoritesDeskId,
            @PathVariable("products_id") Long productsId) {

        favoriteDeskService.deleteProducts(favoritesDeskId, productsId);
    }


}
