package br.com.maringanoticias.crawler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@Transactional
public class CrawlerService {

    @Autowired
    MaringaNaHora maringaNaHora;

    @Autowired
    AndreAlmenara andreAlmenara;

    @Autowired
    GMCOnline gmcOnline;

    @Autowired
    MaringaPost maringaPost;

    @Autowired
    PlantaoMaringa plantaoMaringa;

    public void start() throws IOException {
        this.maringaNaHora.crawler();
        this.andreAlmenara.crawler();
        this.gmcOnline.crawler();
        this.maringaPost.crawler();
        this.plantaoMaringa.crawler();
    }
}
