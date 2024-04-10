package test;

import java.util.ArrayList;

public class Board {
    private final static int BOARD_SIZE = 15;
    private final static int CENTER = BOARD_SIZE / 2;
    private static Board board = null;
    private Tile[][] grid;
    final private int[][] tileMultiplier;
    private int[][] wordMultiplier;

    public Board() {
        this.grid = new Tile[BOARD_SIZE][BOARD_SIZE];

        this.tileMultiplier = new int[][] {
                { 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1 }, // 1
                { 1, 1, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 1, 1 }, // 2
                { 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1 }, // 3
                { 2, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 2 }, // 4
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, // 5
                { 1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1 }, // 6
                { 1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1 }, // 7
                { 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1 }, // 8
                { 1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1 }, // 9
                { 1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1 }, // 10
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, // 11
                { 2, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 2 }, // 12
                { 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1 }, // 13
                { 1, 1, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 1, 1 }, // 14
                { 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1 } // 15
        };

        this.wordMultiplier = new int[][] {
                { 3, 1, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 1, 3 }, // 1
                { 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1 }, // 2
                { 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1 }, // 3
                { 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1 }, // 4
                { 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1 }, // 5
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, // 6
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, // 7
                { 3, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 3 }, // 8
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, // 9
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, // 10
                { 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1 }, // 11
                { 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1 }, // 12
                { 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1 }, // 13
                { 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1 }, // 14
                { 3, 1, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 1, 3 } // 15
        };

    }

    public static Board getBoard() {
        if (board == null) {
            board = new Board();
        }
        return board;
    }

    public Tile[][] getTiles() {
        Tile[][] copyArray = new Tile[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.arraycopy(grid[i], 0, copyArray[i], 0, BOARD_SIZE);
            }
        }
        return copyArray;
    }

    // public void print(Tile[][] grid) {
    // for (int i = 0; i < BOARD_SIZE; i++) {
    // for (int j = 0; j < BOARD_SIZE; j++) {
    // if (grid[i][j] == null) {
    // System.out.print("0 ");
    // } else {
    // System.out.print(grid[i][j].getLetter() + " ");
    // }
    // }
    // System.out.println();
    // }
    // }

    public boolean boardLegal(Word word) {
        boolean isVertical = word.isVertical();
        int wordCount = word.getWordTiles().length;
        int row = word.getRow();
        int col = word.getCol();

        if (row < 0 || col < 0 || row >= BOARD_SIZE || col >= BOARD_SIZE)
            return false;

        if (isVertical) {
            if (row + wordCount > BOARD_SIZE) {
                return false;
            }
        } else {
            if (col + wordCount > BOARD_SIZE) {
                return false;
            }
        }
        if (grid[CENTER][CENTER] == null) {
            if (isVertical) {
                if (col != CENTER) {
                    return false;
                }
                if (row + wordCount < CENTER || row > CENTER) {
                    return false;
                }
            } else {
                if (row != CENTER) {
                    return false;
                }
                if (col + wordCount < CENTER || col > CENTER) {
                    return false;
                }
            }
        } else {
            boolean flag = false;
            if (isVertical) {
                if (row - 1 >= 0 && grid[row - 1][col] != null) {
                    flag = true;
                } else if (row + wordCount + 1 < BOARD_SIZE && grid[row + wordCount + 1][col] != null) {
                    flag = true;
                } else {
                    for (int i = row; i < row + wordCount; i++) {
                        if (grid[i][col] != null) {
                            flag = true;
                            break;
                        }
                        if (col - 1 >= 0 && grid[i][col - 1] != null) {
                            flag = true;
                            break;
                        }
                        if (col + 1 < BOARD_SIZE && grid[i][col + 1] != null) {
                            flag = true;
                            break;
                        }
                    }
                }
            } else {
                if (col - 1 >= 0 && grid[row - 1][col] != null) {
                    flag = true;
                } else if (col + wordCount + 1 < BOARD_SIZE && grid[row][col + wordCount + 1] != null) {
                    flag = true;
                } else {
                    for (int i = col; i < col + wordCount; i++) {
                        if (grid[row][i] != null) {
                            flag = true;
                            break;
                        }
                        if (row - 1 >= 0 && grid[row - 1][i] != null) {
                            flag = true;
                            break;
                        }
                        if (row + 1 < BOARD_SIZE && grid[row + 1][i] != null) {
                            flag = true;
                            break;
                        }
                    }
                }
            }
            if (!flag) {
                return false;
            }
        }
        return true;
    }

    public int getScore(Word word) {
        int multiplier = 1;
        int score = 0;
        int row = word.getRow();
        int col = word.getCol();

        if (word.isVertical()) {
            for (int i = row; i < row + word.getWordTiles().length; i++) {
                multiplier *= wordMultiplier[i][col];
                if (i == CENTER && col == CENTER) {
                    wordMultiplier[i][col] = 1;
                }
                score += word.getWordTiles()[i - row].getScore() * tileMultiplier[i][col];
            }
        } else {
            for (int i = col; i < col + word.getWordTiles().length; i++) {
                multiplier *= wordMultiplier[row][i];
                if (row == CENTER && i == CENTER) {
                    wordMultiplier[row][i] = 1;
                }
                score += word.getWordTiles()[i - col].getScore() * tileMultiplier[row][i];
            }
        }
        return score * multiplier;
    }

    /////////////////////////////////////////

    public ArrayList<Word> getWords(Word word) {
        ArrayList<Word> words = new ArrayList<Word>();
        int row = word.getRow();
        int col = word.getCol();
        int wordCount = word.getWordTiles().length;
        Tile[] wordTiles = word.getWordTiles();
        if (word.isVertical()) {
            for (int i = row; i < row + wordCount; i++) {
                if (wordTiles[i - row] == null) {
                    wordTiles[i - row] = grid[i][col];
                } else {
                    if (col - 1 >= 0 && grid[i][col - 1] != null) {
                        words.add(collectWord(i, col - 1));
                    } else if (col + 1 < BOARD_SIZE && grid[i][col + 1] != null) {
                        words.add(collectWord(i, col + 1));
                    }
                }
            }
            words.add(new Word(wordTiles, row, col, true));
        } else {
            for (int i = col; i < col + wordCount; i++) {
                if (wordTiles[i - col] == null) {
                    wordTiles[i - col] = grid[row][i];
                } else {
                    if (row - 1 >= 0 && grid[row - 1][i] != null) {
                        words.add(collectWordVertical(row - 1, i));
                    } else if (row + 1 < BOARD_SIZE && grid[row + 1][i] != null) {
                        words.add(collectWordVertical(row + 1, i));
                    }
                }
            }
            words.add(new Word(wordTiles, row, col, false));
        }
        return words;
    }

    private Word collectWordVertical(int row, int col) {
        int j = row;
        while (j >= 0 && grid[j][col] != null) {
            j--;
        }
        j++;
        int k = row;
        while (k < BOARD_SIZE && grid[k][col] != null) {
            k++;
        }
        k--;
        Tile[] tiles = new Tile[k - j + 1];
        int start = j;
        int l = 0;
        while (j <= k) {
            tiles[l] = grid[j][col];
            l++;
            j++;
        }
        return new Word(tiles, start, col, true);
    }

    private Word collectWord(int row, int col) {
        int j = col;
        while (j >= 0 && grid[row][j] != null) {
            j--;
        }
        j++;
        int k = col;
        while (k < BOARD_SIZE && grid[row][k] != null) {
            k++;
        }
        k--;
        Tile[] tiles = new Tile[k - j + 1];
        int start = j;
        int l = 0;
        while (j <= k) {
            tiles[l] = grid[row][j];
            l++;
            j++;
        }
        return new Word(tiles, start, row, false);
    }

    private boolean dictionaryLegal(Word word) {
        return true;
    }

    public int tryPlaceWord(Word word) {
        if (!boardLegal(word)) {
            return 0;
        }
        if (!dictionaryLegal(word)) {
            return 0;
        }
        int row = word.getRow();
        int col = word.getCol();

        for (Tile tile : word.getWordTiles()) {
            if (tile != null)
                grid[row][col] = tile;
            if (word.isVertical()) {
                row++;
            } else {
                col++;
            }
        }

        ArrayList<Word> words = getWords(word);
        int score = 0;
        for (Word w : words) {
            score += getScore(w);
        }
        return score;
    }
}
