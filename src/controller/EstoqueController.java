package controller;

import model.Produto;
import java.util.ArrayList;
import java.util.List;

public class EstoqueController {
    private final List<Produto> produtos = new ArrayList<>();
    private int contadorId = 1;

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void adicionarProduto(String nome, int quantidade) {
        produtos.add(new Produto(contadorId++, nome, quantidade));
    }

    public void editarProduto(int id, String novoNome, int novaQuantidade) {
        for (Produto p : produtos) {
            if (p.getId() == id) {
                p.setNome(novoNome);
                p.setQuantidade(novaQuantidade);
                break;
            }
        }
    }

    public void removerProduto(int id) {
        produtos.removeIf(p -> p.getId() == id);
    }
}
