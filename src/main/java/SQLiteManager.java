import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.mapped.MappedPreparedStmt;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SQLiteManager implements DBManager {
    // we are using the in-memory H2 database
    private final static String DATABASE_URL = ConfigManager.getInstance()
            .getValue("databaseUrl", "jdbc:sqlite:test1");

    private Dao<UserAsset, Integer> uAssetsDao;
    private Dao<CraigAsset, Integer> cAssetsDao;
    // הגדרת סינגלטון
    private static SQLiteManager instance = null;//new SQLiteManager();

    private SQLiteManager(){
        try {
            // create our data-source for the database
            ConnectionSource connectionSource = new JdbcConnectionSource(DATABASE_URL);
            setupDatabase(connectionSource);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }
    public static SQLiteManager getInstance(){ // אופציה נוספת
        if (instance == null) {
            synchronized (SQLiteManager.class) {
                if (instance == null) {
                    instance = new SQLiteManager();
                }
            }
        }
        return instance;
    }
//    public static synchronized SQLiteManager getSyncInstance1(){ // אופציה נוספת
//        if (instance == null) {
//            instance = new SQLiteManager();
//        }
//        return instance;

//    }

//    public static SQLiteManager getInstance(){
//        return instance;
//    }

    // עד כאן
    public static void main(String[] args) {
        try {
            List<UserAsset> assetsFromCSV = Main.getAssetsFromCSV();
            // create our data-source for the database
//            ConnectionSource connectionSource = new JdbcConnectionSource(DATABASE_URL);
//            SQLiteManager sqLiteManager = new SQLiteManager();
            SQLiteManager sqLiteManager = SQLiteManager.getInstance();
//            sqLiteManager.setupDatabase(connectionSource);
            sqLiteManager.uAssetsDao.create(assetsFromCSV);
            List<UserAsset> userAssets = sqLiteManager.uAssetsDao.queryForAll();
            Asset asset = sqLiteManager.uAssetsDao.queryForId(1);
            System.out.println(userAssets);
            System.out.println(asset);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void setupDatabase(ConnectionSource connectionSource) throws Exception {
        uAssetsDao = DaoManager.createDao(connectionSource, UserAsset.class);
        cAssetsDao = DaoManager.createDao(connectionSource, CraigAsset.class);

        // if you need to create the table
//        TableUtils.dropTable(uAssetsDao, true);
//        TableUtils.dropTable(cAssetsDao, true);
        TableUtils.createTableIfNotExists(connectionSource, UserAsset.class);
        TableUtils.createTableIfNotExists(connectionSource, CraigAsset.class);
    }

    public Collection<CraigAsset> writeToTableAndRetrieveNewAssests(Set<CraigAsset> assetsForRent) {
        Collection<CraigAsset> newlyCreated = new HashSet<>();
        for (CraigAsset craigAsset : assetsForRent) {
            try {
                Dao.CreateOrUpdateStatus createOrUpdateStatus = cAssetsDao.createOrUpdate(craigAsset);
                if (createOrUpdateStatus.isCreated()) {
                    newlyCreated.add(craigAsset);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return newlyCreated;
    }
}
