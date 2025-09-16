// Local: src/application/TelaInicial.java
package application;

import javax.swing.*;
import java.awt.*;

public class TelaInicial extends JFrame {

    public TelaInicial() {
        setTitle("Menu Principal - Jogo de Xadrez");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        JPanel painelPrincipal = new JPanel(new GridBagLayout());
        painelPrincipal.setBackground(Color.DARK_GRAY);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(15, 20, 15, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titulo = new JLabel("Jogo de Xadrez", SwingConstants.CENTER);
        titulo.setFont(new Font("Serif", Font.BOLD, 40));
        titulo.setForeground(new Color(230, 230, 230));
        painelPrincipal.add(titulo, gbc);
        
        gbc.insets = new Insets(40, 20, 15, 20);
        painelPrincipal.add(new JLabel(), gbc);
        gbc.insets = new Insets(15, 20, 15, 20);

        JButton btnIniciar = criarBotaoEstilizado("Iniciar Jogo");
        JButton btnComoJogar = criarBotaoEstilizado("Como Jogar");

        painelPrincipal.add(btnIniciar, gbc);
        painelPrincipal.add(btnComoJogar, gbc);

        // Ações dos botões
        btnIniciar.addActionListener(e -> iniciarJogo());
        btnComoJogar.addActionListener(e -> mostrarRegras());

        add(painelPrincipal);
    }

    private JButton criarBotaoEstilizado(String texto) {
        JButton button = new JButton(texto);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(new Color(80, 80, 80));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(120, 120, 120), 1),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(100, 100, 100));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(80, 80, 80));
            }
        });
        return button;
    }

    private void iniciarJogo() {
        // Por enquanto, esta ação vai apenas fechar o menu e rodar o jogo no console
        // Este é o "link" entre o seu menu gráfico e o jogo de console existente
        this.dispose(); // Fecha a janela do menu
        Program.iniciarPartidaDeXadrezNoConsole(); // Chama o método que inicia o jogo
    }

    private void mostrarRegras() {
        // A lógica das regras aqui está correta, abrindo uma nova janela
        JFrame frameRegras = new JFrame("Como Jogar Xadrez");
        frameRegras.setSize(600, 450);
        frameRegras.setLocationRelativeTo(this);
        frameRegras.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        areaTexto.setLineWrap(true);
        areaTexto.setWrapStyleWord(true);
        areaTexto.setFont(new Font("SansSerif", Font.PLAIN, 15));
        areaTexto.setMargin(new Insets(10, 10, 10, 10));
        areaTexto.setText(
            "Regras Básicas do Xadrez:\n\n" +
            "O objetivo do jogo é dar xeque-mate no rei adversário...\n\n" +
            // Adicione o resto do texto das regras aqui
            "Movimento das Peças:\n" +
            "• Peão: Move-se uma casa para frente...\n"
        );

        JScrollPane scrollPane = new JScrollPane(areaTexto);
        frameRegras.add(scrollPane);
        frameRegras.setVisible(true);
    }
}