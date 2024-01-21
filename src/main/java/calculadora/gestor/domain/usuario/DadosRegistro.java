package calculadora.gestor.domain.usuario;

import calculadora.gestor.domain.usuario.Role;

public record DadosRegistro(
        String login,
        String senha,
        Role role
) {
}
