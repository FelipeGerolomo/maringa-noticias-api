package br.com.maringanoticias.domain.funcionalidade;

import br.com.maringanoticias.utils.BaseDTO;
import lombok.*;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
public class FuncionalidadeDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nmFuncionalidade;

    private String dsFuncionalidade;

    private String cdFuncionalidade;
}
