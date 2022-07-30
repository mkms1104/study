package com.tobi;

import com.tobi.ch05.Level;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class User {
    private static final int BASIC = 1;
    private static final int SILVER = 2;
    private static final int GOLD = 3;

    private String id;
    private String name;
    private String password;
    private Level level;
    private int login;
    private int recommend;

    public void upgradeLevel() {
        Level nextLevel = this.level.nextLevel();
        if(nextLevel == null) throw new IllegalStateException(this.level + "은 업그레이드가 불가능합니다.");
        else this.level = nextLevel;
    }
}
