import com.opencsv.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

//
/*
Needed components:
 Comunication manager - URL to craiglist  -Send/Recieve HTTP
 Parser - parsing
 Address metcher - WWW to local address
 Notifier - Favorite format massage
*/
public class Main {
    //public static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            //logger.debug("dwkjerhwejrasd", new Exception());
            SQLiteManager sqLiteManager = SQLiteManager.getInstance();
            // Create an object of filereader
            // class with CSV file as a parameter.
//            List<UserAsset> ownAssets = getAssetsFromCSV();
            ConfigManager configManager = ConfigManager.getInstance();
            String baseUrl = configManager.getValue("baseUrl", "https://sfbay.craigslist.org/search/sfc/apa");
            String urlPageNextPattern = configManager.getValue("urlPageNextPattern", "?s=%d");
            int pageIndexJump = configManager.getValue("pageIndexJump", 120);
            String urlFormat = baseUrl + urlPageNextPattern;
            double marginDistance = configManager.getValue("marginDistance", 1.0);
            List<UserAsset> ownAssets = getAssetsFromFile();
            Collection<UserAsset> newUserAssests = sqLiteManager.writeToTableUserAndRetrive(ownAssets);
            for (int i = 0;i < 2;i++) {
                System.out.println(java.time.LocalDateTime.now());
                String url = String.format(urlFormat, i * pageIndexJump);
                System.out.println(url);
                Set<String> links = Parser.getLinks(url);
                Set<CraigAsset> assetsForRent = new HashSet<>();
                for (String link : links) {
                    CraigAsset assetForRent = Parser.getCraigAsset(link);
                    assetsForRent.add(assetForRent);
                }
                Collection<CraigAsset> newCraigAssests = sqLiteManager.writeToTableAndRetrieveNewAssets(assetsForRent);
                List<AssetsWrapper> assetsWrappers = new ArrayList<>();
                DistanceCalculator calculator = new HaversineCalculator();

                for (Asset own : ownAssets) {
                    for (Asset forRent : newCraigAssests) {
                        Coordinate ownCoor = new Coordinate(own.lat, own.lon);
                        Coordinate forRentCoor = new Coordinate(forRent.lat, forRent.lon);
                        AssetsWrapper assetsWrapper = new AssetsWrapper(own, forRent,
                                calculator.calculate(ownCoor, forRentCoor));
                        assetsWrappers.add(assetsWrapper);
                    }
                }
                for (AssetsWrapper assetsWrapper : assetsWrappers) {
                    if (assetsWrapper.distance < marginDistance) {
                        System.out.println(assetsWrapper);
                    }
                }
                if (newCraigAssests.size() < assetsForRent.size()){
                    break;
                }
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<UserAsset> getAssetsFromFile() throws IOException {
        String fileName = ConfigManager.getInstance()
                .getValue("fileName","resources/MyApartments.csv");
        List<String> lines = Files.readAllLines(Paths.get(fileName), Charset.defaultCharset());
        List<UserAsset> ownAssets = new ArrayList<>();
        for(String line: lines){
            UserAsset asset = Parser.getUserAsset(line);
            ownAssets.add(asset);
        }
//        System.out.println(ownAssets);
        return ownAssets;
    }


    public static List<UserAsset> getAssetsFromCSV() throws IOException {
        String fileName = ConfigManager.getInstance()
                .getValue("fileName","resources/MyApartments.csv");
        FileReader filereader = new FileReader(fileName);

        // create csvReader object passing
        // file reader as a parameter
        CSVReader csvReader = new CSVReader(filereader);
        String[] nextRecord;
        List<UserAsset> ownAssets = new ArrayList<>();

        // we are going to read data line by line
        while ((nextRecord = csvReader.readNext()) != null) {
            double lon = Double.parseDouble(nextRecord[nextRecord.length-2]);
            double lat = Double.parseDouble(nextRecord[nextRecord.length-1]);
            Coordinate coordinate = new Coordinate(lon,lat);

            UserAsset asset = AssetBuilder.builder()
                    .setAddress(nextRecord[1])
                    .setDescription(nextRecord[2])
                    .setUrl(nextRecord[0])
                    .setCoordinate(coordinate)
                    .buildUserAsset();

            ownAssets.add(asset);

        }
//            System.out.println(ownCoordinates);
        return ownAssets;
    }
}