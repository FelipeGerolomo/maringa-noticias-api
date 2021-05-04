package br.com.maringanoticias.controller;


import br.com.maringanoticias.crawler.CrawlerService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/crawler")
public class CrawlerController {

    @Autowired
    CrawlerService crawlerService;

    @RequestMapping(method = RequestMethod.GET, value = "")
    public boolean start() throws IOException {
        this.crawlerService.start();
        return true;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/maringa-post")
    public String findMostWeekViewed() throws IOException {
        Document doc = Jsoup.connect("https://maringapost.com.br/").get();
        return doc.toString();
    }
}
