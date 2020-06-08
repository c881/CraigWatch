import com.opencsv.CSVReader;
import org.jetbrains.annotations.NotNull;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//
/*
Needed components:
 Comunication manager - URL to craiglist  -Send/Recieve HTTP
 Parser - parsing
 Address metcher - WWW to local address
 Notifier - Favorite format massage
*/
public class Main {
    public static void main(String[] args) {
        try {
            // Create an object of filereader
            // class with CSV file as a parameter.
            List<Asset> ownAssets = getAssetsFromCSV();
            String url = "https://sfbay.craigslist.org/search/sfc/apa";
            Set<String> links = Parser.getLinks(url);
            Set<Asset> assetsForRent = new HashSet<>();
            for (String link : links) {
                Asset assetForRent = Parser.getAsset(link);
                assetsForRent.add(assetForRent);
            }

            List<AssetsWrapper> assetsWrappers = new ArrayList<>();
            DistanceCalculator calculator = new HaversineCalculator();

            double marginDistance = 15;

            for (Asset own : ownAssets) {
                for (Asset forRent : assetsForRent) {
                    Coordinate ownCoor = new Coordinate(own.lat, own.lon);
                    Coordinate forRentCoor = new Coordinate(forRent.lat, forRent.lon);
                    AssetsWrapper assetsWrapper = new AssetsWrapper(own, forRent,
                            calculator.calculate(ownCoor, forRentCoor));
                    assetsWrappers.add(assetsWrapper);
                }
            }

            for (AssetsWrapper assetsWrapper : assetsWrappers)
            {
                if (assetsWrapper.distance < marginDistance)
                {
                    System.out.println(assetsWrapper);
                }
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @NotNull
    public static List<Asset> getAssetsFromCSV() throws IOException {
        String fileName = "resources/MyApartments.csv";
        FileReader filereader = new FileReader(fileName);

        // create csvReader object passing
        // file reader as a parameter
        CSVReader csvReader = new CSVReader(filereader);
        String[] nextRecord;
        List<Asset> ownAssets = new ArrayList<>();

        // we are going to read data line by line
        while ((nextRecord = csvReader.readNext()) != null) {
            double lon = Double.parseDouble(nextRecord[nextRecord.length-2]);
            double lat = Double.parseDouble(nextRecord[nextRecord.length-1]);
            Coordinate coordinate = new Coordinate(lon,lat);

            Asset asset = AssetBuilder.builder()
                    .setAddress(nextRecord[1])
                    .setDescription(nextRecord[2])
                    .setUrl(nextRecord[0])
                    .setCoordinate(coordinate)
                    .build();

            ownAssets.add(asset);

        }
//            System.out.println(ownCoordinates);
        return ownAssets;
    }
}