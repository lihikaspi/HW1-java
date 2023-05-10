public class Tile {
    public final int value; // cannot change after initialization // 0 for empty tile

    public Tile() {
        this.value = 0;
    }
    public Tile(int value) {
        this.value = value;
    }

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