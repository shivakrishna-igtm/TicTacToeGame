package Controllers;


import models.Game;
import models.GameStatus;
import models.Player;

import java.util.List;

public class GameController {

    public Game createGame(int dimension, List<Player>players){
        try {
            return Game.getBuilder()
                    .setDimension(dimension)
                    .setPlayers(players)
                    .build();
        }
        catch(Exception e){
            return  null;
        }
    }
    public void undo(Game game){
        game.Undo();
        executeNextMove(game);
    }
    public void displayBoard(Game game) {
        game.display();
    }
    public GameStatus getGameStatus(Game game){
        return game.getGameStatus();
    }
    public void executeNextMove(Game game){
        game.makeMove();
    }
    public Player getWinner(Game game) {
        return game.getWinner();
    }

}
