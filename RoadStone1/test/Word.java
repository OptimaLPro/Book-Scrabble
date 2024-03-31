package test;

import java.util.Arrays;

public class Word {
    private final Tile[] wordTiles;
    private final int row, col;
    private final boolean vertical;

    public Word(Tile[] wordTiles, int row, int col, boolean vertical) {
        this.wordTiles = wordTiles;
        this.row = row;
        this.col = col;
        this.vertical = vertical;
    }

    public Tile[] getWordTiles() {
        return wordTiles;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isVertical() {
        return vertical;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(wordTiles);
        result = prime * result + row;
        result = prime * result + col;
        result = prime * result + (vertical ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Word other = (Word) obj;
        if (!Arrays.equals(wordTiles, other.wordTiles))
            return false;
        if (row != other.row)
            return false;
        if (col != other.col)
            return false;
        if (vertical != other.vertical)
            return false;
        return true;
    }
}
