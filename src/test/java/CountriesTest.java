import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CountriesTest {
    /*
        Given
          User connects to the database
        When
          User sends the query to get the region ids from "countries" table
        Then
          Assert that the number of region ids greater than 1 is 17.
        And
          User closes the connection
       */
@Test
    public void countryTest() throws SQLException {
    //1.adim User connects to the database

    JdbcUtils.connectToDataBase("localhost", "sundus", "postgres", "Rsn2013.");
    Statement statement = JdbcUtils.creatStatement();

    //2.Adim  User sends the query to get the region ids from "countries" table
    //(region id =  )
    String sql1 = "SELECT region_id FROM countries";
    ResultSet resulSet1 = statement.executeQuery(sql1);
    List<Integer> ids = new ArrayList<>();

    while (resulSet1.next()) {
        ids.add(resulSet1.getInt(1));
    }
    System.out.println("ids = " + ids);
    List<Integer> idsGreaterThan1 = new ArrayList<>();
    for (int w : ids) {
        idsGreaterThan1.add(w);

    }
    System.out.println("idsGreatThan1 = " + idsGreaterThan1);
    // Assert that the number of region ids greater than 1 is 17.
    //Kullanıcı, "ülkeler" tablosundan bölge kimliklerini almak için sorguyu gönderir
    Assert.assertEquals(17,idsGreaterThan1.size());


    //4.adim User closes the connection

    JdbcUtils.closeConnectionAndStatement();



}






}
