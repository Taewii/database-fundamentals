import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

import static java.lang.System.in;

public class p06_remove_villain {
    public static void main(String[] args) throws SQLException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "");

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/minionsdb", properties);

        String villainName;
        int id = Integer.parseInt(reader.readLine());

        PreparedStatement selectVillainStatement = conn.prepareStatement("SELECT name FROM villains WHERE id = ?");
        selectVillainStatement.setInt(1, id);
        ResultSet villainNameRS = selectVillainStatement.executeQuery();

        if (!villainNameRS.isBeforeFirst()) {
            System.out.println("No such villain was found");
        } else {
            villainNameRS.next();
            villainName = villainNameRS.getString("name");

            PreparedStatement releaseMinionsStatement =
                    conn.prepareStatement("DELETE mv FROM minions_villains AS mv WHERE mv.villain_id = ?");
            releaseMinionsStatement.setInt(1, id);
            int affectedRows = releaseMinionsStatement.executeUpdate();

            PreparedStatement removeVillainStatement = conn.prepareStatement("DELETE FROM villains WHERE id = ?");
            removeVillainStatement.setInt(1, id);
            removeVillainStatement.executeUpdate();

            System.out.println(villainName + " was deleted");
            System.out.println(affectedRows + " minions released");

            releaseMinionsStatement.closeOnCompletion();
            removeVillainStatement.closeOnCompletion();
        }

        reader.close();
        selectVillainStatement.closeOnCompletion();
        villainNameRS.close();
        conn.close();
    }
}
