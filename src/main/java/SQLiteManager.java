import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SQLiteManager implements DBManager {
    // we are using the in-memory H2 database
    private final static String DATABASE_URL = "jdbc:sqlite:test1";

    private Dao<Asset, Integer> assetsDao;

    public static void main(String[] args) {
        try {
            List<Asset> assetsFromCSV = Main.getAssetsFromCSV();
            ConnectionSource connectionSource = null;
            // create our data-source for the database
            connectionSource = new JdbcConnectionSource(DATABASE_URL);
            SQLiteManager sqLiteManager = new SQLiteManager();
            sqLiteManager.setupDatabase(connectionSource);
            sqLiteManager.assetsDao.create(assetsFromCSV.get(0));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void setupDatabase(ConnectionSource connectionSource) throws Exception {

        assetsDao = DaoManager.createDao(connectionSource, Asset.class);

        // if you need to create the table
        TableUtils.dropTable(assetsDao, false);
        TableUtils.createTableIfNotExists(connectionSource, Asset.class);
    }

}
