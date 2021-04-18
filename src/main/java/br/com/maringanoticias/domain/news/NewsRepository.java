package br.com.maringanoticias.domain.news;

import br.com.maringanoticias.utils.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends BaseRepository<NewsEntity, Long> {
    NewsEntity getByDsUrl(String dsUrl);

    @Query("Select n from NewsEntity n where n.dsTitle LIKE  %?1% ")
    List<NewsEntity> findTop1ByDsTitle(String dsTitle);
}
