package lk.ijse.dep9.dao.customl;

import com.github.javafaker.Faker;
import lk.ijse.dep9.dao.DAOFactory;
import lk.ijse.dep9.dao.DAOTypes;
import lk.ijse.dep9.dao.custom.UserDAO;
import lk.ijse.dep9.entity.User;
import lk.ijse.dep9.util.ConnectionUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {
    private Connection connection;
    private UserDAO userDAO;

    @BeforeEach
    void setUp() throws SQLException, IOException {
        connection = DriverManager.getConnection("jdbc:h2:mem:");
        String dbScript =new String(getClass().getResourceAsStream("/db-script.sql").readAllBytes());
        connection.createStatement().execute(dbScript);
        userDAO = DAOFactory.getInstance().getDAO(connection, DAOTypes.USER);

    }

    @AfterEach
    void tearDown() throws SQLException {
        connection.close();
    }

    @Test
    void count() {
        long actualMemberCount = userDAO.count();
        assertEquals(0,actualMemberCount);
    }

    @Test
    void save() {
        User exceptionMember =new User("Chathura","123sa", "Sampath");
        long exceptedCount = userDAO.count()+1;
        User actualUser = userDAO.save(exceptionMember);
        assertEquals(exceptionMember, actualUser);
        assertEquals(1,exceptedCount);

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/user-test-data.csv")
    void deleteById() {

    }

    @Test
    void existsById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void update() {
    }
}