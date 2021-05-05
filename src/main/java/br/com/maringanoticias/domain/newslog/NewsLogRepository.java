package br.com.maringanoticias.domain.newslog;

import br.com.maringanoticias.domain.news.NewsEntity;
import br.com.maringanoticias.utils.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsLogRepository extends BaseRepository<NewsLogEntity, Long> {

}
