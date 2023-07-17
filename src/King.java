public class King extends Piece {
    public King(int color, Square location) {
        super(color, location);
    }

    @Override
    public boolean canMove(String destination) {

        // Get the target location

        Square targetLocation = location.getBoard().getSquareAt(destination);

        // Calculate the distance between the current location and the target location

        int rowDistance = location.getRowDistance(targetLocation);
        int colDistance = location.getColDistance(targetLocation);

        // Check if the target location is within a range of 1 row and 1 column away

        if (rowDistance <= 1 && colDistance <= 1) {

            // Check if the target location is empty or occupied by an opponent's piece

            return targetLocation.isEmpty() || targetLocation.getPiece().getColor() != color;
        }

        return false;
    }

    @Override
    public void move(String destination) {

        // Get the target location

        Square targetLocation = location.getBoard().getSquareAt(destination);

        // Set the current piece to the target location

        targetLocation.setPiece(this);

        // Clear the current location

        location.clear();

        // Update the current location to the target location

        location = targetLocation;

        // Move to the next player's turn

        location.getBoard().nextPlayer();
    }

    @Override
    public String toString() {

        // Return a string representation of the King piece based on its color

        return color == ChessBoard.WHITE ? "K" : "k";
    }
}