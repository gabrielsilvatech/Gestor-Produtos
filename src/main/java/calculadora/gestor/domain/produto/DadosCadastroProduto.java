package calculadora.gestor.domain.produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DadosCadastroProduto(

        @NotBlank
        String nome,
        @NotBlank
        String cor,
        @NotNull
        Categoria categoria,
        @NotNull
        Integer quantidade,
        @NotNull
        BigDecimal valor
) {
}
