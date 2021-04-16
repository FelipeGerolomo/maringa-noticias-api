package br.com.maringanoticias.domain.usuario;

import br.com.maringanoticias.utils.CrudBaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService extends CrudBaseService<UsuarioDTO, UsuarioEntity, Long, UsuarioRepository, UsuarioMapper> {
    public UsuarioService(UsuarioRepository repository, UsuarioMapper mapper) {
        super(repository, mapper);
    }

    public Optional<UsuarioEntity> findFirstByLogin(String username) {
        return repository.findFirstByUsername(username);
    }

    public UsuarioDTO convertToDTO(UsuarioEntity usuarioEntity) {
        return mapper.toDTO(usuarioEntity);
    }

}
