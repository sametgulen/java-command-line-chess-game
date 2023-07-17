public class ChessBoard {
    public static final int WHITE = 0;
    public static final int BLACK = 1;
    private static boolean whitePlaying;
    private final Square[][] board = new Square[8][8];

    // Method to check if the game has ended by counting the number of remaining pieces

    public boolean isGameEnded(){
        int whiteCount = 0;
        int blackCount = 0;

        // Count the number of remaining pieces
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                Piece piece = board[row][column].getPiece();
                if (piece != null) {
                    if (piece.getColor() == WHITE) {
                        whiteCount++;
                    } else {
                        blackCount++;
                    }
                }
            }
        }

        // Game ends when there are no pieces remaining for one color
        return whiteCount == 0 || blackCount == 0;
    }

    public ChessBoard() {
        whitePlaying = true;
        initialize();
    }

    // Method to check if it is currently white player's turn

    public boolean isWhitePlaying() {

        return whitePlaying;
    }

    // Method to initialize the chessboard and set up the pieces

    private void initialize() {

        // Create Square objects for each position on the board

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Square(i, j, this);
            }
        }

        // Create and place the pieces on the board based on the starting positions

        createPieces("RNBQKBNR", 0, 0);
        createPieces("PPPPPPPP", 1, 0);
        createPieces("pppppppp", 6, 1);
        createPieces("rnbqkbnr", 7, 1);
    }
    // Helper method to create pieces and place them on the board

    private void createPieces(String pattern, int row, int color) {
        for (int col = 0; col < 8; col++) {
            char pieceChar = pattern.charAt(col);
            Square square = board[row][col];
            Piece piece = switch (pieceChar) {
                case 'R', 'r' -> new Rook(color, square);
                case 'N', 'n' -> new Knight(color, square);
                case 'B', 'b' -> new Bishop(color, square);
                case 'Q', 'q' -> new Queen(color, square);
                case 'K', 'k' -> new King(color, square);
                case 'P', 'p' -> new Pawn(color, square);
                default -> null;
            };

            square.setPiece(piece);
        }
    }

    // Method to represent the chessboard as a string for display

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("   ");
        String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H"};

        for (String letter : letters) {
            string.append(letter).append(" ");
        }

        string.append("\n  -----------------\n");
        for (int c = 7; c >= 0; c--) {
            string.append(c + 1).append(" |");
            for (int i = 0; i < 8; i++) {
                if (board[c][i].getPiece() == null) {
                    string.append(" ");
                } else {
                    string.append(board[c][i].getPiece().toString());
                }
                string.append("|");
            }
            string.append(" ").append(c + 1).append("\n");
            if (c > 0) {
                string.append("  ");
                string.append("--".repeat(8));
                string.append("\n");
            }
        }
        string.append("  -----------------\n");
        string.append("   ");
        for (String letter : letters) {
            string.append(letter).append(" ");
        }
        return string.toString();
    }



    // Method to get the entire chessboard as a array of Square objects

    public Square[][] getBoard() {

        return board;
    }

    // Method to get the piece at a specific position on the chessboard

    public Piece getPieceAt(String from) {
        String[] lowerCase_letters ={"a", "b", "c", "d", "e", "f", "g", "h"};
        int rowCoordinate = Integer.parseInt(from.substring(1)) - 1;
        int colCoordinate = -1;
        for (int i = 0; i < lowerCase_letters.length; i++) {
            if (from.substring(0, 1).equalsIgnoreCase(lowerCase_letters[i])) {
                colCoordinate = i;
                break;
            }
        }

        if (rowCoordinate > 7 || rowCoordinate < 0 || colCoordinate == -1) {
            throw new IllegalArgumentException("Invalid input");
        }

        return board[rowCoordinate][colCoordinate].getPiece();
    }


    // Method to get the square at a specific position on the chessboard

    public Square getSquareAt(String to) {
        String[] lowerCaseLetters = {"a", "b", "c", "d", "e", "f", "g", "h"};
        int rowCoordinate = Integer.parseInt(to.substring(1)) - 1;
        int colCoordinate = -1;
        for (int i = 0; i < lowerCaseLetters.length; i++) {
            if (to.substring(0, 1).equalsIgnoreCase(lowerCaseLetters[i])) {
                colCoordinate = i;
                break;
            }
        }

        if (rowCoordinate > 7 || rowCoordinate < 0 || colCoordinate == -1) {
            throw new IllegalArgumentException("Invalid input");
        }

        return board[rowCoordinate][colCoordinate];
    }

    // Method to get the squares between two given squares

    public Square[] getSquaresBetween(Square location, Square targetLocation) {
        boolean isAtSameX = location.row == targetLocation.row;
        boolean isAtSameY = location.column == targetLocation.column;
        boolean isCrossPos = Math.abs(location.row-targetLocation.row)==Math.abs(location.column-targetLocation.column);

        if(isAtSameX||isAtSameY||isCrossPos){
            int distance = Math.max(Math.abs(location.row-targetLocation.row),(location.column-targetLocation.column));
            Square[]squares=new Square[distance-1];
            for(int i = 1; i<distance; i++){
                int row = location.row+i*((targetLocation.row-location.row)/distance);
                int column = location.column+i*((targetLocation.column-location.column)/distance);
                squares[i-1]=board[row][column];
            }
            return squares;
        }else {
            return null;
        }
    }

    // Method to get the color of the next player to make a move

    public void nextPlayer() {

        ChessBoard.whitePlaying = !ChessBoard.whitePlaying;
    }

}