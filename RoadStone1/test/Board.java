package test;

import java.util.ArrayList;

import test.Tile.Bag;

public class Board {
    private boolean firstWordOnBoard = false;
    private final static int BOARD_SIZE = 15;
    private final static int CENTER = BOARD_SIZE / 2;
    private final int[][] score;
    private static Board board = null;
    private Tile[][] grid;
    private ArrayList<Word> onBoardWords = new ArrayList<>(); // change to set
    private boolean middleTileActive = true;

    public Board() {
        this.grid = new Tile[BOARD_SIZE][BOARD_SIZE];
        // for (int i = 0; i < BOARD_SIZE; i++) {
        // for (int j = 0; j < BOARD_SIZE; j++) {
        // this.grid[i][j] = null;
        // }
        // }

        // 9 = "Star"
        // 2 = "Double word score"
        // 3 = "Tripple word score"
        // 4 = "Double letter score"
        // 5 = "Tripple letter score"

        this.score = new int[][] {
                { 3, 0, 0, 4, 0, 0, 0, 3, 0, 0, 0, 4, 0, 0, 3 }, // 1
                { 0, 2, 0, 0, 0, 5, 0, 0, 0, 5, 0, 0, 0, 2, 0 }, // 2
                { 0, 0, 2, 0, 0, 0, 4, 0, 4, 0, 0, 0, 2, 0, 0 }, // 3
                { 4, 0, 0, 2, 0, 0, 0, 4, 0, 0, 0, 2, 0, 0, 4 }, // 4
                { 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0 }, // 5
                { 0, 5, 0, 0, 0, 5, 0, 0, 0, 5, 0, 0, 0, 5, 0 }, // 6
                { 0, 0, 4, 0, 0, 0, 4, 0, 4, 0, 0, 0, 4, 0, 0 }, // 7
                { 3, 0, 0, 4, 0, 0, 0, 9, 0, 0, 0, 4, 0, 0, 3 }, // 8
                { 0, 0, 4, 0, 0, 0, 4, 0, 4, 0, 0, 0, 4, 0, 0 }, // 9
                { 0, 5, 0, 0, 0, 5, 0, 0, 0, 5, 0, 0, 0, 5, 0 }, // 10
                { 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0 }, // 11
                { 4, 0, 0, 2, 0, 0, 0, 4, 0, 0, 0, 2, 0, 0, 4 }, // 12
                { 0, 0, 2, 0, 0, 0, 4, 0, 4, 0, 0, 0, 2, 0, 0 }, // 13
                { 0, 2, 0, 0, 0, 5, 0, 0, 0, 5, 0, 0, 0, 2, 0 }, // 14
                { 3, 0, 0, 4, 0, 0, 0, 3, 0, 0, 0, 4, 0, 0, 3 } // 15
        };

    }

    public static Board getBoard() {
        if (board == null) {
            board = new Board();
        }
        return board;
    }

    public Tile[][] getTiles() {
        // check yaron if its good
        Tile[][] copyArray = new Tile[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.arraycopy(grid[i], 0, copyArray[i], 0, BOARD_SIZE);
            }
        }
        return copyArray;
    }

    public void print(Tile[][] grid) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (grid[i][j] == null) {
                    System.out.print("0 ");
                } else {
                    System.out.print(grid[i][j].getLetter() + " ");
                }
            }
            System.out.println();
        }
    }

    public boolean boardLegal(Word word) {
        boolean isVertical = word.isVertical();
        int wordCount = word.getWordTiles().length;
        int row = word.getRow();
        int col = word.getCol();

        if (row < 0 || col < 0 || row >= BOARD_SIZE || col >= BOARD_SIZE)
            return false;

        if (isVertical) {
            if (row + wordCount > BOARD_SIZE)
                return false;
        } else if (col + wordCount > BOARD_SIZE)
            return false;

        if (!firstWordOnBoard) {
            if (isVertical) {
                for (int i = 0; i < wordCount; i++) {
                    if (col == CENTER && row + i == CENTER) {
                        firstWordOnBoard = true;
                        return true;
                    }
                }
                return false;
            } else {
                for (int i = 0; i < wordCount; i++) {
                    if (row == CENTER && col + i == CENTER) {
                        firstWordOnBoard = true;
                        return true;
                    }
                }
                return false;
            }
        } else {
            if (!isVertical) {
                if ((col > 1 && grid[row][col - 1] != null) || (col < BOARD_SIZE - 1 && grid[row][col + 1] != null))
                    return true;
            } else if ((row > 1 && grid[row - 1][col] != null) || (row < BOARD_SIZE - 1 && grid[row + 1][col] != null))
                return true;

            for (int i = 0; i < wordCount; i++) {
                final int currRow = isVertical ? row + i : row;
                final int currCol = isVertical ? col : col + i;
                if (grid[currRow][currCol] != null)
                    return true;
                else if (!isVertical) {
                    if ((row > 1 && grid[currRow - 1][currCol] != null)
                            || (row < BOARD_SIZE - 1 && grid[currRow + 1][currCol] != null))
                        return true;
                } else if ((col > 1 && grid[currRow][currCol - 1] != null)
                        || (col < BOARD_SIZE - 1 && grid[currRow][currCol + 1] != null))
                    return true;
            }
        }

        return true;
    }

    public int getTotalWordScore(Word word) {
        int totalWordScore = 0;
        for (Tile tile : word.getWordTiles()) {
            totalWordScore += tile.getScore();
        }
        return totalWordScore;
    }

    public int getScore(Word word) {
        final int wordCount = word.getWordTiles().length;
        final boolean isVertical = word.isVertical();
        int totalScore = 0;
        int doubleWordScoreTiles = 0;
        int tripleWordScoreTiles = 0;

        for (int i = 0; i < wordCount; i++) {
            final int row = isVertical ? word.getRow() + i : word.getRow();
            final int col = isVertical ? word.getCol() : word.getCol() + i;

            int tileScore;

            if (word.getWordTiles()[i] == null) {
                // totalScore += grid[row][col].getScore();
                tileScore = grid[row][col].getScore();
            } else {
                // if (grid[row][col] == null) {
                tileScore = word.getWordTiles()[i].getScore();
            }
            // 9 = "Star"
            // 2 = "Double word score"
            // 3 = "Tripple word score"
            // 4 = "Double letter score"
            // 5 = "Tripple letter score"

            switch (this.score[row][col]) {
                case 2:
                    totalScore += tileScore;
                    doubleWordScoreTiles++;
                    break;
                case 3:
                    totalScore += tileScore;
                    tripleWordScoreTiles++;
                    break;
                case 4:
                    totalScore += tileScore * 2;
                    break;
                case 5:
                    totalScore += tileScore * 3;
                    break;
                case 9:
                    totalScore += tileScore;
                    doubleWordScoreTiles++;
                    break;
                case 0:
                    totalScore += tileScore;
                    break;
                default:
                    break;
            }
        }

        if (doubleWordScoreTiles > 0) {
            totalScore *= 2 * doubleWordScoreTiles;
        }
        if (tripleWordScoreTiles > 0) {
            totalScore *= 3 * tripleWordScoreTiles;
        }

        return totalScore;
    }

    /////////////////////////////////////////

    public ArrayList<Word> getWords(Word word) {
        ArrayList<Word> newWords = new ArrayList<>();

        for (int i = 0; i < word.getWordTiles().length; i++) {
            int row = word.isVertical() ? word.getRow() + i : word.getRow();
            int col = word.isVertical() ? word.getCol() : word.getCol() + i;

            Tile tile;
            if (word.getWordTiles()[i] == null)
                tile = grid[row][col];
            else
                tile = word.getWordTiles()[i];

            // Check horizontally for new words
            if (word.isVertical()) {
                String newHorizontalWord = getHorizontalWord(row, col, tile);
                if (newHorizontalWord.length() > 1 && !isOnBoard(word)) {
                    newWords.add(new Word(convertStringToTiles(newHorizontalWord), row,
                            col - newHorizontalWord.indexOf(tile.getLetter()), false));
                }

                // if (row > 1 && grid[row - 1][col] != null && word.getWordTiles()[0] != null)
                // {
                // String newVerticalWord2 = getVerticalWord(row - 1, col, grid[row - 1][col]);
                // if (newVerticalWord2.length() > 1 && !isOnBoard(word)) {
                // newWords.add(new Word(convertStringToTiles(newVerticalWord2), row -
                // newVerticalWord2.indexOf(grid[row - 1][col].getLetter()), col, false));
                // }
                // }

                // if (row < BOARD_SIZE - 1 && grid[row + 1][col] != null &&
                // word.getWordTiles()[i] != null) {
                // String newVerticalWord3 = getVerticalWord(row + 1, col, grid[row + 1][col]);
                // if (newVerticalWord3.length() > 1 && !isOnBoard(word)) {
                // newWords.add(new Word(convertStringToTiles(newVerticalWord3), row, col,
                // false));
                // }
                // }
            }

            // Check vertically for new words
            if (!word.isVertical()) {

                String newVerticalWord = getVerticalWord(row, col, tile);
                if (newVerticalWord.length() > 1 && !isOnBoard(word)) {
                    newWords.add(new Word(convertStringToTiles(newVerticalWord),
                            row - newVerticalWord.indexOf(tile.getLetter()), col, true));
                }

                // if (col > 1 && grid[row][col - 1] != null && word.getWordTiles()[i] != null)
                // {
                // String newHorizontalWord2 = getHorizontalWord(row, col - 1, grid[row][col -
                // 1]);
                // if (newHorizontalWord2.length() > 1 && !isOnBoard(word)) {
                // newWords.add(new Word(convertStringToTiles(newHorizontalWord2), row, col -
                // newHorizontalWord2.indexOf(grid[row][col - 1].getLetter()), true));
                // }
                // }

                // if (col < BOARD_SIZE - 1 && grid[row][col + 1] != null &&
                // word.getWordTiles()[i] != null) {
                // String newHorizontalWord3 = getHorizontalWord(row, col + 1, grid[row][col +
                // 1]);
                // if (newHorizontalWord3.length() > 1 && !isOnBoard(word)) {
                // newWords.add(new Word(convertStringToTiles(newHorizontalWord3), row, col,
                // true));
                // }
                // }
            }
        }

        int col = word.getCol();
        int row = word.getRow();

        if (word.isVertical()) {
            if (word.getRow() > 1 && grid[row - 1][col] != null && word.getWordTiles()[0] != null) {
                String newVerticalWord2 = getVerticalWord(row - 1, col, grid[row - 1][col]);
                if (newVerticalWord2.length() > 1 && !isOnBoard(word)) {
                    newWords.add(new Word(convertStringToTiles(newVerticalWord2),
                            row - newVerticalWord2.indexOf(grid[row - 1][col].getLetter()), col, false));
                }
            }

            if (row < BOARD_SIZE - 1 && grid[row + 1][col] != null
                    && word.getWordTiles()[word.getWordTiles().length - 1] != null) {
                String newVerticalWord3 = getVerticalWord(row + 1, col, grid[row + 1][col]);
                if (newVerticalWord3.length() > 1 && !isOnBoard(word)) {
                    newWords.add(new Word(convertStringToTiles(newVerticalWord3), row, col, false));
                }
            }
        }

        if (!word.isVertical()) {
            if (col > 1 && grid[row][col - 1] != null && word.getWordTiles()[0] != null) {
                String newHorizontalWord2 = getHorizontalWord(row, col - 1, grid[row][col - 1]);
                if (newHorizontalWord2.length() > 1 && !isOnBoard(word)) {
                    newWords.add(new Word(convertStringToTiles(newHorizontalWord2), row,
                            col - newHorizontalWord2.indexOf(grid[row][col - 1].getLetter()), true));
                }
            }

            if (col < BOARD_SIZE - 1 && grid[row][col + 1] != null
                    && word.getWordTiles()[word.getWordTiles().length - 1] != null) {
                String newHorizontalWord3 = getHorizontalWord(row, col + 1, grid[row][col + 1]);
                if (newHorizontalWord3.length() > 1 && !isOnBoard(word)) {
                    newWords.add(new Word(convertStringToTiles(newHorizontalWord3), row, col, true));
                }
            }
        }

        newWords.add(word);
        return newWords;
    }

    private String getHorizontalWord(int row, int col, Tile tile) {
        int originalCol = col;
        StringBuilder horizontalWord = new StringBuilder();
        while (col >= 0 && grid[row][col] != null) {
            horizontalWord.insert(0, grid[row][col].getLetter());
            col--;
        }
        // col++;
        col = originalCol;
        if (col + 1 < BOARD_SIZE && grid[row][col + 1] != null)
            col++;
        while (col < BOARD_SIZE && grid[row][col] != null) {
            horizontalWord.append(grid[row][col].getLetter());
            col++;
        }
        return horizontalWord.toString();
    }

    private String getVerticalWord(int row, int col, Tile tile) {
        int originalRow = row;
        int originalCol = col;
        StringBuilder verticalWord = new StringBuilder();
        boolean isOnBoard = true;

        if (grid[row][col] == null) {
            grid[row][col] = tile;
            isOnBoard = false;
        }

        while (row >= 0 && grid[row][col] != null) {
            verticalWord.insert(0, grid[row][col].getLetter());
            row--;
        }
        // row++;
        row = originalRow;
        if (row + 1 < BOARD_SIZE)
            row++;
        while (row < BOARD_SIZE && grid[row][col] != null) {
            verticalWord.append(grid[row][col].getLetter());
            row++;
        }

        if (!isOnBoard)
            grid[originalRow][originalCol] = null;
        return verticalWord.toString();
    }

    private Tile[] convertStringToTiles(String word) {
        Tile[] tiles = new Tile[word.length()];
        Bag b = Tile.Bag.getBag();
        for (int i = 0; i < word.length(); i++) {
            int tileScore = b.getTileScore(word.charAt(i));
            tiles[i] = new Tile(word.charAt(i), tileScore);
        }
        return tiles;
    }

    private boolean dictionaryLegal(Word word) {
        return true;
    }

    private boolean isOnBoard(Word word) {
        for (Word onBoardWord : onBoardWords) {
            if (onBoardWord.equals(word))
                return true;
        }
        return false;
    }

    public int tryPlaceWord(Word word) {
        if (!boardLegal(word)) {
            return 0;
        }

        int totalScore = 0;
        ArrayList<Word> newWords = getWords(word);

        for (Word newWord : newWords) {
            if (!dictionaryLegal(newWord)) {
                return 0;
            }
            if (!isOnBoard(newWord)) {
                totalScore += getScore(newWord);
                onBoardWords.add(newWord);
            }
        }

        for (int i = 0; i < word.getWordTiles().length; i++) {
            int row = word.isVertical() ? word.getRow() + i : word.getRow();
            int col = word.isVertical() ? word.getCol() : word.getCol() + i;
            if (grid[row][col] == null) {
                grid[row][col] = word.getWordTiles()[i];

                if (middleTileActive) {
                    score[CENTER][CENTER] = 0;
                    middleTileActive = false;
                }
            }
        }

        // print(grid);
        return totalScore;
    }
}
