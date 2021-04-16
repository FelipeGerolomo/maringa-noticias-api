package br.com.maringanoticias.domain.usuario;

import br.com.maringanoticias.domain.perfil.PerfilEntity;
import br.com.maringanoticias.utils.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;

@Entity
@Table(name = "USUARIO")
@Data
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate
@DynamicInsert
@Where(clause = "DH_DELETE IS NULL")
public class UsuarioEntity extends BaseEntity<UsuarioDTO, Long> implements UserDetails {
    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @Column(name = "USERNAME")
    private String username;

    @Basic(optional = false)
    @Column(name = "PASSWORD")
    private String password;

    @JoinColumn(name = "ID_PERFIL", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER) //EAGER
    private PerfilEntity perfil;

    @Override
    public UsuarioDTO getSimpleDTO() {
        return UsuarioDTO.builder().build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(this.perfil);
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.flAtivo;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.flAtivo;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.flAtivo;
    }
}
