package com.javarush.task.task35.task3513;

import java.util.*;


public class Model {
    private static final int FIELD_WIDTH = 4;

    int score;
    int maxTile;


    private Tile[][] gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];

    public Model() {
        resetGameTiles();

        score = 0;
        maxTile = 2;

    }

    private List<Tile> getEmptyTiles(){
        List<Tile> list = new ArrayList<>();
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (gameTiles[i][j].isEmpty()) {
                    list.add(gameTiles[i][j]);
                }
            }
        }
        return list;
    }

    private void addTile(){
        List<Tile> emptyTile = getEmptyTiles();
        if (emptyTile != null && emptyTile.size() != 0) {
            emptyTile.get((int) (emptyTile.size() * Math.random())).value = (Math.random() < 0.9) ? 2 : 4;
        }

    }

    void resetGameTiles(){
        this.gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                this.gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();
    }

    private boolean compressTiles(Tile[] tiles) {
        boolean isChange = false;
        for (int i = 0, j = 0; j < tiles.length - 1; j++, i++) {
            if (tiles[i].value == 0) {
                Tile tile = tiles[i];
                System.arraycopy(tiles, i + 1, tiles, i, tiles.length - 1 - i);
                tiles[tiles.length - 1] = tile;
                i--;

                isChange = true;
            }
        }
        return isChange;
    }

    private boolean mergeTiles(Tile[] tiles) {
        boolean isChange = false;

        for (int i = 1; i < tiles.length; i++) {
            if ((tiles[i - 1].value == tiles[i].value)) {
                tiles[i - 1].value *=2;

                if (tiles[i - 1].value > maxTile) {
                    maxTile = tiles[i-1].value;
                }
                score +=tiles[i-1].value;

                tiles[i] = new Tile();

                isChange = true;
            }
        }

        return isChange;
    }

    void left(){
        boolean isChange = false;

        for (int i = 0; i < FIELD_WIDTH; i++) {
            if (compressTiles(gameTiles[i]) | mergeTiles(gameTiles[i])) {
                isChange = true;
            }
        }

        if(isChange) {
            addTile();
        }
    }
}
