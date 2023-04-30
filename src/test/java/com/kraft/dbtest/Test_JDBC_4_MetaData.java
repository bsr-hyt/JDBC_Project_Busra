package com.kraft.dbtest;

import java.sql.*;

public class Test_JDBC_4_MetaData {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/postgres"; //sonundaki postgres database in simi, eğer busra ise busra yaz.
        String userName = "postgres"; //bu sabit
        String password = "Bsr25546.";
        String query = "select * from customer";

        try {
            Connection connection = DriverManager.getConnection(url,userName,password);
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery(query);

            ResultSetMetaData metaData = resultSet.getMetaData();

            //column sayısını almak için;
            int columnCount = metaData.getColumnCount();
            System.out.println("columnCount = " + columnCount);
            System.out.println("---------------");

            //column adlarını almak için;
            System.out.println("metaData.getColumnName(1) = " + metaData.getColumnName(1));
            System.out.println("metaData.getColumnName(2) = " + metaData.getColumnName(2));
            System.out.println("---------------");

            //tüm column adlarını al;
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                System.out.println(i + ". column name: "+metaData.getColumnName(i));
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Connection Error" + e.getMessage());
        }

    }

}
