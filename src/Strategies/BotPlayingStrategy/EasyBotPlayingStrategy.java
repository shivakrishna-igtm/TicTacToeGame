package Strategies.BotPlayingStrategy;

import models.*;

public class EasyBotPlayingStrategy implements BotPlayingStrategy {
    @Override
    public Move decideMove(Player player,Board playBoard) {
        for(int i=0;i<playBoard.getBoard().get(0).size();i++){
            for(int j=0;j<playBoard.getBoard().get(0).size();j++){
                if(playBoard.getBoard().get(i).get(j).getCellState().equals(CellState.Empty)){
                    return new Move(player,playBoard.getBoard().get(i).get(j));
                }
            }
        }
        return null;
    }
}
