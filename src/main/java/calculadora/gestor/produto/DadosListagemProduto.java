package calculadora.gestor.produto;

import java.math.BigDecimal;

public record DadosListagemProduto(
        String nome,
        String cor,
        Categoria categoria,
        Integer quantidade,
        BigDecimal valor
) {

    public DadosListagemProduto(Produto dados) {
        this(dados.getNome(), dados.getCor(), dados.getCategoria(),dados.getQuantidade(), dados.getValor());
    }

}
