package test;

import java.util.Arrays;
import java.util.Random;

public class Tile {
    final public char letter;
    final public int score;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + letter;
        result = prime * result + score;
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
        Tile other = (Tile) obj;
        if (letter != other.letter)
            return false;
        if (score != other.score)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Tile [letter=" + letter + ", score=" + score;
    }

    // public Tile() {
    // this.letter = null;
    // this.score = null;
    // }

    private Tile(char letter, int score) {
        this.letter = letter;
        this.score = score;
    }

    public static class Bag {
        private static Bag bag = null;
        private int[] tilesQuaninty = new int[26];
        private int[] originalQuantity = new int[26];
        private Tile[] tiles = new Tile[26];

        @Override
        public String toString() {
            return "Bag [tilesQuaninty=" + Arrays.toString(tilesQuaninty) + ", tiles=" + Arrays.toString(tiles) + "]";
        }

        private Bag() {
            tilesQuaninty = new int[] {
                    9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1
            };

            System.arraycopy(tilesQuaninty, 0, originalQuantity, 0, tilesQuaninty.length);

            tiles = new Tile[] {
                    new Tile('A', 1),
                    new Tile('B', 3),
                    new Tile('C', 3),
                    new Tile('D', 2),
                    new Tile('E', 1),
                    new Tile('F', 4),
                    new Tile('G', 2),
                    new Tile('H', 4),
                    new Tile('I', 1),
                    new Tile('J', 8),
                    new Tile('K', 5),
                    new Tile('L', 1),
                    new Tile('M', 3),
                    new Tile('N', 1),
                    new Tile('O', 1),
                    new Tile('P', 3),
                    new Tile('Q', 10),
                    new Tile('R', 1),
                    new Tile('S', 1),
                    new Tile('T', 1),
                    new Tile('U', 1),
                    new Tile('V', 4),
                    new Tile('W', 4),
                    new Tile('X', 8),
                    new Tile('Y', 4),
                    new Tile('Z', 10)
            };
        }

        public Tile getRand() {
            // must add: if bag empty, return null;

            Random rand = new Random();
            int tileCell = rand.nextInt(26);

            if (tilesQuaninty[tileCell] == 0)
                return null;
            else {
                tilesQuaninty[tileCell]--;
                return tiles[tileCell];
            }
        }

        public Tile getTile(char tileChar) {
            if (tileChar < 'A' || tileChar > 'Z')
                return null;

            int tileCell = tileChar - 'A';

            if (tilesQuaninty[tileCell] == 0)
                return null;
            else {
                tilesQuaninty[tileCell]--;
                return tiles[tileCell];
            }
        }

        public void put(Tile tileReturn) {
            int tileCell = tileReturn.letter - 'A';

            if (tilesQuaninty[tileCell] < originalQuantity[tileCell])
                tilesQuaninty[tileCell]++;
        }

        public int size() {
            int count = 0; // counter for tiles number
            for (int i = 0; i < 26; i++) {
                count += tilesQuaninty[i];
            }

            return count;
        }

        public int[] getQuantities() {
            int[] copyTiles = new int[tilesQuaninty.length];
            System.arraycopy(tilesQuaninty, 0, copyTiles, 0, tilesQuaninty.length);
            return copyTiles;
        }

        public static Bag getBag() {
            if (bag == null) {
                // Initialize the bag if it's null
                bag = new Bag();
            }
            return bag;
        }
    }

}
