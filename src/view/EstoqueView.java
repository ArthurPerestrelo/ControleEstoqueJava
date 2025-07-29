package view;

import controller.EstoqueController;
import model.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class EstoqueView extends JFrame {
    private final EstoqueController controller;
    private final DefaultTableModel tabelaModel;

    public EstoqueView(EstoqueController controller) {
        this.controller = controller;

        setTitle("Controle de Estoque");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane abas = new JTabbedPane();
        JPanel painelProdutos = new JPanel(new BorderLayout());

        tabelaModel = new DefaultTableModel(new String[]{"ID", "Nome", "Quantidade"}, 0);
        JTable tabela = new JTable(tabelaModel);
        JScrollPane scroll = new JScrollPane(tabela);

        atualizarTabela();

        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnEditar = new JButton("Editar");
        JButton btnRemover = new JButton("Remover");

        JPanel botoes = new JPanel();
        botoes.add(btnAdicionar);
        botoes.add(btnEditar);
        botoes.add(btnRemover);

        painelProdutos.add(scroll, BorderLayout.CENTER);
        painelProdutos.add(botoes, BorderLayout.SOUTH);

        abas.addTab("Produtos", painelProdutos);
        add(abas);

        btnAdicionar.addActionListener(e -> {
            String nome = JOptionPane.showInputDialog(this, "Nome do produto:");
            if (nome == null || nome.isBlank()) return;

            String quantidadeStr = JOptionPane.showInputDialog(this, "Quantidade:");
            if (quantidadeStr == null || quantidadeStr.isBlank()) return;

            int quantidade = Integer.parseInt(quantidadeStr);
            controller.adicionarProduto(nome, quantidade);
            atualizarTabela();
        });

        btnEditar.addActionListener(e -> {
            int linha = tabela.getSelectedRow();
            if (linha == -1) return;

            int id = (int) tabelaModel.getValueAt(linha, 0);
            String nomeAtual = (String) tabelaModel.getValueAt(linha, 1);
            int quantidadeAtual = (int) tabelaModel.getValueAt(linha, 2);

            String novoNome = JOptionPane.showInputDialog(this, "Novo nome:", nomeAtual);
            if (novoNome == null || novoNome.isBlank()) return;

            String novaQuantidadeStr = JOptionPane.showInputDialog(this, "Nova quantidade:", quantidadeAtual);
            if (novaQuantidadeStr == null || novaQuantidadeStr.isBlank()) return;

            int novaQuantidade = Integer.parseInt(novaQuantidadeStr);
            controller.editarProduto(id, novoNome, novaQuantidade);
            atualizarTabela();
        });

        btnRemover.addActionListener(e -> {
            int linha = tabela.getSelectedRow();
            if (linha == -1) return;

            int id = (int) tabelaModel.getValueAt(linha, 0);
            controller.removerProduto(id);
            atualizarTabela();
        });
    }

    private void atualizarTabela() {
        tabelaModel.setRowCount(0);
        for (Produto p : controller.getProdutos()) {
            tabelaModel.addRow(new Object[]{p.getId(), p.getNome(), p.getQuantidade()});
        }
    }
}
