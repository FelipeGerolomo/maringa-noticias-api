package br.com.maringanoticias.domain.usuario;

import br.com.maringanoticias.domain.perfil.PerfilDTO;
import br.com.maringanoticias.utils.BaseDTO;
import lombok.*;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
public class UsuarioDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

    private PerfilDTO perfil;
}
