// Defining POJO.
public class Coordinate {
    String lat;
    String lon;

    public Coordinate() {
    }

    public Coordinate(String lat, String lon) {
        this.lat = lat;
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                '}';
    }
}
