package br.com.maringanoticias.domain.newslog;

import br.com.maringanoticias.utils.CrudBaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NewsLogService extends CrudBaseService<NewsLogDTO, NewsLogEntity, Long, NewsLogRepository, NewsLogMapper> {
    public NewsLogService(NewsLogRepository repository, NewsLogMapper mapper) {
        super(repository, mapper);
    }
}
