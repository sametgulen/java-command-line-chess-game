public class Pawn extends Piece {
    boolean initialLocation = true;
    public Pawn(int color, Square location) {
        super(color, location);
    }

    public boolean canMove(String to) {
        boolean validMove = false;
        Square targetLocation = location.getBoard().getSquareAt(to);
        int rowDistance = location.getRowDistance(targetLocation);

        // Move forward
        if (location.isAtSameColumn(targetLocation)) {
            // White pawn
            if (color == ChessBoard.WHITE) {
                // Move forward one square
                if (rowDistance == 1 && location.row < targetLocation.row && targetLocation.isEmpty()) {
                    validMove = true;
                }
                // Move forward two squares from initial position
                else if (initialLocation && rowDistance == 2 && location.row == 1 && location.row < targetLocation.row && targetLocation.isEmpty()) {
                    Square midSquare = location.getBoard().getBoard()[location.row + 1][location.column];
                    if (midSquare.isEmpty()) {
                        validMove = true;
                    }
                }
            }
            // Black pawn
            else {
                // Move forward one square
                if (rowDistance == 1 && location.row > targetLocation.row && targetLocation.isEmpty()) {
                    validMove = true;
                }
                // Move forward two squares from initial position
                else if (initialLocation && rowDistance == 2 && location.row == 6 && location.row > targetLocation.row && targetLocation.isEmpty()) {
                    Square midSquare = location.getBoard().getBoard()[location.row - 1][location.column];
                    if (midSquare.isEmpty()) {
                        validMove = true;
                    }
                }
            }
        }
        // Attacking diagonals
        else if (location.isNeighborColumn(targetLocation)) {
            // White pawn
            if (color == ChessBoard.WHITE) {
                validMove = !targetLocation.isEmpty() && targetLocation.getPiece().getColor() == ChessBoard.BLACK && location.row < targetLocation.row;
            }
            // Black pawn
            else if (color == ChessBoard.BLACK) {
                validMove = !targetLocation.isEmpty() && targetLocation.getPiece().getColor() == ChessBoard.WHITE && location.row > targetLocation.row;
            }
        }

        return validMove;
    }


    @Override
    public void move(String to) {
        Square targetLocation = location.getBoard().getSquareAt(to);
        //promoteToQueen
        if (targetLocation.isAtLastRow(color)) {
            targetLocation.putNewQueen(color);
        } else {
            targetLocation.setPiece(this);
        }
        //clear previous location
        location.clear();
        //update current location
        location = targetLocation;
        location.getBoard().nextPlayer();
    }





    @Override
    public String toString() {
        return color == ChessBoard.WHITE ? "P" : "p";
    }
}