public class AssetBuilder {
    long postId;
    String address;
    String description;
    String url;
    long dateTime;
    Coordinate coordinate;


    public AssetBuilder setPostId(long postId) {
        this.postId = postId;
        return this;
    }

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
        asset.setPostId(postId);
        asset.setAddress(address);
        asset.setLat(coordinate.lat);
        asset.setLon(coordinate.lon);
        asset.setDescription(description);
        asset.setUrl(url);
        asset.setDateTime(dateTime);
        return asset;
    }
}
