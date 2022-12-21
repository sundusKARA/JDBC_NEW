import java.sql.Connection;
import java.sql.Statement;

public class Runner {
    public static void main(String[] args) {


      Connection connection= JdbcUtils.connectToDataBase("localhost", "sundus","postgres","Rsn2013.");
      Statement statement =  JdbcUtils.creatStatement();


    }







}
