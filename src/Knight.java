public class Knight extends Piece {
    public Knight(int color, Square location) {
        super(color, location);
    }

    @Override
    public boolean canMove(String destination) {

        // Get the target location based on the destination string

        Square targetLocation = location.getBoard().getSquareAt(destination);

        // Calculate the distance between the current location and the target location

        int rowDistance = location.getRowDistance(targetLocation);
        int colDistance = location.getColDistance(targetLocation);

        // Check if the move is valid for a knight

        if ((rowDistance == 2 && colDistance == 1) || (rowDistance == 1 && colDistance == 2)) {

            // Return true if the target location is empty or has a piece of a different color

            return targetLocation.isEmpty() || targetLocation.getPiece().getColor() != color;
        }

        // Return false if the move is not valid for a knight

        return false;
    }

    @Override
    public void move(String destination) {

        // Get the target location based on the destination string

        Square targetLocation = location.getBoard().getSquareAt(destination);

        // Set the knight as the piece in the target location

        targetLocation.setPiece(this);

        // Clear the current location of the knight

        location.clear();

        // Update the knight's location to the target location

        location = targetLocation;

        // Move to the next player's turn

        location.getBoard().nextPlayer();
    }

    @Override
    public String toString() {

        // Represent the knight as "N" for white or "n" for black

        return color == ChessBoard.WHITE ? "N" : "n";
    }
}