package br.com.maringanoticias.domain.newslog;

import br.com.maringanoticias.domain.news.NewsDTO;
import br.com.maringanoticias.utils.BaseDTO;
import lombok.*;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
public class NewsLogDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private NewsDTO news;
}
