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
public class AndreAlmenara {

    @Autowired
    SourceNewsService sourceNewsService;

    @Autowired
    NewsService newsService;

    public void crawler() throws IOException {
        System.out.println("========================================");
        System.out.println("ANDRE ALMENARA");
        System.out.println("========================================");
        SourceNewsDTO sourceNews = this.sourceNewsService.getByCdSourceNews("ANDRE_ALMENARA");
        List<NewsDTO> newsList = new ArrayList<>();
        String website = sourceNews.getDsUrl();
        Document doc = Jsoup.connect(website).get();
        Elements elements = doc.select("div.pp-content-post.pp-content-grid-post");
        for (Element element : elements) {
            NewsDTO news = new NewsDTO();
            news.setDsTitle(element.select("meta[itemprop='mainEntityOfPage']").attr("content"));
            news.setDsUrl(element.select("meta[itemprop='mainEntityOfPage']").attr("itemid"));
            news.setDsImageUrl(element.select("div[itemprop='image'] > meta[itemprop='url']").attr("content"));
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
