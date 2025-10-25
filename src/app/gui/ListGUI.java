package app.gui;

import javax.swing.*;
import java.awt.*;
import app.struct.simple.*;
import app.struct.duple.*;
import app.struct.circular.*;

public class ListGUI extends JFrame {
    private JComboBox<String> tipoLista;
    private JTextField inputValor;
    private ListVisualizerGUI visualizador;

    private ListIncadeSimple listaSimples = new ListIncadeSimple();
    private ListDoubleIncluded listaDupla = new ListDoubleIncluded();
    private ListCircular listaCircular = new ListCircular();

    public ListGUI() {
        setTitle("Visualizador de Listas Encadeadas");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        visualizador = new ListVisualizerGUI(listaSimples, listaDupla, listaCircular);
        add(visualizador, BorderLayout.CENTER);

        JPanel painelSuperior = new JPanel();
        painelSuperior.setBackground(new Color(240, 248, 255));

        tipoLista = new JComboBox<>(new String[]{"Simples", "Dupla", "Circular"});
        inputValor = new JTextField(5);
        JButton btnInserir = new JButton("Inserir");
        JButton btnRemover = new JButton("Remover");
        JButton btnBuscar = new JButton("Buscar");

        painelSuperior.add(new JLabel("Tipo:"));
        painelSuperior.add(tipoLista);
        painelSuperior.add(new JLabel("Valor:"));
        painelSuperior.add(inputValor);
        painelSuperior.add(btnInserir);
        painelSuperior.add(btnRemover);
        painelSuperior.add(btnBuscar);

        add(painelSuperior, BorderLayout.NORTH);

        tipoLista.addActionListener(e -> {
            String tipo = tipoLista.getSelectedItem().toString();
            visualizador.setTipoLista(tipo);
        });

        btnInserir.addActionListener(e -> {
            try {
                int valor = Integer.parseInt(inputValor.getText());
                switch (tipoLista.getSelectedItem().toString()) {
                    case "Simples" -> listaSimples.inserir(valor);
                    case "Dupla" -> listaDupla.inserir(valor);
                    case "Circular" -> listaCircular.inserir(valor);
                }
                visualizador.animarInsercao();
                inputValor.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Digite um número válido.");
            }
        });

        btnRemover.addActionListener(e -> {
            try {
                int valor = Integer.parseInt(inputValor.getText());
                switch (tipoLista.getSelectedItem().toString()) {
                    case "Simples" -> listaSimples.remover(valor);
                    case "Dupla" -> listaDupla.remover(valor);
                    case "Circular" -> listaCircular.remover(valor);
                }
                visualizador.animarRemocao();
                inputValor.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Digite um número válido.");
            }
        });

        btnBuscar.addActionListener(e -> {
            try {
                int valor = Integer.parseInt(inputValor.getText());
                visualizador.animarBusca(valor);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Digite um número válido.");
            }
        });
    }
}
