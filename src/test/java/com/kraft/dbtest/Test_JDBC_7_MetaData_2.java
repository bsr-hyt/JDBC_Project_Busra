package com.kraft.dbtest;

import java.sql.*;

public class Test_JDBC_7_MetaData_2 {

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/postgres"; //sonundaki postgres database in simi, eğer busra ise busra yaz.
        String userName = "postgres"; //bu sabit
        String password = "Bsr25546.";
        String query = "select * from customer";


        try (Connection connection = DriverManager.getConnection(url,userName,password);
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
             ResultSet resultSet = statement.executeQuery(query))
        {
            ResultSetMetaData metaData = resultSet.getMetaData();

            //column sayısı;
            int columnCount = metaData.getColumnCount();
            System.out.println("columnCount = " + columnCount);
            System.out.println("------------------");

            //column countu kullanarak ilk satırın bütün değerlerini ekrana yazdır;
            for (int i = 1; i <= columnCount; i++) {
                resultSet.absolute(1);
                System.out.print(resultSet.getString(i) + " | ");
            }
            System.out.println();
            System.out.println("------------------");

            //column countu kullanarak bütün satırları yazdır;
            //way 1
            resultSet.beforeFirst(); //önce başa dönmek için.
            while(resultSet.next()){
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(resultSet.getString(i) + " | ");
                }
                System.out.println();
            }
            System.out.println("------------------");

            //column countu kullanarak bütün satırların bütün değerlerini yazdır.. ama en üstte column namleri olsun.
            for (int i = 1; i <= columnCount ; i++) {
                System.out.print(metaData.getColumnName(i) + " | ");
            }
            System.out.println();

            resultSet.beforeFirst(); //önce başa dönelim.
            while(resultSet.next()){
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(resultSet.getString(i) + " | ");
                }
                System.out.println();
            }


        } catch (SQLException e) {
            System.out.print("Connection Error" + e.getMessage());
        }
    }

}
