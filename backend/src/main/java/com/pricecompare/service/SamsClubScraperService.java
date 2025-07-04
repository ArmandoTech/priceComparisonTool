package com.pricecompare.service;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SamsClubScraperService {
    
    private static final String BASE_URL = "https://www.samsclub.com/s/";

    public List<String> searchProducts(String query) throws IOException {
        List<String> results = new ArrayList<>();
        String searchUrl = BASE_URL + query.replace(" ", "%20"); // Replace spaces with %20
        Document doc = Jsoup.connect(searchUrl)
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36")
                        .timeout(10000)
                        .get();

        Elements productCards = doc.select("div.sc-pc-medium-desktop-card-canary.sc-plp-cards-card"); // CSS Classes to find the product cards from Sam's Club
        System.out.println(doc.html());
        for (Element product : productCards) {
            String title = product.select("div.sc-pc-title-medium.title-medium-desktop-canary").text();
            String price = product.select("div.sc-pc-medium-desktop-moneybox-price span").text();

            results.add(title + " - " + price);
        }

        return results;
    }
}
