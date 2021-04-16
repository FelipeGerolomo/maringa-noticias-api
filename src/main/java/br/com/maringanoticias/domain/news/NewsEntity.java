package br.com.maringanoticias.domain.news;

import br.com.maringanoticias.domain.sourcenews.SourceNewsEntity;
import br.com.maringanoticias.utils.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "NEWS")
@Data
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate
@DynamicInsert
@Where(clause = "DH_DELETE IS NULL")
public class NewsEntity extends BaseEntity<NewsDTO, Long> {
    private static final long serialVersionUID = 1L;

    @Basic(optional = true)
    @Column(name = "DS_TITLE")
    private String dsTitle;

    @Basic(optional = false)
    @Column(name = "DS_URL")
    private String dsUrl;

    @Basic(optional = true)
    @Column(name = "DS_DESCRIPTION")
    private String dsDescription;

    @Basic(optional = true)
    @Column(name = "DS_IMAGE_URL")
    private String dsImageUrl;

    @JoinColumn(name = "ID_SOURCE_NEWS", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SourceNewsEntity sourceNews;

    @Override
    public NewsDTO getSimpleDTO() {
        return NewsDTO.builder().build();
    }

}
