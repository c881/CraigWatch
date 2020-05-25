import com.opencsv.CSVReader;

import java.io.FileReader;
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
            String fileName = "resources/MyApartments.csv";
            FileReader filereader = new FileReader(fileName);

            // create csvReader object passing
            // file reader as a parameter
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;
            List<Asset> ownAssets = new ArrayList<>();

            // we are going to read data line by line
            while ((nextRecord = csvReader.readNext()) != null) {
                Asset asset = new Asset();

                double lon = Double.parseDouble(nextRecord[nextRecord.length-2]);
                double lat = Double.parseDouble(nextRecord[nextRecord.length-1]);
                Coordinate coordinate = new Coordinate(lon,lat);

                asset.coordinate = coordinate;
                asset.url = nextRecord[0];
                asset.address = nextRecord[1];

                ownAssets.add(asset);

            }
//            System.out.println(ownCoordinates);
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
                    AssetsWrapper assetsWrapper = new AssetsWrapper(own, forRent,
                            calculator.calculate(own.coordinate, forRent.coordinate));
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
}