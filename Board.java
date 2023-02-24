import org.w3c.dom.html.HTMLTitleElement;

import java.awt.*;
import java.sql.SQLOutput;


public class Board {
    public static final int MAX_BOARD = 5;

    private Tile[][] board = new Tile[MAX_BOARD][MAX_BOARD];
    private int[][] intBoard = new int[MAX_BOARD][MAX_BOARD];
    private int xCord;
    private int yCord;

    public void generateBoard() {

    }



    public void placeTile() {

    }

    public void placeStarterTile() {

    }


    public static void main(String[] args) {

        Board bo = new Board();

        Tile a = Tile.generateTile();
        Tile b = Tile.generateTile();
        Tile c = Tile.generateTile();
        Tile d = Tile.generateTile();
        Tile e = Tile.generateTile();
        Tile f = Tile.generateTile();
        Tile g = Tile.generateTile();
        Tile h = Tile.generateTile();
        Tile i = Tile.generateTile();
        Tile j = Tile.generateTile();
        Tile k = Tile.generateTile();
        Tile l = Tile.generateTile();
        i.TileDisplay(); j.TileDisplay(); k.TileDisplay();l.TileDisplay();
        a.TileDisplay(); b.TileDisplay(); c.TileDisplay(); d.TileDisplay(); e.TileDisplay(); f.TileDisplay(); g.TileDisplay(); h.TileDisplay();

        bo.showBoard(1);

    }

        public void fillBoard(){
            Tile a = Tile.generateTile();
            Tile b = Tile.generateTile();
            Tile c = Tile.generateTile();

            board[MAX_BOARD / 2][MAX_BOARD / 2] = a;
            board[MAX_BOARD / 2 - 1][MAX_BOARD / 2 + 1] = b;
            board[MAX_BOARD / 2][MAX_BOARD / 2 + 1] = c;

            for(int i = 0;i < 4;i++){
                for(int j = 0;j < MAX_BOARD*MAX_BOARD;j++){
                    Tile.allTiles.get(j).set(i, TileColours.EMPTY_TILE_SIDE);
                }
            }
//            Tile.allTiles.get()
        }
        public void showBoard(int check){
        int line = 4;
        int start = 0;
        int counter = 0;
            if(check > 1){
                start = line;
                line *= check;

            }
            if(check == 2){
                for(int i = 0; i < 4;i++){
                    System.out.print("\t ");
                    for(int j = start; j < line ;j++){
                            System.out.print((Tile.allTiles.get(j).get(i)));
                            counter++;
                    }
                    System.out.println();
                }

                    showBoard(1);
            }
            if(check == 1) {
                for (int i = 0; i < 4; i++) {
                    for (int j = start; j < line; j++) {
                            System.out.print((Tile.allTiles.get(j).get(i)));
                            counter++;
                    }
                    System.out.println();
                }


                    showBoard(2);
                }
            }
        }

