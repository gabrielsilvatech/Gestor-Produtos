package calculadora.gestor.domain.produto;

import java.math.BigDecimal;

public record DadosDetalhamentoProduto(

        String nome,

        String cor,

        Categoria categoria,

        Integer quantidade,

        BigDecimal valor
) {
    public DadosDetalhamentoProduto(Produto dados) {
        this(dados.getNome(), dados.getCor(), dados.getCategoria(), dados.getQuantidade(), dados.getValor());
    }
}
