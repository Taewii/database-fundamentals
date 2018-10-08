import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class p03_get_minion_names {
    public static void main(String[] args) throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minionsdb", properties);

        Scanner scanner = new Scanner(System.in);

        String nameSql = "SELECT name FROM villains WHERE id = ?";
        PreparedStatement villainNameStatement = connection.prepareStatement(nameSql);
        int index = Integer.parseInt(scanner.nextLine());
        villainNameStatement.setInt(1, index);
        ResultSet villainNameResultSet = villainNameStatement.executeQuery();

        if (!villainNameResultSet.isBeforeFirst()) {
            System.out.printf("No villain with ID %d exists in the database.", index);
            return;
        }

        villainNameResultSet.next();
        System.out.println("Villian: " + villainNameResultSet.getString("name"));

        String minionsSql = "SELECT m.name, age\n" +
                "FROM villains AS v \n" +
                "JOIN minions_villains AS  mv ON v.id = mv.villain_id\n" +
                "JOIN minions AS m ON m.id = mv.minion_id\n" +
                "WHERE v.id = ?";

        PreparedStatement minionNamesStatement = connection.prepareStatement(minionsSql);
        minionNamesStatement.setInt(1, index);
        ResultSet minionNamesResultSet = minionNamesStatement.executeQuery();

        int counter = 1;
        while (minionNamesResultSet.next()) {
            System.out.printf("%d. %s %s%n", counter++, minionNamesResultSet.getString("name"),
                    minionNamesResultSet.getString("age"));
        }

        scanner.close();
        villainNameResultSet.close();
        minionNamesResultSet.close();
        minionNamesStatement.closeOnCompletion();
        villainNameStatement.closeOnCompletion();
        connection.close();
    }
}
