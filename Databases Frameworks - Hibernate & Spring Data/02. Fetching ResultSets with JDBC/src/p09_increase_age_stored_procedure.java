import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

import static java.lang.System.in;

public class p09_increase_age_stored_procedure {
    public static void main(String[] args) throws SQLException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "");

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/minionsdb", properties);

        int id = Integer.parseInt(reader.readLine());

        CallableStatement increaseAgeStatement = conn.prepareCall("{CALL usp_get_older(?)}");
        increaseAgeStatement.setInt(1, id);
        increaseAgeStatement.execute();

        PreparedStatement getMinionByIdStatement = conn.prepareStatement("SELECT name, age FROM minions WHERE id = ?");
        getMinionByIdStatement.setInt(1, id);
        ResultSet minionRS = getMinionByIdStatement.executeQuery();
        minionRS.next();

        System.out.printf("%s %s%n", minionRS.getString("name"), minionRS.getString("age"));

        reader.close();
        increaseAgeStatement.closeOnCompletion();
        getMinionByIdStatement.closeOnCompletion();
        minionRS.close();
        conn.close();
    }
}
