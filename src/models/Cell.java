package models;

import models.CellState;

public class Cell {
    private Player player;
    private int row;
    private int col;
    CellState cellState=CellState.Empty;
    Cell(int row,int col){
        this.row=row;
        this.col=col;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    public int getRow(){
        return row;
    }
    public int getCol(){
        return col;
    }
    public void setCellState(CellState cellState){
        this.cellState=cellState;
    }
    public CellState getCellState(){
        return cellState;
    }

}
