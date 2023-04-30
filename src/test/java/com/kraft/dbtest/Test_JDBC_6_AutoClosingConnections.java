package com.kraft.dbtest;

import java.sql.*;

public class Test_JDBC_6_AutoClosingConnections {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/postgres"; //sonundaki postgres database in simi, eğer busra ise busra yaz.
        String userName = "postgres"; //bu sabit
        String password = "Bsr25546.";
        String query = "select * from customer";


        try (Connection connection = DriverManager.getConnection(url,userName,password);
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
             ResultSet resultSet = statement.executeQuery(query);)
        {
            resultSet.next();
            System.out.println(resultSet.getString(2));

        } catch (SQLException e) {
            System.out.println("Connection Error" + e.getMessage());
        }
    }
}
