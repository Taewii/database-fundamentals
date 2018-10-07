import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

import static java.lang.System.in;

public class p01_accessing_database_via_simple_java_app {
    public static void main(String[] args) throws SQLException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/soft_uni", props);

        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM employees WHERE salary > ?");
        String salary = reader.readLine();

        stmt.setDouble(1, Double.parseDouble(salary));

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            System.out.printf("%s %s%n", rs.getString("first_name"), rs.getString("last_name"));
        }
    }
}
