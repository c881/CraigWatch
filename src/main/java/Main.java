import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

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
            String file = "/home/jacov/temp.csv";
            FileReader filereader = new FileReader(file);

            // create csvReader object passing
            // file reader as a parameter
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;
            List<Coordinate> coordinates = new ArrayList<>();

            // we are going to read data line by line
            while ((nextRecord = csvReader.readNext()) != null) {
//                for (String cell : nextRecord) {
//                    System.out.print(cell + "\t");
//                }
//                System.out.println();
                double lon = Double.parseDouble(nextRecord[nextRecord.length-2]);
                double lat = Double.parseDouble(nextRecord[nextRecord.length-1]);
                Coordinate location = new Coordinate(lon,lat);
                coordinates.add(location);

            }
            System.out.println(coordinates);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
