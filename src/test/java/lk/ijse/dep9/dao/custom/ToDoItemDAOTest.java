package lk.ijse.dep9.dao.custom;

import com.github.javafaker.Faker;
import lk.ijse.dep9.dao.DAOFactory;
import lk.ijse.dep9.dao.DAOTypes;
import lk.ijse.dep9.dao.SuperDAO;
import lk.ijse.dep9.entity.ToDoItem;
import lk.ijse.dep9.entity.util.StatusType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ToDoItemDAOTest {
    private ToDoItemDAO toDoItemDAO;
    private Connection connection;

    @BeforeEach
    void setUp() throws SQLException, IOException {

        connection = DriverManager.getConnection("jdbc:h2:mem:");
        String dbScript=new String(this.getClass().getResourceAsStream("/db-script.sql").readAllBytes());
        connection.createStatement().execute(dbScript);
        toDoItemDAO = DAOFactory.getInstance().getDAO(connection, DAOTypes.TO_DO_ITEM);

    }

    @AfterEach
    void tearDown() throws SQLException {
     connection.close();
    }

    @Test
    void count() {
        long count = toDoItemDAO.count();
        assertEquals(0,count);
    }

    @Test
    void save() {
        ToDoItem expectedItem = new ToDoItem("Kasun", "Compete test", StatusType.NOT_DONE);
        ToDoItem actualItem = toDoItemDAO.save(expectedItem);

        assertEquals(1,toDoItemDAO.count());

    }
}