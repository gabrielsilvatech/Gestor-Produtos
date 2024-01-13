package calculadora.gestor.produto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity(name = "Produto")
@Table(name = "produtos")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    private String cor;
    private Integer quantidade;
    private BigDecimal valor;
    private Boolean ativo;

    public Produto(DadosCadastroProduto dados) {

        this.ativo = true;
        this.nome = dados.nome();
        this.categoria = dados.categoria();
        this.cor = dados.cor();
        this.quantidade = dados.quantidade();
        this.valor = dados.valor();

    }

    public void atualizarProduto(DadosAtualizacaoProduto dados) {
        if (dados.categoria() != null) {
            this.categoria = dados.categoria();
        }

        if (dados.quantidade() != null) {
            this.quantidade = dados.quantidade();
        }

        if (dados.valor() != null) {
            this.valor = dados.valor();
        }
    }

    public void excluirProduto(Long id) {
        this.ativo = false;
    }

    public void ativarProduto(Long id) {
        this.ativo = true;
    }
}
