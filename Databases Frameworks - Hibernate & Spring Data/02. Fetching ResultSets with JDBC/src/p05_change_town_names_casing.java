import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static java.lang.System.in;

public class p05_change_town_names_casing {
    public static void main(String[] args) throws SQLException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "");

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/minionsdb", properties);

        String countryName = reader.readLine();
        String sql = "UPDATE towns\n" +
                "SET `name` = UPPER(`name`)\n" +
                "WHERE country = ?";

        PreparedStatement updateTownNamesStatement = conn.prepareStatement(sql);
        updateTownNamesStatement.setString(1, countryName);
        int affectedRows = updateTownNamesStatement.executeUpdate();

        List<String> updatedNames = new ArrayList<>();
        if (affectedRows != 0) {
            PreparedStatement retrieveUpdatedNamesStatement =
                    conn.prepareStatement("SELECT name FROM towns WHERE country = ?");
            retrieveUpdatedNamesStatement.setString(1, countryName);
            ResultSet updatedNamesRS = retrieveUpdatedNamesStatement.executeQuery();

            while (updatedNamesRS.next()) {
                String townName = updatedNamesRS.getString("name");
                updatedNames.add(townName);
            }

            retrieveUpdatedNamesStatement.closeOnCompletion();
            updatedNamesRS.close();

            System.out.println(affectedRows + " town names were affected.");
            System.out.println(String.join(", ", updatedNames));
        } else {
            System.out.println("No town names were affected.");
        }

        updateTownNamesStatement.closeOnCompletion();
        conn.close();
    }
}
