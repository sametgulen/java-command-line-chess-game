public class Queen extends Piece {
    public Queen(int color, Square location) {

        super(color, location);
    }

    // Checks if the Queen can move to the specified destination square

    @Override
    public boolean canMove(String destination) {
        Square targetLocation = location.getBoard().getSquareAt(destination);
        Square[] squaresBetween = location.getBoard().getSquaresBetween(location, targetLocation);

        // If there are squares between the current location and the target location

        if (squaresBetween != null) {

            // Check if any of the squares between are not empty, indicating an obstruction

            for (Square square : squaresBetween) {
                if (!square.isEmpty()) {
                    return false;
                }
            }

            // The target location must be empty or occupied by an opponent's piece

            return targetLocation.isEmpty() || targetLocation.getPiece().getColor() != color;
        }

        return false;
    }

    // Moves the Queen to the specified destination square

    @Override
    public void move(String destination) {
        Square targetLocation = location.getBoard().getSquareAt(destination);
        targetLocation.setPiece(this);
        location.clear();
        location = targetLocation;
        location.getBoard().nextPlayer();
    }

    // Returns the string representation of the Queen

    @Override
    public String toString() {

        // Use "Q" for white Queen and "q" for black Queen

        return color == ChessBoard.WHITE ? "Q" : "q";
    }
}