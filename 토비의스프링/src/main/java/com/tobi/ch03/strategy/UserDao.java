package com.tobi.ch03.strategy;

import com.tobi.User;
import com.tobi.ch03.jdbcContext.JdbcContext;

import java.sql.SQLException;

public class UserDao {
    private JdbcContext jdbcContext;

    public void setJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }
//    @Deprecated // JdbcContext 클래스로 분리
//    public void jdbcContextWithStatementStrategy(StatementStrategy stmt) throws SQLException {
//        Connection c = null;
//        PreparedStatement ps = null;
//
//        try {
//            c = dataSource.getConnection();
//
//            ps = stmt.makePreparedStatement(c);
//
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            throw e;
//        } finally {
//            if(ps != null) {
//                try {
//                    ps.close();
//                } catch (SQLException e) {
//
//                }
//            }
//            if(c != null) {
//                try {
//                    c.close();
//                } catch (SQLException e) {
//
//                }
//            }
//        }
//    }

    public void add(final User user) throws SQLException {
//        class AddStatement implements StatementStrategy {
//            @Override
//            public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
//                PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?,?,?)");
//                ps.setString(1, user.getId());
//                ps.setString(2, user.getName());
//                ps.setString(3, user.getPassword());
//                return ps;
//            }
//        }
//
//        StatementStrategy st = new AddStatement();

// ========================================================================================== //

//        this.jdbcContext.workWithStatementStrategy(new StatementStrategy() {
//            @Override
//            public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
//                PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?,?,?)");
//                ps.setString(1, user.getId());
//                ps.setString(2, user.getName());
//                ps.setString(3, user.getPassword());
//                return ps;
//            }
//        });

// ========================================================================================== //
        // TODO add method
    }

    public void deleteAll() throws SQLException {

//        class DeleteAllStatement implements StatementStrategy {
//            @Override
//            public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
//                return c.prepareStatement("delete from users");
//            }
//        }
//
//        StatementStrategy st = new DeleteAllStatement();


// ========================================================================================== //

//        this.jdbcContext.workWithStatementStrategy(new StatementStrategy() {
//            @Override
//            public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
//                return c.prepareStatement("delete from users");
//            }
//        });

// ========================================================================================== //

        this.jdbcContext.executeSql("delete from users");
    }
}
