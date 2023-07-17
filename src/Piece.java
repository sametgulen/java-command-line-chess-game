public abstract class Piece {
    protected int color;
    protected Square location;

    // Constructor for initializing the color and location of the piece

    public Piece(int color, Square location) {
        this.color = color;
        this.location = location;
    }

    // Abstract method to check if the piece can move to the specified destination

    public abstract boolean canMove(String destination);

    // Abstract method to move the piece to the specified destination

    public abstract void move(String destination);

    // Getter method to retrieve the color of the piece

    public int getColor() {
        return color;
    }
}