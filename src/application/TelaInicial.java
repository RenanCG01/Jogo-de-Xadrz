package application;

import javax.swing.*;
import java.awt.*;

public class TelaInicial extends JFrame {

    private String nomeUsuario = "Jogador"; // Nome padrão

    public TelaInicial() {
        setTitle("Menu Principal - Jogo de Xadrez");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Painel principal com imagem de fundo
        JPanel painelPrincipal = new JPanel(new GridBagLayout());
        painelPrincipal.setBackground(Color.DARK_GRAY); // Cor de fundo caso a imagem falhe

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(15, 20, 15, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título
        JLabel titulo = new JLabel("Jogo de Xadrez", SwingConstants.CENTER);
        titulo.setFont(new Font("Serif", Font.BOLD, 40));
        titulo.setForeground(new Color(230, 230, 230));
        painelPrincipal.add(titulo, gbc);
        
        // Espaçador
        gbc.insets = new Insets(40, 20, 15, 20);
        painelPrincipal.add(new JLabel(), gbc); // Adiciona um espaço
        gbc.insets = new Insets(15, 20, 15, 20);


        // Botões
        JButton btnIniciar = criarBotaoEstilizado("Iniciar Jogo");
        JButton btnDefinirNome = criarBotaoEstilizado("Definir Nome de Usuário");
        JButton btnComoJogar = criarBotaoEstilizado("Como Jogar");

        painelPrincipal.add(btnIniciar, gbc);
        painelPrincipal.add(btnDefinirNome, gbc);
        painelPrincipal.add(btnComoJogar, gbc);

        // Ações dos botões
        btnIniciar.addActionListener(e -> iniciarJogo());
        btnDefinirNome.addActionListener(e -> definirNome());
        btnComoJogar.addActionListener(e -> mostrarRegras());

        add(painelPrincipal);
        setVisible(true);
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
        this.dispose(); // Fecha a tela de menu
        // Inicia o jogo de xadrez em uma nova thread para não travar a UI
        new Thread(() -> Program.iniciarPartidaDeXadrez()).start();
    }

    private void definirNome() {
        String novoNome = JOptionPane.showInputDialog(this, "Digite seu nome:", "Definir Nome de Usuário", JOptionPane.PLAIN_MESSAGE);
        if (novoNome != null && !novoNome.trim().isEmpty()) {
            this.nomeUsuario = novoNome;
            JOptionPane.showMessageDialog(this, "Nome de usuário definido como: " + this.nomeUsuario, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            // Futuramente, você pode passar a variável 'nomeUsuario' para a lógica do jogo.
        }
    }

    private void mostrarRegras() {
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
            "O objetivo do jogo é dar xeque-mate no rei adversário. Isso acontece quando o rei está sob ataque (em xeque) e não pode se mover para uma casa segura.\n\n" +
            "Movimento das Peças:\n" +
            "• Peão: Move-se uma casa para frente. No primeiro movimento, pode mover-se duas casas. Captura na diagonal.\n" +
            "• Torre: Move-se em qualquer número de casas na horizontal ou vertical.\n" +
            "• Cavalo: Move-se em 'L' (duas casas em uma direção e uma em uma direção perpendicular).\n" +
            "• Bispo: Move-se em qualquer número de casas na diagonal.\n" +
            "• Dama (Rainha): Combina o movimento da Torre e do Bispo.\n" +
            "• Rei: Move-se uma casa em qualquer direção.\n\n" +
            "Jogadas Especiais:\n" +
            "• Roque: Um movimento especial entre o Rei e uma das Torres para proteger o rei e ativar a torre.\n" +
            "• En Passant: Uma captura especial que um peão pode fazer sobre um peão adversário.\n" +
            "• Promoção: Quando um peão alcança a oitava fileira, ele deve ser promovido a Dama, Torre, Bispo ou Cavalo."
        );

        JScrollPane scrollPane = new JScrollPane(areaTexto);
        frameRegras.add(scrollPane);
        frameRegras.setVisible(true);
    }
}