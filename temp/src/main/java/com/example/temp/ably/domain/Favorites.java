package com.example.temp.ably.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Favorites {

    @Id
    @GeneratedValue
    @Column(name = "favorites_id")
    private Long id;

    private Long favoritesDeskId;
    private Long productsId;
}
