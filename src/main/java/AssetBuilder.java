public class AssetBuilder {
    String address;
    String description;
    String url;
    Coordinate coordinate;

    public AssetBuilder setAddress(String address){
        this.address = address;
        return this;
    }

    public AssetBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public AssetBuilder setUrl(String url){
        this.url = url;
        return this;
    }

    public AssetBuilder setCoordinate(Coordinate coordinate){
        this.coordinate = coordinate;
        return this;
    }

    public static AssetBuilder builder(){
        return new AssetBuilder();
    }


    public Asset build() {
        return new Asset(address, description, url, coordinate);
    }
}
