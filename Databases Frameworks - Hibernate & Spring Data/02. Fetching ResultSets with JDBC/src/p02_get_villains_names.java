import java.sql.*;
import java.util.Properties;

public class p02_get_villains_names {
    public static void main(String[] args) throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minionsdb", properties);

        String sql = "SELECT v.NAME, COUNT(mv.minion_id) AS number_of_minions\n" +
                "FROM villains AS v\n" +
                "JOIN minions_villains AS mv ON mv.villain_id = v.id\n" +
                "GROUP BY mv.villain_id\n" +
                "HAVING number_of_minions > 3\n" +
                "ORDER BY number_of_minions DESC;";

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            System.out.println(rs.getString("name") + " " + rs.getString("number_of_minions"));
        }

        rs.close();
        statement.closeOnCompletion();
        connection.close();
    }
}
