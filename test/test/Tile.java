package test;
import java.util.Objects;
import java.util.Random;

public class Tile {
    public final int int_score;
    public final char char_letter;
    private Tile(char  char_letter ,int int_score){
        this.char_letter = char_letter;
        this.int_score = int_score;
    }
    public static Tile createTile(char letter, int score) {
        return new Tile(letter, score);
    }
    @Override
        public boolean equals(Object obj){
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Tile tile = (Tile) obj; // Cast to Tile
            return char_letter == tile.char_letter && int_score == tile.int_score;
    }
    @Override
    public int hashCode() {
        return Objects.hash(char_letter, int_score);
    }

    public static class Bag{
        public int[] letters;
        public Tile[] tiles;
        private static Bag bag = null;
        private Bag(){
            this.letters = new int[]{9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1};
            //this.tiles=new Tile[26];
            this.tiles = new Tile[] {createTile('A', 1),createTile('B', 3),createTile('C', 1),createTile('D', 2),createTile('E', 1),createTile('F', 4),createTile('G', 2),createTile('H', 4),createTile('I', 1),createTile('J', 8),createTile('K', 5),createTile('L', 1),createTile('M', 3),createTile('N', 1),createTile('O', 1),createTile('P', 3),createTile('Q', 10),createTile('R', 1),createTile('S', 1),createTile('T', 3),createTile('U', 1),createTile('V', 4),createTile('W', 4),createTile('X', 8),createTile('Y', 4),createTile('Z', 10)};
        }

        public static Bag getBag() 
        {
            if(bag==null)
            {
                bag=new Bag();
            }
            return bag;
        }
        

        public Tile getRand(){
            boolean flag = true;
            for (int letter : letters) {
                if(letter!=0)
                    flag=false;
            }
            if(flag)
                return null;
            Random random = new Random();
            int randomLetter = random.nextInt(26);
            if(this.letters[randomLetter]==0) return null;
            else{
                this.letters[randomLetter]=--this.letters[randomLetter];
                return this.tiles[randomLetter];
            }
        }

        public Tile getTile(char c){
            boolean flag = true;
            for (int letter : letters) {
                if(letter!=0)
                    flag=false;
            }
            if(flag)
                return null;
            int index_letter= c-'A';
            if(this.letters[index_letter]!=0)
            {
                this.letters[index_letter]=this.letters[index_letter]-1;
                return this.tiles[index_letter];
            }
            else
            {
                return null;
            }
        }

        public void put(Tile t){
            int [] allLetters = new int[] {9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1};
            int index_letter= t.char_letter-'A';
            if(this.letters[index_letter]!=allLetters[index_letter])
            {
                this.letters[index_letter]++;
            }
        }

        public int size(){
            int sum=0;
            for (int x : letters) {
                sum=sum+x;
            }
            return sum;
        }

        public int[] getQuantities() {
            return this.letters.clone();
        }

        }
    }
