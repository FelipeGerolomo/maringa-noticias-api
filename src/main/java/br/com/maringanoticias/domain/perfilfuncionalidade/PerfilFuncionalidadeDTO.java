package br.com.maringanoticias.domain.perfilfuncionalidade;

import br.com.maringanoticias.domain.funcionalidade.FuncionalidadeDTO;
import br.com.maringanoticias.domain.perfil.PerfilDTO;
import br.com.maringanoticias.utils.BaseDTO;
import lombok.*;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
public class PerfilFuncionalidadeDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private PerfilDTO perfil;

    private FuncionalidadeDTO funcionalidade;
}
