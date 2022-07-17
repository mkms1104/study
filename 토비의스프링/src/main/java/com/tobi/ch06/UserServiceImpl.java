package com.tobi.ch06;

import com.tobi.User;
import com.tobi.ch03.jdbcTemplate.UserDao;
import com.tobi.ch05.Level;
import com.tobi.ch05.UserLevelUpgradedPolicy;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@NoArgsConstructor
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private UserLevelUpgradedPolicy userLevelUpgradedPolicy;

    @Override
    public void upgradeLevels() {
        List<User> users = userDao.getAll();
        for (User user : users) {
            if (userLevelUpgradedPolicy.canUpgradeLevel(user)) {
                userLevelUpgradedPolicy.upgradeLevel(user);
            }
        }
    }

    @Override
    public void add(User user) {
        if(user.getLevel() == null) user.setLevel(Level.BASIC);
        userDao.add(user);
    }
}
