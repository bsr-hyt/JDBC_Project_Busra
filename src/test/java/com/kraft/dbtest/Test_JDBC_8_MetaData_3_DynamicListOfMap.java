package com.kraft.dbtest;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test_JDBC_8_MetaData_3_DynamicListOfMap {
    String url = "jdbc:postgresql://localhost:5432/postgres"; //sonundaki postgres database in simi, eğer busra ise busra yaz.
    String userName = "postgres"; //bu sabit
    String password = "Bsr25546.";
    String query = "select * from customer";


   @Test
    public void test1(){
       String userName = "postgres"; //bu sabit
       String password = "Bsr25546.";
       String query = "select * from customer";


       try (Connection connection = DriverManager.getConnection(url,userName,password);
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery(query);)
       {
           ResultSetMetaData metaData = resultSet.getMetaData();

           //ilk satırın bilgilerini columname=value olacak şekilde bir mape alalım.
           int columnCount = metaData.getColumnCount();
           Map<String,Object> rowMap1 = new HashMap<>();

           resultSet.next(); //ilk satıra geçtik.

           for (int i = 1; i <= columnCount ; i++) {
               rowMap1.put(metaData.getColumnName(i),resultSet.getString(i));
           }

           System.out.println("rowMap1 = " + rowMap1);
           System.out.println("-------------");

           //n.satırın bilgilerini columname=value olacak şekilde bir mape alalım. n=3 olsun mesela.
           Map <String,Object> rowMap = new HashMap<>();
           for (int i = 1; i <= columnCount ; i++) {
               rowMap.put(metaData.getColumnName(i), resultSet.getString(i));
           }
           System.out.println("rowMap = " + rowMap);
           System.out.println("-------------");

           //bütün satırları bir list of map' e alalım.
           List<Map<String,Object>> queryData = new ArrayList<>();
           resultSet.beforeFirst(); //önce başa dönülür.
           while (resultSet.next()){
               Map<String,Object> rowMap2 = new HashMap<>();
               for (int i = 1; i <= columnCount ; i++) {
                   rowMap2.put(metaData.getColumnName(i),resultSet.getString(i));
               }
               queryData.add(rowMap2);
           }

           for (Map<String, Object> queryDatum : queryData) {
               System.out.println("map = " + queryDatum);
           }
           System.out.println("-------------");


           //3.satırdaki kişinin soyadını alalım. --> 3.kişi 2. indexte bulunur; çünkü index sıfırdan başlar.
           System.out.println("queryData.get(2).get(\"lastname\") = " + queryData.get(2).get("lastname"));



       } catch (SQLException e) {
           System.out.println("Connection Error" + e.getMessage());
       }
   }

}
