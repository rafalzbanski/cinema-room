package pl.rafalzbanski.cinema_room.models;

public class Seat {
    private int row;
    private int column;
    private int price;
    private boolean isAvailable = true;

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        this.price = (row >= 1 && row <= 4) ? 8 : 12;
    }

    // Getters and Setters
    public int getRow() { return row; }
    public void setRow(int row) { this.row = row; }

    public int getColumn() { return column; }
    public void setColumn(int column) { this.column = column; }

    public int getPrice() { return price; }

    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    public String getId() {
        return row + "_" + column;
    }
}
