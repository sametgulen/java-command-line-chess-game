public class Rook extends Piece {
    public Rook(int color, Square location) {

        super(color, location);
    }

    // Checks if the rook can move to the specified destination

    @Override
    public boolean canMove(String destination) {
        Square targetLocation = location.getBoard().getSquareAt(destination);
        Square[] squaresBetween = location.getBoard().getSquaresBetween(location, targetLocation);

        // If there are squares between the current location and the target location

        if (squaresBetween != null) {

            // Check if any square between the rook's current location and the target location is occupied

            for (Square square : squaresBetween) {
                if (!square.isEmpty()) {
                    return false; // Rook cannot move if any square in between is occupied
                }
            }

            // Rook can move to the target location if it is either empty or occupied by an opponent's piece

            return targetLocation.isEmpty() || targetLocation.getPiece().getColor() != color;
        }

        return false;// Rook cannot move if the target location is not reachable in a straight line
    }


    // Moves the rook to the specified destination

    @Override
    public void move(String destination) {
        Square targetLocation = location.getBoard().getSquareAt(destination);
        targetLocation.setPiece(this);// Set the rook as the piece on the target location
        location.clear(); // Clear the current location of the rook
        location = targetLocation; // Update the location to the target location
        location.getBoard().nextPlayer(); // Move to the next player's turn
    }

    // Returns the string representation of the rook based on its color

    @Override
    public String toString() {
        return color == ChessBoard.WHITE ? "R" : "r";
    }
}