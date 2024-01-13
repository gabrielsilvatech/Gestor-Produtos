package calculadora.gestor.service.orcamento;

import calculadora.gestor.produto.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
