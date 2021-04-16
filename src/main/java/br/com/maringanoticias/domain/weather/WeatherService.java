package br.com.maringanoticias.domain.weather;

import br.com.maringanoticias.utils.CrudBaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
public class WeatherService extends CrudBaseService<WeatherDTO, WeatherEntity, Long, WeatherRepository, WeatherMapper> {
    public WeatherService(WeatherRepository repository, WeatherMapper mapper) {
        super(repository, mapper);
    }

    public void getWeather() {
        final String uri = "https://api.hgbrasil.com/weather?woeid=12580572&key=676b4a08&fields=only_results,temp,city_name,img_id,description";

        RestTemplate restTemplate = new RestTemplate();
        HGBrasilDTO result = restTemplate.getForObject(uri, HGBrasilDTO.class);

        if (result != null) {
            WeatherDTO weather = new WeatherDTO();
            weather.setDsDescription(result.getDescription());
            weather.setNrTemp(result.getTemp());
            weather.setDsImage(result.getImg_id());
            create(weather);
        }
    }

    public WeatherDTO getLastWeather() {
        return mapper.toDTO(repository.getFirstByOrderByIdDesc());
    }
}
