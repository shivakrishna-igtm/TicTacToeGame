package models;

import models.Board;
import models.Cell;
import models.PlayerType;

import java.util.Scanner;

public class Player {
    private char symbol;
    private PlayerType playerType;
    private String name;
    Player(String name,char symbol,PlayerType playerType){
        this.name=name;
        this.symbol=symbol;
        this.playerType=playerType;

    }
    public Move decideMove(Board playBoard){
        System.out.println("Enter the row you want to play: Mr."+this.name);
        Scanner sc=new Scanner(System.in);
        int row=sc.nextInt();
        System.out.println("Enter the col you want to play");
        int col=sc.nextInt();
        while(playBoard.getBoard().get(row).get(col).getCellState().equals(CellState.filled)){
            System.out.println("the row or the column is already picked try different cell");
            System.out.println("Enter the row you want to play: Mr."+this.name);
            row=sc.nextInt();
            System.out.println("Enter the col you want to play");
            col=sc.nextInt();
        }
        return new Move(this,new Cell(row,col));
    }
    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
