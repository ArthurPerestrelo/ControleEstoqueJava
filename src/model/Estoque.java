package model;

import java.util.ArrayList;

public class Estoque {
    private ArrayList<Produto> produtos = new ArrayList<>();

    public void adicionarProduto(Produto p) {
        produtos.add(p);
    }

    public void removerProduto(String nome) {
        produtos.removeIf(p -> p.getNome().equalsIgnoreCase(nome));
    }

    public Produto buscarProduto(String nome) {
        for (Produto p : produtos) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                return p;
            }
        }
        return null;
    }

    public ArrayList<Produto> listarProdutos() {
        return produtos;
    }
}
