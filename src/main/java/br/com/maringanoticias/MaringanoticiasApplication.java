package br.com.maringanoticias;

import br.com.maringanoticias.crawler.*;
import br.com.maringanoticias.domain.weather.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.TimeZone;


@SpringBootApplication
@EnableScheduling
public class MaringanoticiasApplication {

    @Autowired
    CrawlerService crawlerService;

    @Autowired
    WeatherService weatherService;

    public static void main(String[] args) {
        SpringApplication.run(MaringanoticiasApplication.class, args);
    }

    @PostConstruct
    void started() throws IOException {
        //this.crawlerService.start();
        TimeZone.setDefault(TimeZone.getTimeZone("TimeZone"));
    }

    @Scheduled(cron = "0 0/50 * * * ?")
    public void scheduleFixedDelayTask() throws IOException {
        this.weatherService.getWeather();
        this.crawlerService.start();
    }

}
