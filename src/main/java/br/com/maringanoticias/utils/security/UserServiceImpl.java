package br.com.maringanoticias.utils.security;

import br.com.maringanoticias.domain.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioService usuarioService;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return usuarioService.findFirstByLogin(username).orElseThrow(() -> new UsernameNotFoundException(username));
	}

}
