package br.com.maringanoticias.domain.funcionalidade;

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
@Table(name = "FUNCIONALIDADE")
@Data
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate
@DynamicInsert
@Where(clause = "DH_DELETE IS NULL")
public class FuncionalidadeEntity extends BaseEntity<FuncionalidadeDTO, Long> {
    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @Column(name = "NM_FUNCIONALIDADE")
    private String nmFuncionalidade;

    @Basic(optional = false)
    @Column(name = "DS_FUNCIONALIDADE")
    private String dsFuncionalidade;

    @Basic(optional = false)
    @Column(name = "CD_FUNCIONALIDADE")
    private String cdFuncionalidade;

    @Override
    public FuncionalidadeDTO getSimpleDTO() {
        return FuncionalidadeDTO.builder().build();
    }
}
