package br.com.maringanoticias.domain.news;

import br.com.maringanoticias.utils.CrudBaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Service
@Transactional
public class NewsService extends CrudBaseService<NewsDTO, NewsEntity, Long, NewsRepository, NewsMapper> {
    public NewsService(NewsRepository repository, NewsMapper mapper) {
        super(repository, mapper);
    }

    public NewsDTO getByDsUrl(String dsUrl) {
        return mapper.toDTO(repository.getByDsUrl(dsUrl));
    }

    public boolean isExistNews(NewsDTO news) {
        NewsEntity newsEntity = repository.getByDsUrl(news.getDsUrl());
        if (newsEntity == null) {
            return false;
        }
        return true;
    }

    public List<NewsDTO> findByDsTitle(String dsTitle) {
        return mapper.toDTO(repository.findTop1ByDsTitle(dsTitle));
    }

}
