package com.kraft.dbtest;

import java.sql.*;

public class Test_JDBC_2_BasicMethod_2 {

    public static void main(String[] args) {
        String url="jdbc:postgresql://localhost:5432/postgres"; //sonundaki postgres database in simi, eğer busra ise busra yaz.
        String userName = "postgres"; //bu sabit
        String password = "Bsr25546.";
        String query = "select * from customer";
//        String query = "select * from customer\n" +
//                "order by customerId desc\n" +
//                "limit 3";


        try {
            Connection connection = DriverManager.getConnection(url,userName,password);
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery(query);

            //absolute metodu ile tabloya next ile dahil olmadan istenilen satıra gidilebilir.
            resultSet.absolute(3);
            System.out.println("Current row: "+resultSet.getRow());
            System.out.println("------------------------");

            //üstten tablo dışına çıkma;
            resultSet.beforeFirst();
            System.out.println("Current row: "+resultSet.getRow());
            System.out.println("------------------------");

            //son satıra gitme;
            resultSet.last();
            System.out.println("Current row: "+resultSet.getRow());
            System.out.println("------------------------");

            //3.satıra gitme
            resultSet.absolute(3);
            System.out.println("Current row: "+resultSet.getRow());
            System.out.println("------------------------");

            //alttan tablo dışına çıkma
            resultSet.afterLast();
            System.out.println("Current row: "+resultSet.getRow());
            System.out.println("------------------------");

            //tüm rowların ilk 2 columnu yazdıran kod;
            //way 1
            resultSet.beforeFirst();
            while(resultSet.next()){
                System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
            }
            System.out.println("------------------------");

            //way2
            resultSet.last();
            int row = resultSet.getRow();
            for (int i = 1; i <= row; i++) {
                resultSet.absolute(i);
                System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
            }


            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Connection Error" + e.getMessage());
        }
    }

}
