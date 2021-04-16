package br.com.maringanoticias.domain.newslog;

import br.com.maringanoticias.utils.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsLogRepository extends BaseRepository<NewsLogEntity, Long> {

}
