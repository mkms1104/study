package com.tobi.ch05;

import com.tobi.User;

public interface UserLevelUpgradedPolicy {
    boolean canUpgradeLevel(User user);
    void upgradeLevel(User user);
}
