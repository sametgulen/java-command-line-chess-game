public class Square {
    int row;
    int column;
    ChessBoard board;
    Piece piece;

    public Square(int row, int column, ChessBoard board) {
        this.row = row;
        this.column = column;
        this.board = board;
        this.piece = null;
    }

    // Checks if the square is at the last row for a given color

    public boolean isAtLastRow(int color) {
        return (color == ChessBoard.WHITE && row == 7) || (color == ChessBoard.BLACK && row == 0);
    }

    // Checks if the square is empty (no piece)

    public boolean isEmpty() {

        return piece == null;
    }

    // Checks if the square is in the same column as another square

    public boolean isAtSameColumn(Square s) {

        return column == s.column;
    }

    // Calculates the row distance between this square and another square

    public int getRowDistance(Square s) {

        return Math.abs(row - s.row);
    }

    // Calculates the column distance between this square and another square

    public int getColDistance(Square s) {

        return Math.abs(column - s.column);
    }

    // Checks if the square is a neighbor column to another square

    public boolean isNeighborColumn(Square s) {

        return getColDistance(s) == 1;
    }

    // Gets the chessboard associated with this square

    public ChessBoard getBoard() {

        return board;
    }

    // Gets the piece on this square

    public Piece getPiece() {

        return piece;
    }

    // Sets the piece on this square

    public void setPiece(Piece piece) {

        this.piece = piece;
    }

    // Promotes the pawn in this square to a new queen of the specified color

    public void putNewQueen(int color) {

        this.piece = new Queen(color, this);
    }

    // Clears the square (removes the piece)

    public void clear() {

        this.piece = null;
    }
}