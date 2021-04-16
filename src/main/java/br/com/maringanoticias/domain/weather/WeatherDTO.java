package br.com.maringanoticias.domain.weather;

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
public class WeatherDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nrTemp;

    private String dsDescription;

    private String dsImage;
}
