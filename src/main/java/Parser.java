import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;


// צריכים להוציא את titletextonly ואת div class="mapaddress"
// להפוך את העבודה ל -ASSET במקום COORDINATE

public class Parser {

    public static void main(String[] args) throws IOException {
        String url = "https://sfbay.craigslist.org/search/sfc/apa";

        Set<String> outerLinks = getLinks(url);
        System.out.println(outerLinks);

// לעבור על כל רשימת הלינקים לשלוף את כל ה-ASSESTS

        String innerUrl = outerLinks.iterator().next();

        Asset apartment = getAsset(innerUrl);
        System.out.println(apartment);

    }

    public static Asset getAsset(String innerUrl) throws IOException {
        Document doc = Jsoup.connect(innerUrl).maxBodySize(0).get();
        long timeStamp = 0;

        Elements gps = doc.head().getElementsByAttributeValue("name","geo.position");
        String gpsContent = gps.attr("content");
        String[] split = gpsContent.split(";");

        String description = doc.getElementById("titletextonly").text();
        Elements date_timeago = doc.getElementById("display-date").getElementsByTag("time");
        if (!date_timeago.isEmpty()) {
            String date_time = date_timeago.first().attr("datetime");
            timeStamp = TimeUtil.getEpoch(date_time);
        }

        Coordinate coordinate = new Coordinate(Double.parseDouble(split[0]),Double.parseDouble(split[1]));
        String address = "";
        try {
            address = doc.select("div.mapaddress").text();
        }
        catch (Exception e){
        }
        Elements postinginfos = doc.getElementsByClass("postinginfos");
        long postId = -1;
        if (postinginfos != null && !postinginfos.isEmpty()) {
            Element postingInfo0 = postinginfos.get(0);
            Elements postinginfoList = postingInfo0.getElementsByClass("postinginfo");
            System.out.println(postinginfoList);
            for (Element postingInfo : postinginfoList) {
                String text = postingInfo.text();
                if (text != null && text.contains("post id")){
                    String[] splitArr = text.split(" ");
                    if(splitArr != null && splitArr.length == 3){
                        try {
                            postId = Long.parseLong(splitArr[2]);
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        Asset asset = AssetBuilder.builder()
                                .setPostId(postId)
                                .setAddress(address)
                                .setDescription(description)
                                .setUrl(innerUrl)
                                .setCoordinate(coordinate)
                                .setDateTime(timeStamp)
                                .build();
        return asset;
    }

    public static Set<String> getLinks(String url) throws IOException {
        Document doc = Jsoup.connect(url).maxBodySize(0).get();
        Elements allLinkElements = doc.select("li.result-row > a");
        allLinkElements.forEach(e -> e.attr("href"));
        Set<String> hrefs = allLinkElements.stream().
                map(e -> e.attr("href")).
                collect(Collectors.toSet());
        return hrefs;
    }

}