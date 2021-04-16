package br.com.maringanoticias.domain.sourcenews;

import br.com.maringanoticias.utils.CrudBaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SourceNewsService extends CrudBaseService<SourceNewsDTO, SourceNewsEntity, Long, SourceNewsRepository, SourceNewsMapper> {
    public SourceNewsService(SourceNewsRepository repository, SourceNewsMapper mapper) {
        super(repository, mapper);
    }

    public SourceNewsDTO getByCdSourceNews(String cdSourceNews) {
        return mapper.toDTO(repository.getByCdSourceNews(cdSourceNews));
    }

}
