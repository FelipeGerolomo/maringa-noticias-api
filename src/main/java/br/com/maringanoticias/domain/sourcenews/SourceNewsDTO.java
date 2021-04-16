package br.com.maringanoticias.domain.sourcenews;

import br.com.maringanoticias.utils.BaseDTO;
import lombok.*;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
public class SourceNewsDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nmSourceNews;

    private String cdSourceNews;

    private String dsUrl;

    private String dsLabelImage;

    private String dsLabelIcon;

    
}
