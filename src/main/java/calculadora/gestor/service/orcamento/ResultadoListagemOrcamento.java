package calculadora.gestor.service.orcamento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ResultadoListagemOrcamento {
    List<DadosListagemOrcamentoProduto> listaProdutos;
    BigDecimal total;

}
