import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtils {
    public static void main(String[] args) {

    }
  private static Connection connection;
    private static Statement statement;
    //1. Adım: Driver'a kaydol
    //2. Adım: Database'e bağlan
    //3. Adım: Statement oluştur
    //bu uc adimi handel ettik her seferinde bu uc adimi yapmaktan ve exception atmaktansa bu iki methodu burada yazdik ve static yaptik her yerden cagirabiliriz

    public static Connection connectToDataBase(String hostName, String dbName, String userName, String password) {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://"+hostName+":5432/"+dbName,userName,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(connection!=null){
            System.out.println("Connection Success");
        }else{
            System.out.println("Connection Fail");
        }

     return connection;
    }

    public static Statement creatStatement(){
        try {
            Statement st = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return statement;
    }
    //4. Adım: Query çalıştır.
    public static boolean execute(String sql){
        boolean isExecute;
        try {
            isExecute = statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return isExecute;
    }

    //5. Adım: Bağlantı ve Statement'ı kapat.
    public static void closeConnectionAndStatement(){

        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if(connection.isClosed()&&statement.isClosed()){
                System.out.println("Connection and statement closed!");
            }else{
                System.out.println("Connection and statement NOT closed!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Table olusturan method
    public static void creatTable(String tableName, String... columnName_dataType){
        StringBuilder columnName_dataTypeStr = new StringBuilder("");


        for (String w:columnName_dataType) {
            columnName_dataTypeStr.append(w).append(",");

        }
        columnName_dataTypeStr.deleteCharAt(columnName_dataTypeStr.length()-1); //lastindex() ile de silebilirz
                                                                                       //bu kodla sona gelen virgulu sildik
        System.out.println(columnName_dataTypeStr);


        try {
            statement.execute("CREATE TABLE" +"tableName"+"("+columnName_dataTypeStr+")");
            System.out.println("TABLE"+tableName+"successfully created!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



}
