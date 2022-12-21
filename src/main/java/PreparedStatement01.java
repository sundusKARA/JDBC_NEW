import java.sql.*;

public class PreparedStatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sundus","postgres","Rsn2013.");
        Statement st = con.createStatement();
        /*
        PreparedStatement interface, birden cok kez calistirilabilen onceden derlenmis bir SQL kodunu temsil eder.
        Paremetrelendirilmis SQL sorgulari (query) ile calisisr. bu sorguyu 0 veya daha fazla parametre ile kullanabiliriz
         */
        //Ornek:Prepared statement kullanarak company adı IBM olan number_of_employees değerini 9999 olarak güncelleyin.
        //1. Adim: PreparedStatement query'sini calistir
        String sql1="UPDATE companies1 SET number_of_employees = ? WHERE company = ? ";

        //2.Adim : PreparedStatement objesini olustur
        PreparedStatement pst1 = con.prepareStatement(sql1);

        //3.Adim : setInt() setString(),... methodlarini ku;;anarak soru isaretleri yerlerine deger gir
        pst1.setInt(1, 9999);
        pst1.setString(2,"IBM");

        //4.Adim : Query'yi calistir.
        int guncellenenSatirSayisi = pst1.executeUpdate();
        System.out.println("guncellenenSatirSayisi = "+ guncellenenSatirSayisi);

        String sql2 = "SELECT * FROM companies1";
        ResultSet rst = st.executeQuery(sql2);

        while (rst.next()){
            System.out.println(rst.getInt(1)+ "--"+ rst.getString(2)+ "--"+ rst.getInt(3));

        }

//2. Örnek: Prepared statement kullanarak company adı GOOGLE olan number_of_employees değerini 5555 olarak güncelleyin.

        pst1.setInt(1,5555);
        pst1.setString(2,"GOOGLE");

        int guncellenen = pst1.executeUpdate();
        System.out.println("guncellenen = "+ guncellenen );
        ResultSet rst2 = st.executeQuery(sql2);

        while(rst2.next()){
            System.out.println(rst2.getInt(1)+"--"+ rst2.getString(2)+ "--"+rst2.getInt(3));
        }

        con.close();
        st.close();
        rst.close();
        rst2.close();
        pst1.close();














    }

}
