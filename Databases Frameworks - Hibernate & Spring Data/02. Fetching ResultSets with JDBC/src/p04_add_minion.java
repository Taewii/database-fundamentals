import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

import static java.lang.System.in;

public class p04_add_minion {
    public static void main(String[] args) throws SQLException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minionsdb", properties);

        String[] minionTokens = reader.readLine().split(" ");
        String[] villainTokens = reader.readLine().split(" ");

        String minionName = minionTokens[1];
        int minionAge = Integer.parseInt(minionTokens[2]);
        String minionTown = minionTokens[3];
        String villainName = villainTokens[1];

        PreparedStatement townStatement = connection.prepareStatement("SELECT * FROM towns WHERE `name` = ?");
        townStatement.setString(1, minionTown);
        ResultSet townRS = townStatement.executeQuery();

        if (!townRS.isBeforeFirst()) {
            PreparedStatement insertTownStatement = connection.prepareStatement("INSERT INTO towns(name) VALUES (?)");
            insertTownStatement.setString(1, minionTown);
            insertTownStatement.executeUpdate();

            System.out.printf("Town %s was added to the database.%n", minionTown);
        }

        townRS = townStatement.executeQuery();
        townRS.next();
        int townId = townRS.getInt("id");

        PreparedStatement villainSelectStatement = connection.prepareStatement("SELECT * FROM villains WHERE `name` = ?");
        villainSelectStatement.setString(1, villainName);
        ResultSet villainRS = villainSelectStatement.executeQuery();

        if (!villainRS.isBeforeFirst()) {
            PreparedStatement insertVillainStatement =
                    connection.prepareStatement("INSERT INTO villains(name, evilness_factor) VALUES (?, 'evil')");
            insertVillainStatement.setString(1, villainName);
            insertVillainStatement.executeUpdate();

            System.out.printf("Villain %s was added to the database.%n", villainName);
        }

        villainRS = villainSelectStatement.executeQuery();
        villainRS.next();
        int villainId = villainRS.getInt("id");

        PreparedStatement insertMinionStatement =
                connection.prepareStatement("INSERT INTO minions(`name`, age, town_id) VALUES(?, ?, ?)");
        insertMinionStatement.setString(1, minionName);
        insertMinionStatement.setInt(2, minionAge);
        insertMinionStatement.setInt(3, townId);
        insertMinionStatement.executeUpdate();

        PreparedStatement getMinionIdStatement = connection.prepareStatement("SELECT id FROM minions WHERE `name` = ?");
        getMinionIdStatement.setString(1, minionName);
        ResultSet minionIdRS = getMinionIdStatement.executeQuery();
        minionIdRS.next();
        int minionId = minionIdRS.getInt("id");

        PreparedStatement connectMinionToVillainStatement =
                connection.prepareStatement("INSERT INTO minions_villains (minion_id, villain_id) VALUES(?, ?)");
        connectMinionToVillainStatement.setInt(1, minionId);
        connectMinionToVillainStatement.setInt(2, villainId);
        connectMinionToVillainStatement.executeUpdate();

        System.out.printf("Successfully added %s to be minion of %s%n", minionName, villainName);

        minionIdRS.close();
        townRS.close();
        villainRS.close();
        connectMinionToVillainStatement.closeOnCompletion();
        getMinionIdStatement.closeOnCompletion();
        insertMinionStatement.closeOnCompletion();
        townStatement.closeOnCompletion();
        villainSelectStatement.closeOnCompletion();
        connection.close();
    }
}
