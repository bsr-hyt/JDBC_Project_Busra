package com.kraft.dbtest;

import java.sql.*;

public class Test_JDBC_2_BasicMethods {

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/postgres"; //sonundaki postgres database in simi, eğer busra ise busra yaz.
        String userName = "postgres"; //bu sabit
        String password = "Bsr25546.";
        String query = "select * from customer";

        try {
            Connection connection = DriverManager.getConnection(url, userName, password);
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery(query);

            //tabloya dahil olmak için;
            resultSet.next();
            //satır numarası almak için ve column indexi ve column adı ile veri almak için
            System.out.println(resultSet.getRow() + " " + resultSet.getString(2));
            resultSet.next();
            System.out.println(resultSet.getRow() + " " + resultSet.getString("firstname"));

//            resultSet.absolute(3); //absolute metodunu kullanabilmen için ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY yazıyoruz.

            System.out.println("------------------------");
            //column name veya databse name için büyük küçük harf duyarlılığı yoktur.
            System.out.println(resultSet.getRow() + " " + resultSet.getString("FIRSTNAME"));

            System.out.println("------------------------");
            resultSet.next();
            //datanın türüne göre uygun metodlar kullanılabilir; getBoolean, getString, getInt..
            int addressId = resultSet.getInt("addressId");
            System.out.println(addressId * 20);

            System.out.println("------------------------");
            //ilk satıra dönmek için;
            resultSet.first();
            System.out.println(resultSet.getRow() + " " + resultSet.getString(2));

            System.out.println("------------------------");
            //son satıra gitmek için;
            resultSet.last();
            System.out.println(resultSet.getRow() + " " + resultSet.getString(2));

            System.out.println("------------------------");
            //belirli bir satıra gitmek için
            resultSet.absolute(2);
            System.out.println(resultSet.getRow() + " " + resultSet.getString(2));

            System.out.println("------------------------");
            //bir önceki satıra gitmek için;
            resultSet.previous();
            System.out.println(resultSet.getRow() + " " + resultSet.getString(2));


            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Connection error: " + e.getMessage());
        }

    }

}
