package br.com.maringanoticias.domain.perfil;

import br.com.maringanoticias.utils.CrudBaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PerfilService extends CrudBaseService<PerfilDTO, PerfilEntity, Long, PerfilRepository, PerfilMapper> {
    public PerfilService(PerfilRepository repository, PerfilMapper mapper) {
        super(repository, mapper);
    }
}
