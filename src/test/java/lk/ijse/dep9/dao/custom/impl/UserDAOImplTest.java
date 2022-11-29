package lk.ijse.dep9.dao.custom.impl;

import lk.ijse.dep9.dao.DAOFactory;
import lk.ijse.dep9.dao.DAOTypes;
import lk.ijse.dep9.dao.custom.QueryDAO;
import lk.ijse.dep9.dao.custom.UserDAO;
import lk.ijse.dep9.util.ConnectionUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOImplTest {
    private UserDAO userDAO;
    private Connection connection;
    @BeforeEach
    void setUp() throws SQLException, IOException {
        connection = DriverManager.getConnection("jdbc:h2:mem:");
        String dbScript = new String(getClass().getResourceAsStream("/db-script.sql").readAllBytes());
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


}