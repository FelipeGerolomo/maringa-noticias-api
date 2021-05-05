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
public class GMCOnline {

    @Autowired
    SourceNewsService sourceNewsService;

    @Autowired
    NewsService newsService;

    public void crawler() throws IOException {
        System.out.println("========================================");
        System.out.println("GMC ONLINE");
        System.out.println("========================================");
        SourceNewsDTO sourceNews = this.sourceNewsService.getByCdSourceNews("GMC_ONLINE");
        List<NewsDTO> newsList = new ArrayList<>();
        Document doc = Jsoup.connect("https://gmconline.com.br/noticias/cidade/").get();
        Elements elements = doc.select(".noticias-lista article.noticia");
        for (Element element : elements) {
            NewsDTO news = new NewsDTO();
            news.setDsTitle(element.select(".article-title").text());
            news.setDsUrl(element.select(".article-title a").attr("href"));
            news.setDsImageUrl(element.select("img").attr("data-src"));
            news.setDsDescription(element.select(".article-title").text());
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
