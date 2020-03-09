public class Asset {
    String address;
    String description;
    String url;
    Coordinate coordinate;

    public Asset() {
    }

    public Asset(String address, String description, String url, Coordinate coordinate) {
        this.address = address;
        this.description = description;
        this.url = url;
        this.coordinate = coordinate;
    }
}
