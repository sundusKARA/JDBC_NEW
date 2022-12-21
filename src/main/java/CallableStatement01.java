import java.lang.reflect.Type;
import java.sql.*;

public class CallableStatement01 {
    /*
    Javada methodlar return type sahibi olsa da olmasa da method olarak adlandirilir
    SQL'de ise data retyurn ediyorsa "function" denirReturn yapmiyorsa "precedure" olarak adlandirilir

     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sundus","postgres","Rsn2013.");
        Statement st = con.createStatement();

     //CallableStatement ile function cagirmayi parameterelendirecegiz
        //1.Adim Function code'nu yaz
        String sql2 = "CREATE OR REPLACE FUNCTION  toplamaF(x NUMERIC, y NUMERIC) --BIR FUNCTION OLUSTURUYORUZ. IKI TANE SAYIYI TOPLAYAN FOUNCITON\n" +
                "RETURNS NUMERIC \n" +
                "LANGUAGE plpgsql\n" +
                "AS\n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "RETURN x+y;\n" +
                "\n" +
                "END\n" +
                "$$";
        //2. Adim Function'i calistir
        st.execute(sql2);

        //3. Adim Function'i cagir
      CallableStatement cst1 = con.prepareCall("{? = call toplamaF(?,?)}");    //return type yazip call deyip function'in ismini yaz

        //4.Adim: Return icin registerOurParametre() methodunu, parametreler icin ise set()... methodlari uygula (sqlden javaya cagirdigimizda farklilik gosteriyor. yani sqlde numeric javada baska bir type denk geliyor)

        cst1.registerOutParameter(1, Types.NUMERIC);
        cst1.setInt(2,6);
        cst1.setInt(3,4);

        //5.Adim : execute() methodu ile CallableStatement'i calistir

        cst1.execute();

        //6. Adim : Sonucu cagirmak icin return data type tipine gore
        System.out.println(cst1.getBigDecimal(1));


        //2.Ornek: Koninin hacmini hesaplayan bir function yazin
        //1.Adim Function code'nu yaz
        String sql3 = "CREATE OR REPLACE FUNCTION  konininHacmi(r NUMERIC, h NUMERIC)\n" +
                "RETURNS NUMERIC \n" +
                "LANGUAGE plpgsql\n" +
                "AS\n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "RETURN 3.14*r*r*h/3;\n" + //burayi hesap icin degistirdik
                "\n" +
                "END\n" +
                "$$";
        //2. Adim Function'i calistir
        st.execute(sql3);

        //3. Adim Function'i cagir
        CallableStatement cst2 = con.prepareCall("{? = call konininHacmi(?,?)}");

        //4.Adim: Return icin registerOurParametre() methodunu, parametreler icin ise set()... methodlari uygula (sqlden javaya cagirdigimizda farklilik gosteriyor. yani sqlde numeric javada baska bir type denk geliyor)

        cst2.registerOutParameter(1, Types.NUMERIC);
        cst2.setInt(2,6);
        cst2.setInt(3,9);

        //5.Adim : execute() methodu ile CallableStatement'i calistir

        cst2.execute();

//6. Adim : Sonucu cagirmak icin return data type tipine gore
        System.out.println(cst2.getBigDecimal(1));
        System.out.printf("%.2f", cst2.getBigDecimal(1));



    }
}
