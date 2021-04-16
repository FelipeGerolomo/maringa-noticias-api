package br.com.maringanoticias.domain.newslog;

import br.com.maringanoticias.domain.news.NewsEntity;
import br.com.maringanoticias.domain.sourcenews.SourceNewsEntity;
import br.com.maringanoticias.utils.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "NEWS_LOG")
@Data
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate
@DynamicInsert
@Where(clause = "DH_DELETE IS NULL")
public class NewsLogEntity extends BaseEntity<NewsLogDTO, Long> {
    private static final long serialVersionUID = 1L;

    @JoinColumn(name = "ID_NEWS", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private NewsEntity news;

    @Override
    public NewsLogDTO getSimpleDTO() {
        return NewsLogDTO.builder().build();
    }

}
