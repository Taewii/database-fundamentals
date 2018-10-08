import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class p07_print_all_minion_names {
    public static void main(String[] args) throws SQLException {

        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "");

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/minionsdb", properties);

        List<String> minionNames = new ArrayList<>();
        PreparedStatement getMinionNamesStatement = conn.prepareStatement("SELECT name FROM minions");
        ResultSet minionNamesRS = getMinionNamesStatement.executeQuery();

        while (minionNamesRS.next()) {
            String name = minionNamesRS.getString("name");
            minionNames.add(name);
        }

        for (int i = 0; i < minionNames.size() / 2; i++) {
            System.out.println(minionNames.get(i));
            System.out.println(minionNames.get(minionNames.size() - 1 - i));
        }

        minionNamesRS.close();
        getMinionNamesStatement.closeOnCompletion();
        conn.close();
    }
}
