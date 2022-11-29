package lk.ijse.dep9.dao.custom.impl;

import lk.ijse.dep9.entity.util.StatusType;
import lk.ijse.dep9.dao.custom.ToDoItemDAO;
import lk.ijse.dep9.dao.exception.ConstraintViolationException;
import lk.ijse.dep9.entity.ToDoItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ToDoItemDAOImpl implements ToDoItemDAO {
    private Connection connection;

    public ToDoItemDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public long count() {
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT COUNT(id) FROM todo_item");
            ResultSet rst = stm.executeQuery();
            rst.next();
            return rst.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    @Override
    public void deleteById(Integer id) throws ConstraintViolationException {
        try {
            PreparedStatement stm = connection.prepareStatement("DELETE FROM todo_item WHERE id=?");
            stm.setInt(1,id);
            stm.executeUpdate();
        } catch (SQLException e) {
            if(existsById(id)) throw new ConstraintViolationException("Item ID still exist within other tables");
            throw new RuntimeException(e);
        }


    }

    @Override
    public boolean existsById(Integer id) {
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM todo_item WHERE id=?");
            stm.setInt(1,id);
            return (stm.executeQuery().next());


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List findAll() {

        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement("SELECT * FROM  todo_item");
            ResultSet rst = stm.executeQuery();
            ArrayList<ToDoItem> itemList = new ArrayList<>();
            while (rst.next()) {
                int id = rst.getInt("id");
                String username = rst.getString("user_name");
                String description = rst.getString("description");
                StatusType status = StatusType.valueOf(rst.getString("status"));
                itemList.add(new ToDoItem(id,username,description,status));
            }
            return itemList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }

    @Override
    public Optional<ToDoItem> findById(Integer id) {
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement("SELECT * FROM todo_item WHERE id=?");
            stm.setInt(1,id);
            ResultSet rst = stm.executeQuery();
            if (rst.next()) {
                String username = rst.getString("user_name");
                String description = rst.getString("description");
                StatusType status = StatusType.valueOf(rst.getString("status"));
                return Optional.of(new ToDoItem(id,username,description,status));

            }else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ToDoItem save(ToDoItem item) throws ConstraintViolationException {
        try {
            PreparedStatement stm = connection.prepareStatement("INSERT INTO `todo_item` (`user_name`, `description`, `status`) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            stm.setString(1,item.getUsername());
            stm.setString(2,item.getDescription());
            stm.setString(3,item.getStatus().toString());
            if(stm.executeUpdate()==1){
                ResultSet keys = stm.getGeneratedKeys();
                keys.next();
                int id = keys.getInt(1);
                System.out.println(id);
                item.setId(id);
                return item;
            }else {
                throw new SQLException("Failed to update the item");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ToDoItem update(ToDoItem item) throws ConstraintViolationException {
        try {
            PreparedStatement stm = connection.prepareStatement("UPDATE todo_item SET user_name=?, description=?, status=? WHERE id=?");
            stm.setString(1,item.getUsername());
            stm.setString(2,item.getDescription());
            stm.setString(3, String.valueOf(item.getStatus()));

            if(stm.executeUpdate()==1){
                return item;
            }else {
                throw new SQLException("Failed to update the item");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
