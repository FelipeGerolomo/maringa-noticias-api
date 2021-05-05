package br.com.maringanoticias.controller;

import br.com.maringanoticias.domain.sourcenews.SourceNewsDTO;
import br.com.maringanoticias.domain.sourcenews.SourceNewsEntity;
import br.com.maringanoticias.domain.sourcenews.SourceNewsService;
import br.com.maringanoticias.utils.CrudBaseController;
import com.querydsl.core.types.Predicate;
import org.apache.commons.io.IOUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/source-news")
public class SourceNewsController extends CrudBaseController<Long, SourceNewsDTO, SourceNewsService> {

    public SourceNewsController(SourceNewsService service) {
        super(service);
    }

    @Override
    public Page<SourceNewsDTO>
    find(@QuerydslPredicate(root = SourceNewsEntity.class) Predicate predicate, Pageable pageable) {
        return super.find(predicate, pageable);
    }

    @Override
    public List<SourceNewsDTO> find(@QuerydslPredicate(root = SourceNewsEntity.class) Predicate predicate) {
        return super.find(predicate);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/icon/{image}", produces = "image/svg+xml")
    public @ResponseBody
    byte[] getIconWeather(@PathVariable("image") String image) throws IOException {
        InputStream in = getClass().getResourceAsStream("/images/sourcenews/" + image);
        return IOUtils.toByteArray(in);
    }

}