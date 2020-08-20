import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;


public class Parser implements Runnable{   // - for using as threads.

    public static void main(String[] args) throws IOException {
        String url = "https://sfbay.craigslist.org/search/sfc/apa";

        Set<String> outerLinks = getLinks(url);
//        System.out.println(outerLinks);


        String innerUrl = outerLinks.iterator().next();

        Asset apartment = getCraigAsset(innerUrl);
//        System.out.println(apartment);

    }

    @Override
    public void run() {
        
    }

    public static CraigAsset getCraigAsset (String innerUrl) throws IOException {
        AssetBuilder assetBuilder = getAssetBuilder(innerUrl);
        return assetBuilder.buildCraigAsset();
    }

    public static UserAsset getUserAsset(String innerUrl) throws IOException {
        AssetBuilder assetBuilder = getAssetBuilder(innerUrl);
        return assetBuilder.buildUserAsset();
    }

    private static AssetBuilder getAssetBuilder(String innerUrl) throws IOException {
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
//            System.out.println(postinginfoList);
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
        AssetBuilder assetBuilder = AssetBuilder.builder()
                .setPostId(postId)
                .setAddress(address)
                .setDescription(description)
                .setUrl(innerUrl)
                .setCoordinate(coordinate)
                .setDateTime(timeStamp);
        return assetBuilder;
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