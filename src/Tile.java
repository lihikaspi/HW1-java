public class Tile {
    private final int value; // cannot change after initialization // 0 for empty tile

    /**
     * default constructor
     */
    public Tile() {
        this.value = 0;
    }

    /**
     * constructor
     * @param value tile number
     */
    public Tile(int value) {
        this.value = value;
    }

    /**
     * get tile number
     * @return tile number
     */
    public int getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Tile)) {
            return false;
        }
        Tile tile = (Tile) other;
        return value == tile.value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }
}