package test;

import java.util.Arrays;
import java.util.Random;

public class Tile {
    final char letter;
    final int score;

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

    private Tile(char letter, int score) {
        this.letter = letter;
        this.score = score;
    }

    public static class Bag {
        private static Bag bag = null;
        int[] tilesQuaninty = new int[26];
        int[] originalQuantity = new int[26];
        Tile[] tiles = new Tile[26];

        @Override
        public String toString() {
            return "Bag [tilesQuaninty=" + Arrays.toString(tilesQuaninty) + ", tiles=" + Arrays.toString(tiles) + "]";
        }

        private Bag() {
            tilesQuaninty[0] = 9;
            tilesQuaninty[1] = 2;
            tilesQuaninty[2] = 2;
            tilesQuaninty[3] = 4;
            tilesQuaninty[4] = 12;
            tilesQuaninty[5] = 2;
            tilesQuaninty[6] = 3;
            tilesQuaninty[7] = 2;
            tilesQuaninty[8] = 9;
            tilesQuaninty[9] = 1;
            tilesQuaninty[10] = 1;
            tilesQuaninty[11] = 4;
            tilesQuaninty[12] = 2;
            tilesQuaninty[13] = 6;
            tilesQuaninty[14] = 8;
            tilesQuaninty[15] = 2;
            tilesQuaninty[16] = 1;
            tilesQuaninty[17] = 6;
            tilesQuaninty[18] = 4;
            tilesQuaninty[19] = 6;
            tilesQuaninty[20] = 4;
            tilesQuaninty[21] = 2;
            tilesQuaninty[22] = 2;
            tilesQuaninty[23] = 1;
            tilesQuaninty[24] = 2;
            tilesQuaninty[25] = 1;

            System.arraycopy(tilesQuaninty, 0, originalQuantity, 0, tilesQuaninty.length);

            tiles[0] = new Tile('A', 1);
            tiles[1] = new Tile('B', 3);
            tiles[2] = new Tile('C', 3);
            tiles[3] = new Tile('D', 2);
            tiles[4] = new Tile('E', 1);
            tiles[5] = new Tile('F', 4);
            tiles[6] = new Tile('G', 2);
            tiles[7] = new Tile('H', 4);
            tiles[8] = new Tile('I', 1);
            tiles[9] = new Tile('J', 8);
            tiles[10] = new Tile('K', 5);
            tiles[11] = new Tile('L', 1);
            tiles[12] = new Tile('M', 3);
            tiles[13] = new Tile('N', 1);
            tiles[14] = new Tile('O', 1);
            tiles[15] = new Tile('P', 3);
            tiles[16] = new Tile('Q', 10);
            tiles[17] = new Tile('R', 1);
            tiles[18] = new Tile('S', 1);
            tiles[19] = new Tile('T', 1);
            tiles[20] = new Tile('U', 1);
            tiles[21] = new Tile('V', 4);
            tiles[22] = new Tile('W', 4);
            tiles[23] = new Tile('X', 8);
            tiles[24] = new Tile('Y', 4);
            tiles[25] = new Tile('Z', 10);
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
