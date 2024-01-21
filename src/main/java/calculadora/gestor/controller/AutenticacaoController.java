package calculadora.gestor.controller;

import calculadora.gestor.domain.usuario.DadosAutenticacao;
import calculadora.gestor.domain.usuario.DadosTokenJWT;
import calculadora.gestor.infraestrutura.security.TokenService;
import calculadora.gestor.domain.usuario.Usuario;
import calculadora.gestor.repository.UsuarioRepository;
import calculadora.gestor.domain.usuario.DadosRegistro;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(usernamePassword);

        var token = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJWT(token));
    }

    @PostMapping("/registrar")
    public ResponseEntity registrar(@RequestBody @Valid DadosRegistro dados) {
        if (usuarioRepository.findByLogin(dados.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(dados.senha());
        Usuario novoUsuario = new Usuario(dados.login(), encryptedPassword, dados.role());

        usuarioRepository.save(novoUsuario);
        return ResponseEntity.ok().build();
    }

}
