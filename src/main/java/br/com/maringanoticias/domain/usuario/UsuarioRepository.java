package br.com.maringanoticias.domain.usuario;

import br.com.maringanoticias.utils.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends BaseRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findFirstByUsername(String username);
}
