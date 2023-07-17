public class Bishop extends Piece {
    public Bishop(int color, Square location) {

        super(color, location);
    }

    @Override
    public boolean canMove(String destination) {
        Square targetLocation = location.getBoard().getSquareAt(destination);
        Square[] squaresBetween = location.getBoard().getSquaresBetween(location, targetLocation);

        // Check if there are any squares between the current location and the target location

        if (squaresBetween != null) {
            for (Square square : squaresBetween) {
                if (!square.isEmpty()) {
                    return false; // Bishop cannot move if there is an obstruction
                }
            }

            // Bishop can move if the target location is either empty or contains an opponent's piece

            return targetLocation.isEmpty() || targetLocation.getPiece().getColor() != color;
        }

        return false; // Bishop can only move diagonally, so return false if not on a diagonal path
    }

    @Override
    public void move(String destination) {

        Square targetLocation = location.getBoard().getSquareAt(destination);

        // Set the bishop as the piece in the target location

        targetLocation.setPiece(this);

        // Clear the current location

        location.clear();

        // Set the new location of the bishop

        location = targetLocation;

        // Move is complete, so switch to the next player's turn

        location.getBoard().nextPlayer();
    }

    @Override
    public String toString() {
        // Use uppercase 'B' for white bishop and lowercase 'b' for black bishop

        return color == ChessBoard.WHITE ? "B" : "b";
    }
}