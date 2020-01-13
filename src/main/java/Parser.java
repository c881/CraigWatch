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

        //Set<String> outerLinks = getLinks(url);
        //System.out.println(hrefs);
        String innerUrl = "https://sfbay.craigslist.org/sfc/apa/d/san-francisco-brand-new-one-bedroom/7055387735.html";
        Document doc = Jsoup.connect(innerUrl).maxBodySize(0).get();

        Elements gps = doc.head().getElementsByAttributeValue("name","geo.position");
        String content = gps.attr("content");
        //System.out.println(doc.head());
        System.out.println(gps);
        System.out.println(content);
        String[] split = content.split(";");
        Coordinate apartment = new Coordinate(split[0],split[1]);
        System.out.println(apartment);



    }

    private static Set<String> getLinks(String url) throws IOException {
        Document doc = Jsoup.connect(url).maxBodySize(0).get();
        Elements allLinkElements = doc.select("li.result-row > a");
        allLinkElements.forEach(e -> e.attr("href"));
        Set<String> hrefs = allLinkElements.stream().map(e -> e.attr("href")).collect(Collectors.toSet());
        return hrefs;
    }

}
