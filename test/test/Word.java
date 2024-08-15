package test;

import java.util.Arrays;
import java.util.Objects;


public class Word {

	public Tile[] tiles;
    public int col;
    public int row;
    public boolean vertical;

    public Word (Tile[] t,int row,int col,boolean  ver){
        this.tiles=t;
        this.row=row;
        this.col=col;
        this.vertical=ver;
    }
    public Tile[] getTiles(){
        return  this.tiles;
    }
    public int getRow(){
        return  this.row;
    }
    public int getCol(){
        return  this.col;
    }
    public boolean  GetVer(){
        return  this.vertical;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Word w = (Word) obj; // Cast to Tile
        return col == w.col && row == w.row && this.vertical==w.vertical && Arrays.equals(this.tiles, w.tiles);
}
     @Override
    public int hashCode() {
        return Objects.hash(this.tiles, this.row,this.col,this.vertical);
    }
}
