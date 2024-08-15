package test;

import java.util.ArrayList;
import java.util.Arrays;

public  class Board {
    public Tile[][] board=null;
    private static Board boardInstance = null;
    ArrayList<int[]> doubleLetterAqua;
    ArrayList<int[]> tripleLetterBlue;
    ArrayList<int[]> doubleWordYellow;
    ArrayList<int[]> tripleWordRed;
    private Board() {
        board = new Tile[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                board[i][j] = null; // Explicitly setting each tile to null
            }
        }
        
        doubleLetterAqua=new ArrayList<>(Arrays.asList(
            new int[]{0, 3},
            new int[]{0, 11},
            new int[]{2, 6},
            new int[]{2, 8},
            new int[]{3, 0},
            new int[]{3, 7},
            new int[]{3, 14},
            new int[]{6, 2},
            new int[]{6, 6},
            new int[]{6, 8},
            new int[]{6, 12},
            new int[]{7, 3},
            new int[]{7, 11},
            new int[]{8, 2},
            new int[]{8, 6},
            new int[]{8, 8},
            new int[]{8, 12},
            new int[]{11, 0},
            new int[]{11, 7},
            new int[]{11, 14},
            new int[]{12, 6},
            new int[]{12, 8},
            new int[]{14, 3},
            new int[]{14, 11}
        ));
        doubleWordYellow= new ArrayList<>(Arrays.asList(
            new int[]{1, 1},
            new int[]{2, 2},
            new int[]{3, 3},
            new int[]{4, 4},
            new int[]{10, 10},
            new int[]{11, 11},
            new int[]{12, 12},
            new int[]{13, 13},
            new int[]{1, 13},
            new int[]{2, 12},
            new int[]{3, 11},
            new int[]{4, 10},
            new int[]{10, 4},
            new int[]{11, 3},
            new int[]{12, 2},
            new int[]{13, 1}
        ));
        tripleWordRed=new ArrayList<>(Arrays.asList(
            new int[]{0, 0},
            new int[]{0, 7},
            new int[]{0, 14},
            new int[]{7, 0},
            new int[]{7, 14},
            new int[]{14, 0},
            new int[]{14, 7},
            new int[]{14, 14}
        ));
        tripleLetterBlue= new ArrayList<>(Arrays.asList(
            new int[]{1, 5},
            new int[]{1, 9},
            new int[]{5, 1},
            new int[]{5, 5},
            new int[]{5, 9},
            new int[]{5, 13},
            new int[]{9, 1},
            new int[]{9, 5},
            new int[]{9, 9},
            new int[]{9, 13},
            new int[]{13, 5},
            new int[]{13, 9}
        ));
    }
    public static Board createBoard() {
        return new Board();
    }
    public static Board getBoard(){
            if(boardInstance==null)
            {
                boardInstance=new Board();
            }
            return boardInstance;
    }
    public Tile[][] getTiles() {
        Tile[][] copy = new Tile[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                copy[i][j] = this.board[i][j]; // Shallow copy (depending on how deep copy is defined in Tile)
            }
        }
        return copy;
    }
    public boolean boardLegal(Word w){
        if(CheckBoundries(w) == false)
        {
            return false; // check boundries
        }
        if(IsEmpty(this.board)){ // check first word in star
            if(w.vertical)
            {
                if(w.col!=7 || w.row>7 || w.tiles.length+w.row <7) // check boundries
                    return false;
            }
            else
            {
                if(w.row!=7 || w.col>7 ||w.tiles.length+w.col <7)
                    return false;
            }
        }
        else{ // chceck second word and next
            for (int i = 0; i < w.tiles.length; i++) {
                if(w.tiles[i]==null) // checing word with exist letter
                {
                    if(w.vertical){
                        if(this.board[w.row+i][w.col]==null)
                        {
                            return false;
                        }
                    }
                    else{
                        if(this.board[w.row][w.col+i]==null)
                        {
                            return false;
                        }
                    }
                }
                else{ // שלא תכתוב מילה על מילה
                    if(w.vertical){
                        if(this.board[w.row+i][w.col]!=null){
                            return false;
                        }
                        if(w.col<14 && this.board[w.row+i][w.col+1] !=null)
                        {
                            return true;
                        }
                        if(w.col>0 && this.board[w.row+i][w.col-1] !=null)
                        {
                            return true;
                        }
                    }
                    else{
                        if(this.board[w.row][w.col+i]!=null)
                        {
                            return false;
                        }
                        if(w.row<14 && this.board[w.row+1][w.col+i] !=null)
                        {
                            return true;
                        }
                        if(w.row>0 && this.board[w.row-1][w.col+i] !=null)
                        {
                            return true;
                        }
                    }
                   
                }
                
            }
                

        }
        return true;
    }
    public boolean dictionaryLegal(Word w){
        return true;
    }
    public static boolean containsChar(Tile[] array) {
        for (Tile t : array) {
            if (t.letter == '_') {
                return true; // Character found
            }
        }
        return false; // Character not found
    }
    public Tile[] copyArray(ArrayList <Tile> t){
        Tile[] t_final ;
        t_final=new Tile[t.size()];
        for(int j=0;j<t_final.length;j++){
            t_final[j] = t.get(j);
        }
        return t_final;
    }
    public ArrayList<Word> getWords(Word w){
        ArrayList<Word> NewWord = new ArrayList<>();
        ArrayList<Tile>t = new ArrayList<>();
        Tile[] t_final ;
        Word W_push;
        int j;
        int h;
        W_push=new Word(w.tiles, w.row, w.col, w.vertical);
        NewWord.add(W_push);
        if(w.vertical)
        {
            
            /*if(containsChar(w.tiles)==true)
            {
                for(int i=0;i<w.tiles.length;i++)
                {
                    if(w.tiles[i].letter!='_')
                    {
                        t.add(w.tiles[i]);
                    }
                    else{
                        t.add(this.board[w.row+i][w.col]);
                    }
                }
                t_final=copyArray(t);
                W_push = new Word(t_final,w.row,w.col,true);
                NewWord.add(W_push);
                t.clear();
            }*/
            //else{
                for (int i=0; i<w.tiles.length;i++)
                {
                    if(w.tiles[i]!= null){
                        if ((w.col>=1 && this.board[w.row+i][w.col-1]!=null)||
                        (w.col<14 && this.board[w.row+i][w.col+1]!=null)){
                            for (j=0; w.col-j>=0 && this.board[w.row+i][w.col-j]!=null;j++);
                            j--;
                            
                            for (h=w.col-j;h<=14 && this.board[w.row+i][h]!=null ;h++){
                                t.add(this.board[w.row+i][h]);
                            }
                            t_final=copyArray(t);
                            W_push=new Word(t_final, w.col-j, h-1, false);
                            NewWord.add(W_push);
                            t.clear();
                        }
                    }
                }
            //}

        }
        else{
           /* if(containsChar(w.tiles)==true)
            {
                for(int i=0;i<w.tiles.length;i++)
                {
                    if(w.tiles[i].letter!='_')
                    {
                        t.add(w.tiles[i]);
                    }
                    else{
                        t.add(this.board[w.row][w.col+i]);
                    }
                }
                t_final=new Tile[t.size()];
                for(j=0;j<t_final.length;j++){
                    t_final[j] = t.get(j);
                }
                W_push = new Word(t_final,w.row,w.col,true);
                NewWord.add(W_push);
                t.clear();
            } */
            //else {
                for (int i=0; i<w.tiles.length;i++)
                {
                        if ((w.row>=1 && this.board[w.row-1][w.col+i]!=null)||
                        (w.row<14 && this.board[w.row+1][w.col+i]!=null)){
                            for (j=0; w.row-j>=0 && this.board[w.row-j][w.col+i]!=null;j++);
                            j--;
                            
                            for (h=w.row-j;h<=14 && this.board[h][w.col+i]!=null ;h++){
                                t.add(this.board[h][w.col+i]);
                            }
                            t_final=copyArray(t);
                            W_push=new Word(t_final, w.row-j, h-1, false);
                            NewWord.add(W_push);
                            t.clear();
                        }
                }
          //  }
        }
        return NewWord;
    }
    public boolean IsEmpty(Tile[][] board){
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if(board[i][j]!=null)
                    return false;
            }
        }
        return true;
    }
    public boolean CheckBoundries(Word w){
        if(w.col <0 || w.col>=15 || w.row <0 || w.row>=15)
            return false;
        if(w.vertical)
        {
            if(w.row+w.tiles.length>=15)
                return false;
            
        }
        else{
            if(w.col+w.tiles.length>=15)
                return false;
        }
        return true;
    }
    public int getScore(Word w){
        int totalSum=0;
        int sum=0;
        boolean vertical;
        int x, y;
        int scalar=1;
        ArrayList <Word> words=getWords(w);
        for(int i=0; i<words.size();i++){
          x=words.get(i).row;
          y=words.get(i).col;
          vertical= words.get(i).vertical;
          if(vertical ){
            for (int j=0; j<words.get(i).tiles.length;j++, x++){
                    if(words.get(i).tiles[j]==null)
                    {
                        words.get(i).tiles[j]=this.board[x][y];
                    }
                    int[] a={x,y};
                    if(tripleLetterBlue.contains(a)){
                        sum+=words.get(i).tiles[j].int_score*3;
                    }
                    else if(doubleLetterAqua.contains(a)){
                        sum+=words.get(i).tiles[j].int_score*2;
                    }
                    else if(doubleWordYellow.contains(a)){
                        scalar*=2;
                    }
                    else if(tripleWordRed.contains(a)){
                        scalar*=3;
                    }
                    else{
                        sum+=words.get(i).tiles[j].int_score;
                    }
                
            }
        }
        else{
            for (int j=0; j<words.get(i).tiles.length;j++, y++){
                int[] a={x,y};
                if(words.get(i).tiles[j]==null)
                {
                    words.get(i).tiles[j]=this.board[x][y];
                }
                if(tripleLetterBlue.contains(a)){
                    sum+=words.get(i).tiles[j].int_score*3;
                }
                else if(doubleLetterAqua.contains(a)){
                    sum+=words.get(i).tiles[j].int_score*2;
                }
                else if(doubleWordYellow.contains(a)){
                    scalar*=2;
                }
                else if(tripleWordRed.contains(a)){
                    scalar*=3;
                }
                else{
                    sum+=words.get(i).tiles[j].int_score;
                }
            }
        }
            if(IsEmpty(this.board)){
                scalar*=2;
            }
            sum*=scalar;
            totalSum+=sum;
            sum=0;
            scalar=1;
        
        
        }
        return totalSum;
    }
    public int tryPlaceWord(Word w){
        int totalscore=0;
        if (dictionaryLegal(w) && boardLegal(w)){
            totalscore= getScore(w);
            if(w.vertical)
            {
                for(int i=0;i<w.tiles.length;i++)
                {
                    this.board[w.row+i][w.col]=w.tiles[i];
                }
            }
            else
            {
                for(int i=0;i<w.tiles.length;i++)
                {
                    this.board[w.row][w.col+i]=w.tiles[i];
                }
            }
          return totalscore;  
        }    
        return 0;
        }
        
}
