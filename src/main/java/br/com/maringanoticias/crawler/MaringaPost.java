package br.com.maringanoticias.crawler;

import br.com.maringanoticias.domain.news.NewsDTO;
import br.com.maringanoticias.domain.news.NewsService;
import br.com.maringanoticias.domain.sourcenews.SourceNewsDTO;
import br.com.maringanoticias.domain.sourcenews.SourceNewsService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MaringaPost {
    @Autowired
    SourceNewsService sourceNewsService;

    @Autowired
    NewsService newsService;

    public void crawler() throws IOException {
        System.out.println("========================================");
        System.out.println("MARINGA POST");
        System.out.println("========================================");
        SourceNewsDTO sourceNews = this.sourceNewsService.getByCdSourceNews("MARINGA_POST");
        List<NewsDTO> newsList = new ArrayList<>();
        String website = sourceNews.getDsUrl();
        Document doc = Jsoup.connect(website).get();
        Elements elements = doc.select("div.td_module_flex_1.td_module_wrap.td-animation-stack");
        for (Element element : elements) {
            NewsDTO news = new NewsDTO();
            news.setDsTitle(element.select("div.td-module-meta-info > h3.entry-title > a").text());
            news.setDsUrl(element.select("div.td-module-meta-info > h3.entry-title > a").attr("href"));
            String ImageUrl = element.select("div.td-image-container a span ").attr("style");
            news.setDsImageUrl(ImageUrl.substring( ImageUrl.indexOf("https://"), ImageUrl.indexOf(")") ));
            news.setDsDescription(element.select("div.td-module-meta-info > div.td-excerpt").text());
            news.setSourceNews(sourceNews);
            if (!this.newsService.isExistNews(news)) {
                newsList.add(news);
            }
        }
        if (newsList.size() > 0) {
            newsService.saveAllListEntity(newsList);
        }
    }

}
