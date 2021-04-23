package br.com.maringanoticias.controller;

import br.com.maringanoticias.domain.weather.WeatherDTO;
import br.com.maringanoticias.domain.weather.WeatherEntity;
import br.com.maringanoticias.domain.weather.WeatherService;
import br.com.maringanoticias.utils.CrudBaseController;
import com.querydsl.core.types.Predicate;
import org.apache.commons.io.IOUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController extends CrudBaseController<Long, WeatherDTO, WeatherService> {

    public WeatherController(WeatherService service) {
        super(service);
    }

    @Override
    public Page<WeatherDTO>
    find(@QuerydslPredicate(root = WeatherEntity.class) Predicate predicate, Pageable pageable) {
        return super.find(predicate, pageable);
    }

    @Override
    public List<WeatherDTO> find(@QuerydslPredicate(root = WeatherEntity.class) Predicate predicate) {
        return super.find(predicate);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/last")
    public WeatherDTO getLastWeather() {
        return service.getLastWeather();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/icon/{image}", produces = "image/svg+xml")
    public @ResponseBody
    byte[] getIconWeather(@PathVariable("image") String image) throws IOException {
        InputStream in = getClass().getResourceAsStream("/images/weather/" + image + ".svg");
        return IOUtils.toByteArray(in);
    }
}