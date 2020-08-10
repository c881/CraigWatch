import com.j256.ormlite.field.DatabaseField;

public class Asset {
    @DatabaseField(id=true)
    private long postId;

    @DatabaseField
    String address;

    @DatabaseField
    String description;

    @DatabaseField
    String url;

    @DatabaseField
    double lon;

    @DatabaseField
    double lat;

    @DatabaseField
    long dateTime;

    public Asset() {
    }
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public long getDateTime() {
        return dateTime;
    }

    public void setDateTime(long dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return  "address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", coordinate=" + '\'' +
                ", posted=" + dateTime;
    }
}
