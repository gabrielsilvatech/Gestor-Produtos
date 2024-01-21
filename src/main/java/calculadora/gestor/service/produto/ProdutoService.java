package calculadora.gestor.service.produto;

import calculadora.gestor.infraestrutura.exception.ValidacaoException;
import calculadora.gestor.domain.produto.DadosAtualizacaoProduto;
import calculadora.gestor.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public void atualizarProduto(DadosAtualizacaoProduto dados) {
        if (!produtoRepository.existsById(dados.id())) {
            throw new ValidacaoException("Produto não existe");
        }

        produtoRepository.getReferenceById(dados.id()).atualizarProduto(dados);

    }

    public void excluirProduto(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new ValidacaoException("Produto não existe");
        }

        if (!produtoRepository.getReferenceById(id).getAtivo()) {
            throw new ValidacaoException("Produto inativo");
        }

        produtoRepository.getReferenceById(id).excluirProduto(id);
    }

    public void ativarProduto(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new ValidacaoException("Produto não existe");
        }

        if (produtoRepository.getReferenceById(id).getAtivo()) {
            throw new ValidacaoException("Produto já está ativo");
        }

        produtoRepository.getReferenceById(id).ativarProduto(id);
    }

}
