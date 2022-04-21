package com.ouragan.pixelwarapi.Grid;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table
public class GirdData {
    @Id
    @SequenceGenerator(
            name="grid_sequence",
            sequenceName = "grid_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "grid_sequence"
    )

    private Long id;
    private int[][] Grid;
    private int height;
    private int width;

    public GirdData() {
        this.height = GridConfig.initialHeight;
        this.width = GridConfig.initialWidth;
        this.Grid = new int[this.width][this.height];

        fillArray(this.Grid, GridConfig.initialColor);
    }

    public GirdData(GirdData gridData){
        this.height = gridData.getHeight();
        this.width = gridData.getWidth();
        this.Grid = new int[this.width][this.height];


        copyArray(gridData.getGrid(), this.Grid);
    }

    public GirdData(Long id, int[][] Grid, final int height, final int width) {
        this.id = id;
        this.height = height;
        this.width = width;
        this.Grid = new int[this.width][this.height];

        copyArray(Grid, this.Grid);
    }

    public GirdData(int[][] Grid, final int height, final int width) {
        this.height = height;
        this.width = width;
        this.Grid = new int[this.width][this.height];

        copyArray(Grid, this.Grid);
    }

    public GirdData(final int height, final int width, final int filler) {
        this.height = height;
        this.width = width;
        this.Grid = new int[this.width][this.height];

        fillArray(this.Grid, filler);
    }

    public int[][] getGrid() {
        return Grid;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setGrid(int[][] grid) {
        copyArray(grid, this.Grid);
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setGrid(int w, int h, int[][] grid) {
        this.height = h;
        this.width = w;
        this.Grid = new int[w][h];
        copyArray(grid, this.Grid);
    }

    public void setPixel(int x, int y, int color){
        if(x < 0 || x >= width || y < 0 || y >= height) return;

        this.Grid[x][y] = color;
    }

    @Override
    public String toString() {

        return "GirdData{" +
                "id=" + id +
                ", Grid=" +
                ", height=" + height +
                ", width=" + width +
                '}';
    }

    public static void copyArray(int[][] from, int[][] to){

        if(from.length == 0 || to.length == 0) return;

        int maxI = Math.min(from.length, to.length);
        int maxJ = Math.min(from[0].length, to[0].length);

        for(int i=0; i<maxI; ++i){
            for(int j=0; j<maxJ; ++j){
                to[i][j] = from[i][j];
            }
        }
    }

    public static void fillArray(int[][] array, int num){
        if(array.length == 0) return;

        for(int i=0; i<array.length; ++i){
            for(int j=0; j<array[0].length; ++j){
                array[i][j] = num;
            }
        }
    }

    public void printCurrentGrid(){
        for(int[] arr : Grid){
            for(int i : arr)
                System.out.print(i + " ");
            System.out.println();
        }
    }

    public void resetGrid(int color){
        fillArray(this.Grid, color);
    }

    public int fillOverArea(int x1, int y1, int x2, int y2, int color){
        int count = 0;
        int xmax = Math.min(x2, this.width - 1);
        int ymax = Math.min(y2, this.height - 1);

        for (int i = Math.max(x1, 0); i <= xmax; ++i){
            for (int j = Math.max(y1, 0); j <= ymax; ++j){
                this.Grid[i][j] = color;
                ++count;
            }
        }

        return count;
    }

    public void resize(int newWidth, int newHeight, int fillColor){
        int[][] newGrid = new int[newWidth][newHeight];
        fillArray(newGrid, fillColor);
        copyArray(this.Grid, newGrid);
        this.Grid = newGrid;
        this.height = newHeight;
        this.width = newWidth;
    }
}