// Local: src/application/Program.java
package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import javax.swing.SwingUtilities;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

    public static void main(String[] args) {
        // Este comando abre a nossa janela de menu em vez de rodar o código do console
        SwingUtilities.invokeLater(() -> {
            TelaInicial menu = new TelaInicial();
            menu.setVisible(true);
        });
    }

    /**
     * Este método contém o código ORIGINAL do seu jogo de console.
     * Ele agora é chamado quando o usuário clica em "Iniciar Jogo" na janela do menu.
     */
    public static void iniciarPartidaDeXadrezNoConsole() {
        Scanner sc = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();
        List<ChessPiece> captured = new ArrayList<>();

        while (!chessMatch.getCheckMate()) {
            try {
                UI.clearScreen();
                UI.printMatch(chessMatch, captured);
                System.out.println();
                System.out.print("Origem: ");
                ChessPosition source = UI.readChessPosition(sc);

                boolean[][] possibleMoves = chessMatch.possibleMoves(source);
                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces(), possibleMoves);

                System.out.println();
                System.out.print("Destino: ");
                ChessPosition target = UI.readChessPosition(sc);

                ChessPiece capturedPiece = chessMatch.performChessMove(source, target);

                if (capturedPiece != null) {
                    captured.add(capturedPiece);
                }

                if (chessMatch.getPromoted() != null) {
                    System.out.print("Entre a peça para promoção (B/N/R/Q): ");
                    String type = sc.nextLine().toUpperCase();
                    while (!type.equals("B") && !type.equals("N") && !type.equals("R") & !type.equals("Q")) {
                        System.out.print("Valor inválido! Entre a peça para promoção (B/N/R/Q): ");
                        type = sc.nextLine().toUpperCase();
                    }
                    chessMatch.replacePromotedPiece(type);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Pressione ENTER para continuar");
                sc.nextLine();
            }
        }
        UI.clearScreen();
        UI.printMatch(chessMatch, captured);
    }
}