import java.sql.Connection;
import java.sql.Statement;

public class Runner_12_13_22 {
    public static void main(String[] args) {
        //1. Adım: Driver'a kaydol
        //2. Adım: Datbase'e bağlan

        Connection connection=JdbcUtils.connectToDataBase("localhost","sundus","postgres","Rsn2013.");

        //3. Adım: Statement oluştur.
        Statement statement = JdbcUtils.creatStatement();

        //4. Adım: Query çalıştır.
      //  JdbcUtils.execute("CREAT TABLE student (name VARCHAR(20),id INT, address VARCHAR(80))");

        JdbcUtils.creatTable("SchoOl","classes VARCHAR(20)","teacher_name VARCHAR(20)","id INT");


    }
}
