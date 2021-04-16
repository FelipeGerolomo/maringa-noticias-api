package br.com.maringanoticias.domain.perfil;

import br.com.maringanoticias.utils.BaseDTO;
import lombok.*;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
public class PerfilDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nmPerfil;

    private String dsPerfil;

    private String cdPerfil;
    
}
