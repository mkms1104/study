package com.tobi.ch05;

import com.tobi.User;
import com.tobi.ch03.jdbcTemplate.UserDao;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.util.List;

@Setter
@NoArgsConstructor
public class UserService {
    private UserDao userDao;
    private UserLevelUpgradedPolicy userLevelUpgradedPolicy;
    private PlatformTransactionManager transactionManager;

    public void upgradeLevels() {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            List<User> users = userDao.getAll();
            for (User user : users) {
                if (userLevelUpgradedPolicy.canUpgradeLevel(user)) {
                    userLevelUpgradedPolicy.upgradeLevel(user);
                }
            }
            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
        }
    }

    public void add(User user) {
        if(user.getLevel() == null) user.setLevel(Level.BASIC);
        userDao.add(user);
    }
}
