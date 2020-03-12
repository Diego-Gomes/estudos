package br.com.alura.forum.configuracao.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.alura.forum.data.repository.IUsuarioRepository;
import br.com.alura.forum.model.Usuario;

public class AutenticacaoUsuarioFilter extends OncePerRequestFilter {

	private TokenService tokenService;

	private IUsuarioRepository iUsuarioRepository;

	public AutenticacaoUsuarioFilter(TokenService tokenService, IUsuarioRepository iUsuarioRepository) {
		this.tokenService = tokenService;
		this.iUsuarioRepository = iUsuarioRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = recuperaToken(request);

		boolean isValido = tokenService.isTokenValido(token);

		if (isValido) {
			Autenticar(token);
		}

		filterChain.doFilter(request, response);
	}

	private String recuperaToken(HttpServletRequest request) {

		String authorization = request.getHeader("Authorization");
		String prefix = "Bearer ";

		if (authorization == null || authorization.isEmpty() || !authorization.startsWith(prefix))
			return null;

		return authorization.substring(prefix.length());
	}

	private void Autenticar(String token) {
		
		Long idUsuario = tokenService.getIdUsuario(token);
		
		Optional<Usuario> optional = iUsuarioRepository.findById(idUsuario);

		if(optional.isPresent()) {
			
			Usuario usuario = optional.get();
		
			UsernamePasswordAuthenticationToken authentication = 
				new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
		
			SecurityContextHolder.getContext().setAuthentication(authentication);

		}

	}

}
