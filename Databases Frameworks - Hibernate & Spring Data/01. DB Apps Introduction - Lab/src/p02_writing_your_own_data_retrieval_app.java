import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

import static java.lang.System.in;

public class p02_writing_your_own_data_retrieval_app {
    public static void main(String[] args) throws SQLException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/diablo", properties);

        String query = "SELECT CONCAT(u.first_name, ' ', u.last_name) AS full_name, COUNT(ug.user_id) AS `games`\n" +
                "FROM users AS u\n" +
                "JOIN users_games AS ug ON ug.user_id = u.id\n" +
                "WHERE u.user_name = ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        String username = reader.readLine();
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();

        rs.next();
        if (rs.getString("full_name") != null) {
            System.out.printf("User: %s%n%s has played %s games", username,
                    rs.getString("full_name"), rs.getString("games"));
        } else {
            System.out.println("No such users exists");
        }
    }
}
