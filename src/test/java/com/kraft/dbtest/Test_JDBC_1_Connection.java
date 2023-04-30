package com.kraft.dbtest;

import java.sql.*;

public class Test_JDBC_1_Connection {

    public static void main(String[] args) {
        //MS SQL Server için
//        String url ="jdbc:sqlserver://localhost:1433;DatabaseName=Batch3SQL;encrypt=true;trustServerCertificate=true";
//        String userName = "sa";
//        String password = "111111";
//        String query = "select * from employees";

        //PostgreSQL için
        String url="jdbc:postgresql://localhost:5432/postgres"; //sonundaki postgres database in simi, eğer busra ise busra yaz.
        String userName = "postgres"; //bu sabit
        String password = "Bsr25546.";
        String query = "select * from employees";

        //port açık olmalı; açmak için
        // cmd ye git ve    netstat -a -n    yaz. --> MsSql de port kapalı gelir indirdiğinde, o yüzden açman lazım.
        //postgre de default olarak açık geliyor.

        try {
            Connection connection = DriverManager.getConnection(url,userName,password);
            System.out.println("Connection OK");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            resultSet.next(); // boolean değer döner. eğer true ise alt satıra geçer. False ise alt satır yoktur anlamına gelir.
            System.out.println(resultSet.getString(1));
            System.out.println(resultSet.getString(2));

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Connection error" + e.getMessage());
        }




    }

}
