package br.com.maringanoticias.domain.news;

import br.com.maringanoticias.domain.sourcenews.SourceNewsDTO;
import br.com.maringanoticias.utils.BaseDTO;
import lombok.*;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
public class NewsDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String dsTitle;

    private String dsUrl;

    private String dsDescription;

    private String dsImageUrl;

    private SourceNewsDTO sourceNews;

    
}
