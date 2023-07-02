package Strategies.GameWinningStrategies;
import models.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOneWinningStrategy implements GameWinningStrategy{

    List<HashMap<Character,Integer>> rowSymbolsCount=new ArrayList<>();
    List<HashMap<Character,Integer>> colSymbolsCount=new ArrayList<>();
    HashMap<Character,Integer> topLeftDiagonal=new HashMap<>();
    HashMap<Character,Integer> topRightDiagonal=new HashMap<>();
    public OrderOneWinningStrategy(int dimension){
        for(int i=0;i<dimension;i++){
            rowSymbolsCount.add(new HashMap<>());
            colSymbolsCount.add(new HashMap<>());
        }
    }
    public boolean isTopLeftDiagonal(int row,int col){
        return col==row;
    }
    boolean isTopRightDiagonal(int row,int col,int dimension){
        return col+row== dimension-1;
    }
    @Override
    public boolean checkWinner(Board playBoard, Player player, Cell cellMove) {
        char symbol=player.getSymbol();
        int row=cellMove.getRow();
        int col=cellMove.getCol();
        int dimension=playBoard.getBoard().get(row).size();
        if(!rowSymbolsCount.get(row).containsKey(symbol)){
            rowSymbolsCount.get(row).put(symbol,0);
        }
        rowSymbolsCount.get(row).put(symbol,rowSymbolsCount.get(row).get(symbol)+1);
        if(!colSymbolsCount.get(col).containsKey(symbol)){
            colSymbolsCount.get(col).put(symbol,0);
        }
        colSymbolsCount.get(col).put(symbol,colSymbolsCount.get(col).get(symbol)+1);
        if(isTopLeftDiagonal(row,col)){
            if(!topLeftDiagonal.containsKey(symbol)){
                topLeftDiagonal.put(symbol,0);
            }
            topLeftDiagonal.put(symbol,topLeftDiagonal.get(symbol)+1);
        }
        if(isTopLeftDiagonal(row,col)){
            if(!topLeftDiagonal.containsKey(symbol)){
                topLeftDiagonal.put(symbol,0);
            }
            topLeftDiagonal.put(symbol,topLeftDiagonal.get(symbol)+1);
        }
        if(isTopRightDiagonal(row,col, dimension)){
            if(!topRightDiagonal.containsKey(symbol)){
                topRightDiagonal.put(symbol,0);
            }
            topRightDiagonal.put(symbol,topRightDiagonal.get(symbol)+1);
        }
        if(rowSymbolsCount.get(row).get(symbol) == dimension) return true;
        if(colSymbolsCount.get(col).get(symbol) == dimension) return true;


        if(isTopRightDiagonal(row,col,dimension) &&
                topRightDiagonal.get(symbol) == dimension) return true;
        if(isTopLeftDiagonal(row,col) &&
                topLeftDiagonal.get(symbol) == dimension) return true;

        return false;
    }
    public void undoTheHashMap(Board playBoard,Player player,Cell cell){
        int row=cell.getRow();
        int col= cell.getCol();
        char symbol=player.getSymbol();
        int dimension=playBoard.getBoard().get(0).size();
        rowSymbolsCount.get(row).put(symbol,rowSymbolsCount.get(row).get(symbol)-1);
        colSymbolsCount.get(col).put(symbol,colSymbolsCount.get(col).get(symbol)-1);
        if(isTopRightDiagonal(row,col,dimension)){
            topRightDiagonal.put(symbol,topRightDiagonal.get(symbol)-1);
        }
        if(isTopLeftDiagonal(row,col)){
            topLeftDiagonal.put(symbol,topLeftDiagonal.get(symbol)-1);
        }
    }



}
