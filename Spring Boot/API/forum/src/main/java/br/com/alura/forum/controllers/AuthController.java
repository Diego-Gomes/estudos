package br.com.alura.forum.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.configuracao.security.TokenService;
import br.com.alura.forum.dto.JWTDto;
import br.com.alura.forum.dto.LoginForm;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/auth")
@Api(tags="Autenticação")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager ;

	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<?> autenticar(@RequestBody @Valid LoginForm  login){
		UsernamePasswordAuthenticationToken dadosLogin = login.converter();
		try {
			Authentication authenticate = authenticationManager.authenticate(dadosLogin);
			String token = tokenService.gerarToken(authenticate);
			
			return ResponseEntity.ok(new JWTDto(token, "Bearer"));
			
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
			
	}
	
}
