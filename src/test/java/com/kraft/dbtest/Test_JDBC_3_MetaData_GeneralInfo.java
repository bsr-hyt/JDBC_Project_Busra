package com.kraft.dbtest;

import java.sql.*;

public class Test_JDBC_3_MetaData_GeneralInfo {

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/postgres"; //sonundaki postgres database in simi, eğer busra ise busra yaz.
        String userName = "postgres"; //bu sabit
        String password = "Bsr25546.";
        String query = "select * from customer";

        try {
            Connection connection = DriverManager.getConnection(url,userName,password);
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery(query);

           //Database ile ilgili genel bilgiler..version,ürünadı,üretici vb.
            DatabaseMetaData metaData = connection.getMetaData();

            System.out.println("metaData.getUserName() = " + metaData.getUserName());
            System.out.println("metaData.getDriverName() = " + metaData.getDriverName());
            System.out.println("metaData.getDriverVersion() = " + metaData.getDriverVersion());
            System.out.println("metaData.getDatabaseProductVersion() = " + metaData.getDatabaseProductVersion());
            System.out.println("metaData.getDatabaseProductName() = " + metaData.getDatabaseProductName());


            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Connection Error" + e.getMessage());
        }

    }
}
