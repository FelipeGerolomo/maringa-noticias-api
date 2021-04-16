package br.com.maringanoticias.domain.perfilfuncionalidade;

import br.com.maringanoticias.domain.funcionalidade.FuncionalidadeEntity;
import br.com.maringanoticias.domain.perfil.PerfilEntity;
import br.com.maringanoticias.utils.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "PERFIL_FUNCIONALIDADE")
@Data
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate
@DynamicInsert
@Where(clause = "DH_DELETE IS NULL")
public class PerfilFuncionalidadeEntity extends BaseEntity<PerfilFuncionalidadeDTO, Long> {
    private static final long serialVersionUID = 1L;

    @JoinColumn(name = "ID_PERFIL", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PerfilEntity perfil;

    @JoinColumn(name = "ID_FUNCIONALIDADE", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private FuncionalidadeEntity funcionalidade;

    @Override
    public PerfilFuncionalidadeDTO getSimpleDTO() {
        return PerfilFuncionalidadeDTO.builder().build();
    }
}
