import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.sql.*;

public class ExecuteQuery02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sundus","postgres","Rsn2013.");
        Statement st = con.createStatement();


        //1. Örnek: companies tablosundan en yüksek ikinci number_of_employees değeri olan company ve number_of_employees değerlerini çağırın.
        String sql1 = "select company,number_of_employees from companies order by number_of_employees desc offset 1 limit 1";
        ResultSet resultSet1 = st.executeQuery(sql1);

        while (resultSet1.next()) {
            System.out.println(resultSet1.getString(1) + "--" + resultSet1.getInt(2));//1.Çağırma şeklimiz
//            System.out.println(resultSet1.getString("company") + "--" + resultSet1.getInt("number_of_employees"));//2.Çağırma şeklimiz
        }

        //2.Yol: Subquery kullanarak
        String sql2 = "select company,number_of_employees from companies \n" +
                "where number_of_employees = \n" +
                "(select max(number_of_employees) from companies where number_of_employees < (select max(number_of_employees) from companies))";
        ResultSet resultSet2 = st.executeQuery(sql2);

        while (resultSet2.next()){
            System.out.println(resultSet2.getString(1) + "--" + resultSet2.getInt(2));//1.Çağırma şeklimiz
//            System.out.println(resultSet2.getString("company") + "--" + resultSet2.getInt("number_of_employees"));//2.Çağırma şeklimiz
        }

        con.close();
        st.close();
        resultSet1.close();
        resultSet2.close();







    }
}
