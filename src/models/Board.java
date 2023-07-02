package models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<List<Cell>> board;
    private int dimension;
    Board(int dimension){
        board=new ArrayList<>();
        for(int i=0;i<dimension;i++){
            List<Cell> l=new ArrayList<>();
            for(int j=0;j<dimension;j++){
                l.add(new Cell(i,j));
            }
            board.add(l);
        }
    }

    public void display(){
        for(int i=0;i<board.size();i++){
            for(int j=0;j<board.get(i).size();j++){
                if(board.get(i).get(j).cellState.equals(CellState.Empty)){
                    System.out.printf("|  |");
                }
                else{
                    System.out.printf("|"+board.get(i).get(j).getPlayer().getSymbol()+"|");
                }
            }
            System.out.println();
        }
    }
    public List<List<Cell>> getBoard(){
        return board;
    }


}
