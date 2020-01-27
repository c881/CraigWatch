import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

public class Parser {

    public static void main(String[] args) throws IOException {
        String url = "https://sfbay.craigslist.org/search/sfc/apa";

        Set<String> outerLinks = getLinks(url);
        System.out.println(outerLinks);

        String innerUrl = outerLinks.iterator().next();

        Coordinate apartment = getCoordinate(innerUrl);
        System.out.println(apartment);



    }

    private static Coordinate getCoordinate(String innerUrl) throws IOException {
        Document doc = Jsoup.connect(innerUrl).maxBodySize(0).get();

        Elements gps = doc.head().getElementsByAttributeValue("name","geo.position");
        String content = gps.attr("content");
        String[] split = content.split(";");
        return new Coordinate(Double.parseDouble(split[0]),Double.parseDouble(split[1]));
    }

    private static Set<String> getLinks(String url) throws IOException {
        Document doc = Jsoup.connect(url).maxBodySize(0).get();
        Elements allLinkElements = doc.select("li.result-row > a");
        allLinkElements.forEach(e -> e.attr("href"));
        Set<String> hrefs = allLinkElements.stream().
                map(e -> e.attr("href")).
                collect(Collectors.toSet());
        return hrefs;
    }

}
