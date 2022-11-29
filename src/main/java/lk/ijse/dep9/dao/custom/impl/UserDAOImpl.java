package lk.ijse.dep9.dao.custom.impl;

import lk.ijse.dep9.dao.custom.UserDAO;
import lk.ijse.dep9.entity.SuperEntity;
import lk.ijse.dep9.entity.User;

import javax.sql.rowset.serial.SerialStruct;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {

    private final Connection connection;

    public UserDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public long count() {
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT COUNT(user_name) FROM user");
            ResultSet rst = stm.executeQuery();
            rst.next();
            return rst.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(String  userName) {
        try {
            PreparedStatement stm = connection.prepareStatement("DELETE FROM user WHERE user_name = ?");
            stm.setString(1, userName);
            stm.executeUpdate();
        } catch (SQLException e) {
            if (existsById(userName)) throw new RuntimeException(e);
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean existsById(String  userName) {
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT user_name FROM user WHERE user_name = ?");
            stm.setString(1, userName);
            return stm.executeQuery().next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List findAll() {
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM user");
            ResultSet rst = stm.executeQuery();
            List<User> userList = new ArrayList<>();
            while (rst.next()) {
                String userName = rst.getString("user_name");
                String fullName = rst.getString("full_name");
                String password = rst.getString("password");
                int copies = rst.getInt("copies");
                userList.add(new User(userName, fullName, password));
            }
            return userList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Optional findById(String  userName) {
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM user WHERE user_name = ?");
            stm.setString(1, userName);
            ResultSet rst = stm.executeQuery();
            if (rst.next()) {
                String fullName = rst.getString("full_name");
                String password = rst.getString("password");
                return Optional.of(new User(userName, fullName, password));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User save(User user) {
        try {
            PreparedStatement stm = connection.prepareStatement("INSERT INTO book (user_name, full_name, password) VALUES (?, ?, ?)");
            stm.setString(1, user.getUserName());
            stm.setString(2, user.getFullName());
            stm.setString(3, user.getPassword());
            if (stm.executeUpdate() == 1) {
                return user;
            } else {
                throw new SQLException("Failed to save the user");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public User update(User user) {
        try {
            PreparedStatement stm = connection.prepareStatement("UPDATE book SET full_name=?, password=? WHERE user_name=?");
            stm.setString(1, user.getFullName());
            stm.setString(2, user.getPassword());
            stm.setString(3, user.getUserName());
            if (stm.executeUpdate() == 1) {
                return user;
            } else {
                throw new SQLException("Failed to update the user");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
