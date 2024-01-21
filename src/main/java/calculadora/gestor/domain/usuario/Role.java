package calculadora.gestor.domain.usuario;

public enum Role {
    ADMINISTRADOR("administrador"),
    USUARIO("usuario");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
