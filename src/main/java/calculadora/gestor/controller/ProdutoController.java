package calculadora.gestor.controller;

import calculadora.gestor.produto.*;
import calculadora.gestor.repository.ProdutoRepository;
import calculadora.gestor.service.orcamento.OrcamentoService;
import calculadora.gestor.service.orcamento.ResultadoListagemOrcamento;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private OrcamentoService orcamentoService;


    @PostMapping
    @Transactional
    public void cadastrarProdutos(@RequestBody @Valid DadosCadastroProduto dados) {
        produtoRepository.save(new Produto(dados));
    }

    @GetMapping
    public Page<DadosListagemProduto> listarProdutos(Pageable pageable) {
        return produtoRepository.findAllByAtivoTrue(pageable).map(DadosListagemProduto::new);
    }

    @PutMapping
    @Transactional
    public void atualizarProdutos(@RequestBody @Valid DadosAtualizacaoProduto dados) {
        var produto = produtoRepository.getReferenceById(dados.id());
        produto.atualizarProduto(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluirProdutos(@PathVariable("id") Long id) {
        var produto = produtoRepository.getReferenceById(id);
        produto.excluirProduto(id);
    }

    @PutMapping("/{id}")
    @Transactional
    public void ativarProdutos(@PathVariable("id") Long id) {
        var produto = produtoRepository.getReferenceById(id);
        produto.ativarProduto(id);
    }

    @PostMapping("/calcular")
    public ResultadoListagemOrcamento orcamentoProdutos(@RequestBody @Valid List<DadosOrcamentoProduto> produtos) {

        return orcamentoService.calculaOrcamento(produtos);

    }

}
