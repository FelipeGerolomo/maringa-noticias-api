package br.com.maringanoticias.crawler;

import br.com.maringanoticias.domain.news.NewsDTO;
import br.com.maringanoticias.domain.news.NewsService;
import br.com.maringanoticias.domain.sourcenews.SourceNewsDTO;
import br.com.maringanoticias.domain.sourcenews.SourceNewsEntity;
import br.com.maringanoticias.domain.sourcenews.SourceNewsService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MaringaNaHora {
    @Autowired
    SourceNewsService sourceNewsService;

    @Autowired
    NewsService newsService;

    public void crawler() throws IOException {
        SourceNewsDTO sourceNews = this.sourceNewsService.getByCdSourceNews("MARINGA_HORA");
        List<NewsDTO> newsList = new ArrayList<>();
        String website = sourceNews.getDsUrl();
        Document doc = Jsoup.connect(website).get();
        Elements elements = doc.select("div.col-sm-6");
        for (Element element : elements) {
            NewsDTO news = new NewsDTO();
            news.setDsTitle(element.select("div.post_caption > a.wow.fadeInUp.heding").text());
            news.setDsUrl(website.concat(element.select("div.post_caption > a.wow.fadeInUp.heding").attr("href")));
            news.setDsImageUrl(website.concat(element.select("div.font_load_post > a > img").attr("src")));
            news.setDsDescription(element.select("div.font_load_post > .post_caption > a:last-child").text());
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
