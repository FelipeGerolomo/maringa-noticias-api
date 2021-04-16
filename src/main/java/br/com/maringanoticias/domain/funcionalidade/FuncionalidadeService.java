package br.com.maringanoticias.domain.funcionalidade;

import br.com.maringanoticias.utils.CrudBaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FuncionalidadeService extends
        CrudBaseService<FuncionalidadeDTO, FuncionalidadeEntity, Long, FuncionalidadeRepository, FuncionalidadeMapper> {
    public FuncionalidadeService(FuncionalidadeRepository repository, FuncionalidadeMapper mapper) {
        super(repository, mapper);
    }
}
