package com.pricecompare.controller;

import com.pricecompare.service.SamsClubScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/samsclub")
public class SamsClubController {

    @Autowired
    private SamsClubScraperService scraperService;

    @GetMapping("/scrape")
    public List<String> scrape(@RequestParam String query) {
        try {
            return scraperService.searchProducts(query);
        } catch (Exception e) {
            return List.of("Error: " + e.getMessage());
        }
    }
}
