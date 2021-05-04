package br.com.maringanoticias.controller;


import br.com.maringanoticias.crawler.CrawlerService;
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
    public boolean findMostWeekViewed() throws IOException {
        this.crawlerService.start();
        return true;
    }
}
