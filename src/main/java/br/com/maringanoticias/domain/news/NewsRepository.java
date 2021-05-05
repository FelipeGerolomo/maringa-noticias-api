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

    @Query(nativeQuery = true, value = "SELECT N.* FROM public.news AS N " +
            "INNER JOIN (" +
            "SELECT id_news, COUNT(*) FROM public.news_log " +
            "WHERE dh_insert >= now() - interval '2 week' " +
            "GROUP BY id_news HAVING COUNT(*) > 1 " +
            "ORDER BY count desc )" +
            "AS NL on N.id = NL.id_news")
    List<NewsEntity> findMostWeekViewed();
}
