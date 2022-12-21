import java.sql.*;

public class ExecuteUpdate01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {


        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sundus","postgres","Rsn2013.");
        Statement st = con.createStatement();

        //1. Örnek: number_of_employees değeri ortalama çalışan sayısından az olan number_of_employees değerlerini 16000 olarak UPDATE edin.

         String s1 = " UPDATE companies SET number_of_employees = 16000 WHERE number_of_employees < (SELECT AVG (number_of_employees) FROM companies)";

          int updateEdilenSatirSayisi = st.executeUpdate(s1);
        System.out.println("updateEdilenSatirSayisi : " + updateEdilenSatirSayisi);
        ResultSet resulSet1 = st.executeQuery("SELECT * FROM companies");
        while (resulSet1.next()){
            System.out.println(resulSet1.getInt(1)+ "--" + resulSet1.getString(2)+ "--"+ resulSet1.getInt(3));
        }

        con.close();
        st.close();
        resulSet1.close();
    }
}
