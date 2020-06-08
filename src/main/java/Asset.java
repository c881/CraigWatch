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
    Coordinate coordinate;
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

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
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
                ", coordinate=" + coordinate+ '\'' +
                ", posted=" + dateTime;
    }
}
