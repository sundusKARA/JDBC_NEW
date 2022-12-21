import java.sql.*;

public class ExecuteQuery01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sundus","postgres","Rsn2013.");
        Statement st = con.createStatement();
    //1. Örnek:  region id'si 1 olan "country name" değerlerini çağırın.

    String sql1 = "select country_name\n" +
                  "from countries\n" +
                  "where region_id =1";
    boolean r1 = st.execute(sql1);
    System.out.println("r1 = " + r1);

    //recordlari gormek icin  ExecuteQuery() methodunu kullanmaliyiz
    ResultSet resultSet1 = st.executeQuery(sql1);  //data type resulset databaseden gelen datalarin icinde bulundugu konteynerdir
                                                   // bu kodu yazdiktan sonra datalari gorecegiz
     while(resultSet1.next()){     //next boolean data doner. siradakine gecer bu kodla

         System.out.println(resultSet1.getString(1)); //1 bu bize 1.columni verir, bir de column ismiyle de yazdirabiliriz
                                                                 // pgadminde datamiz string oldugu icin string cagirdik
     }

//2.Örnek: "region_id"nin 2'den büyük olduğu "country_id" ve "country_name" değerlerini çağırın.

        String sql2 = "SELECT country_name, country_id FROM countries WHERE region_id>2"; // tirnak icindeki codu pgadminda yazmistik ve onu bir konteynera koyarak calistirdik
      ResultSet resultSet2 = st.executeQuery(sql2);

      while (resultSet2.next()){

          System.out.println(resultSet2.getString("country_name")+"--"+resultSet2.getString("country_id"));
      }
        //3.Örnek: "number_of_employees" değeri en düşük olan satırın tüm değerlerini çağırın.
        String sql3 = "SELECT *FROM companies WHERE number_of_employees = (SELECT MIN (number_of_employees) FROM companies)";
      ResultSet resultSet3 = st.executeQuery(sql3);

      while (resultSet3.next()){
          System.out.println(resultSet3.getInt(1)+"--"+resultSet3.getString(2)+"--"+resultSet3.getInt(3));

      }





    }
}
