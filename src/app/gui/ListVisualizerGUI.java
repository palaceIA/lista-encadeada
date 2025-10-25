package app.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import app.struct.simple.*;
import app.struct.duple.*;
import app.struct.circular.*;

public class ListVisualizerGUI extends JPanel {
    private String tipoLista;
    private ListIncadeSimple listaSimples;
    private ListDoubleIncluded listaDupla;
    private ListCircular listaCircular;

    private List<Integer> valoresAnimados = new ArrayList<>();
    private int deslocamentoX = 0;
    private int indexHighlight = -1;
    private boolean highlightOn = false;
    private float fadeOpacity = 1f;

    private Timer animTimer;
    private Timer blinkTimer;
    private Timer fadeTimer;

    public ListVisualizerGUI(ListIncadeSimple s, ListDoubleIncluded d, ListCircular c) {
        this.listaSimples = s;
        this.listaDupla = d;
        this.listaCircular = c;
        this.tipoLista = "Simples";
        setBackground(new Color(252, 253, 255));
        atualizarListaAnimada();

        // Timer para animações de movimento
        animTimer = new Timer(15, e -> {
            if (deslocamentoX > 0) deslocamentoX -= 5;
            repaint();
        });
    }

    public void setTipoLista(String tipo) {
        this.tipoLista = tipo;
        fadeOpacity = 0f;

        if (fadeTimer != null && fadeTimer.isRunning()) fadeTimer.stop();
        fadeTimer = new Timer(30, e -> {
            fadeOpacity += 0.05f;
            if (fadeOpacity >= 1f) {
                fadeOpacity = 1f;
                ((Timer) e.getSource()).stop();
            }
            atualizarListaAnimada();
            repaint();
        });
        fadeTimer.start();
    }

    public void animarInsercao() {
        atualizarListaAnimada();
        deslocamentoX = 80;
        animTimer.start();
    }

    public void animarRemocao() {
        atualizarListaAnimada();
        deslocamentoX = 0;
        animTimer.start();
    }

    public void animarBusca(int valorBuscado) {
        atualizarListaAnimada();
        indexHighlight = valoresAnimados.indexOf(valorBuscado);
        if (indexHighlight == -1) {
            JOptionPane.showMessageDialog(this, "Valor não encontrado na lista!");
            return;
        }

        highlightOn = true;

        if (blinkTimer != null && blinkTimer.isRunning()) blinkTimer.stop();
        blinkTimer = new Timer(150, e -> {
            highlightOn = !highlightOn;
            repaint();
        });
        blinkTimer.start();

        new Timer(1200, e -> {
            blinkTimer.stop();
            highlightOn = false;
            indexHighlight = -1;
            repaint();
        }).start();
    }

    private void atualizarListaAnimada() {
        valoresAnimados = getValoresAtuais();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (((Graphics2D) g).getComposite() instanceof AlphaComposite) {
            ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fadeOpacity));
        }

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setFont(new Font("Segoe UI", Font.BOLD, 14));

        int x = 50 - deslocamentoX;
        int y = getHeight() / 2 - 20;
        int largura = 60;
        int altura = 40;
        int espacamento = 90;

        for (int i = 0; i < valoresAnimados.size(); i++) {
            int valor = valoresAnimados.get(i);

            Color corInicial = new Color(100, 181, 246);
            Color corFinal = new Color(66, 133, 244);

            if (i == indexHighlight && highlightOn)
                corInicial = corFinal = new Color(255, 215, 0);

            GradientPaint gp = new GradientPaint(x, y, corInicial, x + largura, y + altura, corFinal);
            g2.setPaint(gp);
            g2.fillRoundRect(x, y, largura, altura, 12, 12);

            g2.setColor(new Color(30, 30, 30));
            g2.drawRoundRect(x, y, largura, altura, 12, 12);

            String texto = String.valueOf(valor);
            FontMetrics fm = g2.getFontMetrics();
            int tx = x + (largura - fm.stringWidth(texto)) / 2;
            int ty = y + ((altura - fm.getHeight()) / 2) + fm.getAscent();
            g2.setColor(Color.WHITE);
            g2.drawString(texto, tx, ty);

            // 🔹 Desenhar setas dependendo do tipo de lista
            if (i < valoresAnimados.size() - 1) {
                int setaInicio = x + largura;
                int setaFim = x + espacamento;

                g2.setStroke(new BasicStroke(2f));
                g2.setColor(Color.BLACK);

                if (tipoLista.equals("Simples")) {
                    // → seta simples
                    g2.drawLine(setaInicio, y + altura / 2, setaFim - 10, y + altura / 2);
                    g2.fillPolygon(
                            new int[]{setaFim - 15, setaFim - 15, setaFim - 5},
                            new int[]{y + altura / 2 - 5, y + altura / 2 + 5, y + altura / 2},
                            3);
                } else if (tipoLista.equals("Dupla")) {
                    // ↔ setas duplas
                    int linhaY = y + altura / 2;
                    g2.drawLine(setaInicio, linhaY, setaFim - 10, linhaY);

                    // seta direita →
                    g2.fillPolygon(
                            new int[]{setaFim - 15, setaFim - 15, setaFim - 5},
                            new int[]{linhaY - 5, linhaY + 5, linhaY},
                            3);

                    // seta esquerda ←
                    g2.fillPolygon(
                            new int[]{setaInicio + 5, setaInicio + 5, setaInicio - 5},
                            new int[]{linhaY - 5, linhaY + 5, linhaY},
                            3);
                }
            }

            x += espacamento;
        }

        // 🔁 Representação do círculo de volta na lista circular
        if (tipoLista.equals("Circular") && valoresAnimados.size() > 1) {
            int inicioX = 50;
            int fimX = 50 + (valoresAnimados.size() - 1) * espacamento + largura;
            int meioY = y - 70;

            g2.setColor(new Color(220, 20, 60));
            g2.setStroke(new BasicStroke(2.5f));
            g2.drawArc(inicioX, meioY, fimX - inicioX - 20, 140, 0, 180);

            g2.setFont(new Font("Segoe UI", Font.BOLD, 18));
            g2.drawString("↩", inicioX + 15, meioY + 15);
        }

        // título informativo
        g2.setFont(new Font("Segoe UI", Font.BOLD, 16));
        g2.setColor(new Color(50, 50, 50));
        g2.drawString("Tipo de Lista: " + tipoLista, 20, 30);
    }

    private List<Integer> getValoresAtuais() {
        List<Integer> valores = new ArrayList<>();
        switch (tipoLista) {
            case "Simples" -> {
                NoSimple atual = listaSimples.getInicio();
                while (atual != null) {
                    valores.add(atual.valor);
                    atual = atual.proximo;
                }
            }
            case "Dupla" -> {
                NoDouble atual = listaDupla.getInicio();
                while (atual != null) {
                    valores.add(atual.valor);
                    atual = atual.proximo;
                }
            }
            case "Circular" -> {
                NoCircular atual = listaCircular.getInicio();
                if (atual == null) break;
                do {
                    valores.add(atual.valor);
                    atual = atual.proximo;
                } while (atual != listaCircular.getInicio());
            }
        }
        return valores;
    }
}
