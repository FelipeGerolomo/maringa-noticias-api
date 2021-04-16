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
public class PlantaoMaringa {
    @Autowired
    SourceNewsService sourceNewsService;

    @Autowired
    NewsService newsService;

    public void crawler() throws IOException {
        SourceNewsDTO sourceNews = this.sourceNewsService.getByCdSourceNews("PLANTAO_MARINGA");
        List<NewsDTO> newsList = new ArrayList<>();
        String website = sourceNews.getDsUrl();
        Document doc = Jsoup.connect(website).get();
        Elements elements = doc.select("div.col-md-12 > div.box_branca");
        for (Element element : elements) {
            NewsDTO news = new NewsDTO();
            news.setDsTitle(element.select("h2.hidden-xs").text());
            news.setDsUrl(element.select("div.desc_noticia a").attr("href"));
            news.setDsImageUrl(element.select("img").attr("src"));
            news.setDsDescription(element.select("div.desc_noticia p").text());
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
