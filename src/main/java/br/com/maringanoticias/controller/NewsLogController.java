package br.com.maringanoticias.controller;

import br.com.maringanoticias.domain.newslog.NewsLogDTO;
import br.com.maringanoticias.domain.newslog.NewsLogEntity;
import br.com.maringanoticias.domain.newslog.NewsLogService;
import br.com.maringanoticias.utils.CrudBaseController;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/news-log")
public class NewsLogController extends CrudBaseController<Long, NewsLogDTO, NewsLogService> {

    public NewsLogController(NewsLogService service) {
        super(service);
    }

    @Override
    public Page<NewsLogDTO>
    find(@QuerydslPredicate(root = NewsLogEntity.class) Predicate predicate, Pageable pageable) {
        return super.find(predicate, pageable);
    }

    @Override
    public List<NewsLogDTO> find(@QuerydslPredicate(root = NewsLogEntity.class) Predicate predicate) {
        return super.find(predicate);
    }
}