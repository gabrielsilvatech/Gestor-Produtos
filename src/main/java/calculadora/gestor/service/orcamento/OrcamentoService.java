package calculadora.gestor.service.orcamento;

import calculadora.gestor.infraestrutura.exception.ValidacaoException;
import calculadora.gestor.produto.DadosOrcamentoProduto;
import calculadora.gestor.produto.Produto;
import calculadora.gestor.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrcamentoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public OrcamentoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public ResultadoListagemOrcamento calculaOrcamento(List<DadosOrcamentoProduto> produtos) {
        BigDecimal total = BigDecimal.ZERO;
        List<DadosListagemOrcamentoProduto> listaProdutos = new ArrayList<>();

        for (DadosOrcamentoProduto dadosProduto : produtos) {
            Long idProduto = dadosProduto.id();
            Integer quantidade = dadosProduto.quantidade();

            Produto produto = produtoRepository.findById(idProduto).orElse(null);

            if (produto == null) {
                throw new ValidacaoException("Produto não existe");
            }

            BigDecimal valorProduto = produto.getValor();
            BigDecimal subTotal = valorProduto.multiply(BigDecimal.valueOf(quantidade));
            total = total.add(subTotal);

            var detalhesProduto = new DadosListagemOrcamentoProduto(produto.getId(),quantidade,produto.getValor(), subTotal);
            listaProdutos.add(detalhesProduto);

        }

        return new ResultadoListagemOrcamento(listaProdutos, total);

    }

}
