import java.sql.*;
import java.util.Properties;

public class p01_initial_setup {

    static String CONNECTION = "jdbc:mysql://localhost:3306/";

    public static void main(String[] args) throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "");

        Connection connection = DriverManager.getConnection(CONNECTION, properties);
        Statement statement = connection.createStatement();
        String sql;

        sql = "DROP DATABASE IF EXISTS minionsdb";
        statement.execute(sql);
        sql = "CREATE DATABASE minionsdb";
        statement.execute(sql);

        connection = DriverManager.getConnection(CONNECTION + "minionsdb", properties);
        statement = connection.createStatement();

        sql = "CREATE TABLE towns (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(30), country VARCHAR(30));";
        statement.execute(sql);

        sql = "CREATE TABLE minions (" +
                "id INT PRIMARY KEY AUTO_INCREMENT," +
                " name VARCHAR(30), age INT," +
                " town_id INT," +
                "CONSTRAINT fk_town_id FOREIGN KEY (town_id)" +
                "REFERENCES towns(id));";
        statement.execute(sql);

        sql = "CREATE TABLE villains (" +
                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                "name varchar(50), " +
                "evilness_factor varchar(20))";
        statement.execute(sql);

        sql = "CREATE TABLE minions_villains(" +
                "minion_id INT, " +
                "villain_id INT, " +
                "CONSTRAINT fk_Minions FOREIGN KEY (minion_id) " +
                "REFERENCES Minions(id), " +
                "CONSTRAINT  fk_Villains FOREIGN KEY (villain_id) " +
                "REFERENCES villains(id))";
        statement.execute(sql);

        sql = "INSERT INTO towns (name, country) " +
                "VALUES ('Sofia','Bulgaria'), ('Burgas','Bulgaria'), ('Varna', 'Bulgaria')," +
                " ('London','UK'), ('Liverpool','UK'),('Ocean City','USA'),('Paris','France')";
        statement.execute(sql);

        sql = "INSERT INTO minions (name, age, town_id) " +
                "VALUES ('bob',10,1),('kevin',12,2),('steward',9,3), ('rob',22,3), ('michael',5,2),('pep',3,2)";
        statement.execute(sql);

        sql = "INSERT INTO villains (name, evilness_factor) " +
                "VALUES ('Gru','super evil'),('Victor','evil'),('Simon Cat','good'),('Pusheen','super good'),('Mammal','evil')";
        statement.execute(sql);

        sql = "INSERT INTO minions_villains " +
                "VALUES (1,2), (3,1),(1,3),(3,3),(4,1),(2,2),(1,1),(3,4), (1, 4), (1,5), (5, 1), (4,1), (3, 1)";
        statement.execute(sql);

        statement.closeOnCompletion();
        connection.close();
    }
}
