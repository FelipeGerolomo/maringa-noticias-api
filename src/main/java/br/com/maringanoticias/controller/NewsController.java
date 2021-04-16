package br.com.maringanoticias.controller;

import br.com.maringanoticias.domain.news.NewsDTO;
import br.com.maringanoticias.domain.news.NewsEntity;
import br.com.maringanoticias.domain.news.NewsService;
import br.com.maringanoticias.utils.CrudBaseController;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController extends CrudBaseController<Long, NewsDTO, NewsService> {

    public NewsController(NewsService service) {
        super(service);
    }

    @Override
    public Page<NewsDTO>
    find(@QuerydslPredicate(root = NewsEntity.class) Predicate predicate, Pageable pageable) {
        return super.find(predicate, pageable);
    }

    @Override
    public List<NewsDTO> find(@QuerydslPredicate(root = NewsEntity.class) Predicate predicate) {
        return super.find(predicate);
    }
}