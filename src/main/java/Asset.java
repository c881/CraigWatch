import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "assets")
public class Asset {
    @DatabaseField(generatedId = true)
    private int id;

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
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
