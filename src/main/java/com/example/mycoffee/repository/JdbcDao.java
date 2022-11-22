package com.example.mycoffee.repository;

import java.sql.*;
import java.util.List;

public class JdbcDao {
    private static Connection dbConnection;

    static {
        try {
            getDbConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public JdbcDao() {
    }

    public static Connection getDbConnection() throws SQLException {
        if (dbConnection == null) {
            String connecitonString = "jdbc:mysql://localhost:3306/coffeevan";
            dbConnection = DriverManager.getConnection(connecitonString, "root", "111111");
        }
        return dbConnection;
    }

    public static ResultSet select(String table) throws SQLException {
        return dbConnection.createStatement().executeQuery("SELECT * FROM " + table);
    }

    public static void deleteById(String table, String idColumnName, String columnValue) throws SQLException{
      PreparedStatement preparedStatement = dbConnection.prepareStatement("delete from "+ table +" where "+idColumnName+" = ?");
      preparedStatement.setInt(1, Integer.parseInt(columnValue));
      preparedStatement.executeUpdate();
    }

}
