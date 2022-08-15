package com.example.temp.ably.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Products {
    @Id
    @GeneratedValue
    @Column(name = "products_id")
    private Long id;

    private String thumbnailUrl;
    private String name;
    private Long price;
}
