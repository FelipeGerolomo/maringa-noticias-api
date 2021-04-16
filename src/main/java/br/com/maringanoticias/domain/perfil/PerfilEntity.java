package br.com.maringanoticias.domain.perfil;

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
@Table(name = "PERFIL")
@Data
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate
@DynamicInsert
@Where(clause = "DH_DELETE IS NULL")
public class PerfilEntity extends BaseEntity<PerfilDTO, Long> implements GrantedAuthority {
    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @Column(name = "NM_PERFIL")
    private String nmPerfil;

    @Basic(optional = false)
    @Column(name = "DS_PERFIL")
    private String dsPerfil;

    @Basic(optional = false)
    @Column(name = "CD_PERFIL")
    private String cdPerfil;

    @Override
    public PerfilDTO getSimpleDTO() {
        return PerfilDTO.builder().build();
    }

    @Override
    public String getAuthority() {
        return this.cdPerfil;
    }
}
