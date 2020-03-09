public class AssetBuilder {
    String address;
    String description;
    String url;
    Coordinate coordinate;

    public AssetBuilder setAddress(String address){
        this.address = address;
        return this;
    }

    public AssetBuilder setUrl(String Url){
        this.url = url;
        return this;
    }

    public AssetBuilder setCoordinate(Coordinate coordinate){
        this.coordinate = coordinate;
        return this;
    }


    public Asset createAsset() {
        return new Asset(address, description, url, coordinate);
    }
}