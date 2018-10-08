import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Arrays;
import java.util.Properties;

import static java.lang.System.in;

public class p08_increase_minions_age {
    public static void main(String[] args) throws SQLException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "");

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/minionsdb", properties);

        int[] ids = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        PreparedStatement updateAgesStatement = conn.prepareStatement("UPDATE minions SET age = age + 1 WHERE id = ?");

        for (int id : ids) {
            updateAgesStatement.setInt(1, id);
            updateAgesStatement.executeUpdate();
        }

        PreparedStatement printAllMinionsStatement = conn.prepareStatement("SELECT name, age FROM minions");
        ResultSet minionsRS = printAllMinionsStatement.executeQuery();

        while (minionsRS.next()) {
            String name = minionsRS.getString("name");
            String age = minionsRS.getString("age");
            System.out.println(name + " " + age);
        }

        reader.close();
        updateAgesStatement.closeOnCompletion();
        printAllMinionsStatement.closeOnCompletion();
        minionsRS.close();
    }
}
