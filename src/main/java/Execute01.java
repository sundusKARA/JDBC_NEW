import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1. Adım: Driver'a kaydol
        Class.forName("org.postgresql.Driver");

        //2. Adım: Database'e bağlan
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sundus","postgres","Rsn2013.");

        //3. Adım: Statement oluştur.
        Statement st = con.createStatement();

        System.out.println("Connection Success");
        /*
        execute() methodu DDL(creat, drop, alter table) ve DQL(select) icin kullanilabilir
        1) Eger execute() methodu DDL icin kullanilirsa 'false' return eder
        2) Eger execute() methodu DQL icin kullanilirsa 'ResultSet' alindiginda 'true' aksi halde 'false' verir

         */


//1.Örnek: "workers" adında bir table oluşturup "worker_id,worker_name, worker_salary" sütunlarını ekleyin.
   boolean sql1 = st.execute("CREATE TABLE workers(worker_id VARCHAR(20), worker_name VARCHAR(20), worker_salary INT )"); // Burasi boolean dondurur o yuzden true veya false verecek
        System.out.println("sql1 = " + sql1);                                                                                                 //herhangi bir data cagiramazsa bu code false verir eger data varsa true verir
                                                                                                                               // bu sadece database olusturduk icinde data yok
//2.Örnek: Table'a worker_address sütunu ekleyerek alter yapın.
        String sql2 = "alter table workers add worker_address varchar(80);";

      //  st.execute("alter table workers add worker_address varchar(80);"); ayni islemi yapar

        st.execute(sql2);
        System.out.println("sql2 = " + sql2);

        //3.Örnek: Drop workers table (sil)

        String sql3 = "Drop Table workers";
        st.execute(sql3);    //pgadmindeki en son code silme codu oldugu icin burada istedigim kadar calistirabilirirm hata vermez
        System.out.println("sql3 = " + sql3);

        //5. adim baglanti ve statementi kapat
        con.close();
        st.close();







    }
}
