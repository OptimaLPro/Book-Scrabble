package test;

import test.Tile.Bag;

public class Board {
    private boolean firstWordOnBoard = false;
    private static Board board = null;
    Tile[][] grid;

    public Board() {
        this.grid = new Tile[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                this.grid[i][j] = null;
            }
        }
    }

    public static Board getBoard() {
        if (board == null) {
            board = new Board();
        }
        return board;
    }

    public boolean boardLegal(Word word) {
        final int wordCount = word.getWordTiles().length;

        if (word.getRow() < 0 || word.getCol() < 0)
            return false;

        if (!firstWordOnBoard) {
            if (word.isVertical()) {
                if (word.getCol() != 7)
                    return false;
            } else {
                if (word.getRow() != 7)
                    return false;
            }
        }

        if (word.isVertical()) {
            if (word.getRow() + wordCount > 15)
                return false;
        } else if (word.getCol() + wordCount > 15)
            return false;
        else {
            this.firstWordOnBoard = true;
        }

        return true;
    }

    public dictionaryLegal(){
        return true;
    }

    public getWords(Word word){
        
    }
}
