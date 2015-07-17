package rev.model.map.core;

import java.util.Iterator;

/**
 * A tile map that uses a two-dimensional array to store the values.
 * 
 *
 * 
 */
public class BaseTileMap implements TileMap {

    /**
     * The underlying array.
     * 
     */
    private Tile[][] data;

    /**
     * The width of the map.
     * 
     */
    private final int width;

    /**
     * The height of the map.
     * 
     */
    private final int height;

    /**
     * Initializes the map.
     * 
     * @param width
     *            the width of the map, in tiles.
     * @param height
     *            the height of the map, in tiles.
     * @param data
     *            the tiles that comprise the map.
     */
    public BaseTileMap(int width, int height, Tile[][] data) {
        this.width = width;
        this.height = height;
        this.data = data;
    }

    @Override
    public Iterator<Tile> iterator() {
        return new BaseTileMapIterator(this);
    }

    @Override
    public Tile getTile(int x, int y) {
        return data[x][y];
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    /**
     * An iterator over the tile map.
     * 
     */
    private class BaseTileMapIterator implements Iterator<Tile> {

        /**
         * The underlying map.
         * 
         */
        private BaseTileMap data;

        /**
         * The size of the tile map.
         * 
         */
        private final int size;

        /**
         * The next tile to return.
         * 
         */
        private int next = 0;

        /**
         * Initializes the iterator.
         * 
         * @param data
         *            the tile map.
         */
        public BaseTileMapIterator(BaseTileMap data) {
            this.data = data;
            this.size = data.getWidth() * data.getHeight();
        }

        @Override
        public boolean hasNext() {
            if (next < this.size) {
                return true;
            }
            return false;
        }

        @Override
        public Tile next() {
            Tile tile = this.data.getTile(this.next / this.data.getWidth(),
                    this.next % this.data.getHeight());
            this.next++;
            return tile;
        }

        @Override
        public void remove() {

            /*
             * Not applicable.
             */
        }
    }
}