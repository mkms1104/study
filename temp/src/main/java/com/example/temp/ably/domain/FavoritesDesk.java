package com.example.temp.ably.domain;

import lombok.Data;

import javax.persistence.*;

// 찜 서랍
@Data
@Entity
@Table(
    uniqueConstraints={
        @UniqueConstraint(
            columnNames={"name", "usersId"}
        )
    }
)
public class FavoritesDesk {
    @Id
    @GeneratedValue
    @Column(name = "favorites_desk_id")
    private Long id;
    private String name;

    private Long usersId;
}
