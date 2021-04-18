package br.com.maringanoticias;

import br.com.maringanoticias.crawler.*;
import br.com.maringanoticias.domain.weather.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;



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

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() throws IOException {
        //this.weatherService.getWeather();
        //this.crawlerService.start();
    }

    @Scheduled(cron = "0 0/2 * * * ?")
    public void scheduleFixedDelayTask() throws IOException {
        //this.weatherService.getWeather();
        //this.crawlerService.start();
    }

}