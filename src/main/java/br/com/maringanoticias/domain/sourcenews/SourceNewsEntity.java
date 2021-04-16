package br.com.maringanoticias.domain.sourcenews;

import br.com.maringanoticias.utils.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "SOURCE_NEWS")
@Data
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate
@DynamicInsert
@Where(clause = "DH_DELETE IS NULL")
public class SourceNewsEntity extends BaseEntity<SourceNewsDTO, Long> {
    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @Column(name = "NM_SOURCE_NEWS")
    private String nmSourceNews;

    @Basic(optional = false)
    @Column(name = "CD_SOURCE_NEWS")
    private String cdSourceNews;

    @Basic(optional = false)
    @Column(name = "DS_URL")
    private String dsUrl;

    @Basic(optional = false)
    @Column(name = "DS_LABEL_IMAGE")
    private String dsLabelImage;

    @Basic(optional = false)
    @Column(name = "DS_LABEL_ICON")
    private String dsLabelIcon;

    @Override
    public SourceNewsDTO getSimpleDTO() {
        return SourceNewsDTO.builder().build();
    }

}
