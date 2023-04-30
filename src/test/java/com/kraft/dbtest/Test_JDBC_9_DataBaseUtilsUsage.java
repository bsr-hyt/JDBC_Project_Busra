package com.kraft.dbtest;

import com.kraft.utilities.DBUtils;
import org.testng.annotations.Test;

import java.util.List;

public class Test_JDBC_9_DataBaseUtilsUsage {

    @Test
    public void columnNames() {
        //DataBase bağlantısını kuralım.
        DBUtils.createConnection();

        //sorgumuzu(query) belirleyelim.
        String query = "select * from customer";

        //column isimlerini bir liste alalım.
        List<String> columnNames = DBUtils.getColumnNames(query);

        System.out.println("columnNames = " + columnNames);

        //bağlantıyı kapatalım.
        DBUtils.destroy();
    }

    @Test
    public void columnDatas() {
        //DataBase bağlantısını kuralım.
        DBUtils.createConnection();

        //sorgumuzu(query) belirleyelim.
        String query = "select * from customer";

        //firstname columndaki bütün dataları bir liste al.
        List<Object> firstname = DBUtils.getColumnData(query, "firstname");
        System.out.println("firstname = " + firstname);

        //bağlantıyı kapatalım.
        DBUtils.destroy();
    }

    @Test
    public void firstRowDataToList() {
        //DataBase bağlantısını kuralım.
        DBUtils.createConnection();

        //sorgumuzu(query) belirleyelim.
        String query = "select * from customer";

        //ilk satırın bilgilerini bir liste alalım.
        List<Object> rowList = DBUtils.getRowList(query);
        for (Object o : rowList) {
            System.out.println(o);
        }

        //bağlantıyı kapatalım.
        DBUtils.destroy();

        //Not: diğer satırların bilgisini nasıl alacağız?
        //function ile veriyi getirip; tek satırlık veri getirdin diyelim mesela
        //bu metodu(getRowList()) kullanacaksın.
    }

    @Test
    public void tableDataList() {
        //DataBase bağlantısını kuralım.
        DBUtils.createConnection();

        //sorgumuzu(query) belirleyelim.
        String query = "select * from customer";

        //tablodaki bütün verileri bir liste al.
        List<List<Object>> queryResultList = DBUtils.getQueryResultList(query);
        System.out.println("queryResultList = " + queryResultList);

        //bağlantıyı kapatalım.
        DBUtils.destroy();
    }

    @Test
    public void tableDataMap() {
        //DataBase bağlantısını kuralım.
        DBUtils.createConnection();

        //sorgumuzu(query) belirleyelim.
        String query = "select * from customer";

        //Tablodaki bilgileri bir list of map'e alalım.
        List<List<Object>> queryResultList = DBUtils.getQueryResultList(query);
        System.out.println("queryResultList = " + queryResultList);

        //2.sıradaki kayıtın ismini alalım.
//        System.out.println("queryResultList.get(1).get(\"firstname\") = " + queryResultList.get(1).get("firstname"));

        //bağlantıyı kapatalım.
        DBUtils.destroy();
    }

}
