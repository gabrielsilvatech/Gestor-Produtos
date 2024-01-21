package calculadora.gestor.service.orcamento;

import calculadora.gestor.domain.produto.Produto;

import java.math.BigDecimal;


public record DadosListagemOrcamentoProduto (
        Long id,
        Integer quantidade,
        BigDecimal valor,
        BigDecimal subtotal
){
    public DadosListagemOrcamentoProduto(Produto dados) {
        this(dados.getId(), dados.getQuantidade(), dados.getValor(), BigDecimal.ZERO);
    }
}
