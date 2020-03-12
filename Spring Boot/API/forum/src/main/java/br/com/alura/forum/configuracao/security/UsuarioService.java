package br.com.alura.forum.configuracao.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.alura.forum.data.repository.IUsuarioRepository;
import br.com.alura.forum.model.Usuario;

@Service
public class UsuarioService implements UserDetailsService {

	@Autowired
	private IUsuarioRepository iUsuariorRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
	 Optional<Usuario>	usuario = iUsuariorRepository.findByEmail(username);
	
	 if(usuario.isPresent()) {
		 return usuario.get();
	 }
	 
	 throw new UsernameNotFoundException("Dados inválidos");
	
	 
	}

}
