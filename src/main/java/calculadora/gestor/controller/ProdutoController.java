package calculadora.gestor.controller;

import calculadora.gestor.produto.*;
import calculadora.gestor.repository.ProdutoRepository;
import calculadora.gestor.service.orcamento.OrcamentoService;
import calculadora.gestor.service.produto.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private OrcamentoService orcamentoService;

    @Autowired
    private ProdutoService produtoService;


    @PostMapping
    @Transactional
    public ResponseEntity cadastrarProdutos(@RequestBody @Valid DadosCadastroProduto dados, UriComponentsBuilder builder) {
        var produto = new Produto(dados);
        produtoRepository.save(produto);
        var uri = builder.path("produtos/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoProduto(produto));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemProduto>> listarProdutos(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        var page = produtoRepository.findAllByAtivoTrue(pageable).map(DadosListagemProduto::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarProdutos(@RequestBody @Valid DadosAtualizacaoProduto dados) {
        var produto = produtoRepository.getReferenceById(dados.id());
        produtoService.atualizarProduto(dados);
        return ResponseEntity.ok(new DadosDetalhamentoProduto(produto));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirProdutos(@PathVariable("id") Long id) {
        var produto = produtoRepository.getReferenceById(id);
        produtoService.excluirProduto(produto.getId());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity ativarProdutos(@PathVariable("id") Long id) {
        var produto = produtoRepository.getReferenceById(id);
        produtoService.ativarProduto(produto.getId());
        return ResponseEntity.ok(new DadosDetalhamentoProduto(produto));
    }

    @PostMapping("/calcular")
    public ResponseEntity orcamentoProdutos(@RequestBody @Valid List<DadosOrcamentoProduto> produtos) {
        var orcamento = orcamentoService.calculaOrcamento(produtos);
        return ResponseEntity.ok(orcamento);

    }

}
