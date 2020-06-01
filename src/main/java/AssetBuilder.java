public class AssetBuilder {
    String address;
    String description;
    String url;
    long dateTime;
    Coordinate coordinate;


    public AssetBuilder setDateTime(long dateTime) {
        this.dateTime = dateTime;
        return this;
    }


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
        Asset asset = new Asset();
        asset.setAddress(address);
        asset.setCoordinate(coordinate);
        asset.setDescription(description);
        asset.setUrl(url);
        asset.setDateTime(dateTime);
        return asset;
    }
}
