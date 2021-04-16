package br.com.maringanoticias.domain.sourcenews;

import br.com.maringanoticias.utils.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceNewsRepository extends BaseRepository<SourceNewsEntity, Long> {
    SourceNewsEntity getByCdSourceNews(String cdSourceNews);
}
