package br.com.maringanoticias.domain.weather;

import br.com.maringanoticias.utils.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends BaseRepository<WeatherEntity, Long> {
    WeatherEntity getFirstByOrderByIdDesc();
}
