package br.com.maringanoticias.domain.news;

import br.com.maringanoticias.utils.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends BaseRepository<NewsEntity, Long> {
    NewsEntity getByDsUrl(String dsUrl);
}
