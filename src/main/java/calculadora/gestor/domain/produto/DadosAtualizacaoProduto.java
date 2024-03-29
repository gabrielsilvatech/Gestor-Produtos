package calculadora.gestor.domain.produto;

import java.math.BigDecimal;

public record DadosAtualizacaoProduto(

        Long id,
        Categoria categoria,
        Integer quantidade,
        BigDecimal valor
) {
}
