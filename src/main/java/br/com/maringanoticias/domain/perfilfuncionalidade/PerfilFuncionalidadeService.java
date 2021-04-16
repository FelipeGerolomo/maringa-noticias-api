package br.com.maringanoticias.domain.perfilfuncionalidade;

import br.com.maringanoticias.utils.CrudBaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PerfilFuncionalidadeService extends CrudBaseService<PerfilFuncionalidadeDTO, PerfilFuncionalidadeEntity, Long, PerfilFuncionalidadeRepository, PerfilFuncionalidadeMapper> {
    public PerfilFuncionalidadeService(PerfilFuncionalidadeRepository repository, PerfilFuncionalidadeMapper mapper) {
        super(repository, mapper);
    }
}
