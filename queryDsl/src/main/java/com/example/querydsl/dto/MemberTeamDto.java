package com.example.querydsl.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class MemberTeamDto {
    private Long memberId;
    private String username;
    private int age;
    private Long itemId;
    private String teamName;

    @QueryProjection
    public MemberTeamDto(Long memberId, String username, int age, Long itemId, String teamName) {
        this.memberId = memberId;
        this.username = username;
        this.age = age;
        this.itemId = itemId;
        this.teamName = teamName;
    }
}
