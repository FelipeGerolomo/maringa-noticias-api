package br.com.maringanoticias.domain.weather;

import br.com.maringanoticias.utils.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "WEATHER")
@Data
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate
@DynamicInsert
@Where(clause = "DH_DELETE IS NULL")
public class WeatherEntity extends BaseEntity<WeatherDTO, Long> {
    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @Column(name = "NR_TEMP")
    private String nrTemp;

    @Basic(optional = false)
    @Column(name = "DS_DESCRIPTION")
    private String dsDescription;

    @Basic(optional = false)
    @Column(name = "DS_IMAGE")
    private String dsImage;

    @Override
    public WeatherDTO getSimpleDTO() {
        return WeatherDTO.builder().build();
    }

}
