package com.kraft.dbtest;

import java.sql.*;

public class Test_JDBC_5_ClosingConnections {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/postgres"; //sonundaki postgres database in simi, eğer busra ise busra yaz.
        String userName = "postgres"; //bu sabit
        String password = "Bsr25546.";
        String query = "select * from customer";

        Connection connection= null;
        Statement statement=null;
        ResultSet resultSet=null;

        try {
            connection = DriverManager.getConnection(url,userName,password);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(query);

            resultSet.next();
            System.out.println(resultSet.getString(2));

        } catch (SQLException e) {
            System.out.println("Connection Error" + e.getMessage());
        }finally {
            //more actiondan seçtik
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }


    }
}
