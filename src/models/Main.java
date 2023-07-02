package models;

import Controllers.GameController;
import models.Bot;
import models.BotDifficultyLevel;
import models.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        System.out.println("Enter the dimension");

        Scanner sc= new Scanner(System.in);
        int dimension=sc.nextInt();
        int toIterate=dimension-1;
        System.out.println("Are there any bots in this game? y/n");
        String isBotString = sc.next();
        List<Player> players=new ArrayList<>();
        if(isBotString.equals("y")){
            toIterate -= 1;
            // Initialise the bot
            System.out.println("Enter the name of the bot: ");
            String name = sc.next();

            System.out.println("Enter the symbol of the bot: ");
            char symbol = sc.next().charAt(0);

            players.add(new Bot(name, symbol, BotDifficultyLevel.EASY));
        }


        for(int i=0;i<toIterate;i++){
            System.out.println("Enter the name for Player:"+(i+1));
            String name=sc.next();
            System.out.println("Enter the symbol for player:"+(i+1));
            char symbol=sc.next().charAt(0);
            players.add(new Player(name,symbol, PlayerType.Human));
        }
        int playersSize=players.size();
        int playerTurn=0;
        GameController gc=new GameController();
        Game game=gc.createGame(dimension,players);
        System.out.println("The current boards situation is");
        gc.displayBoard(game);
        while(gc.getGameStatus(game).equals(GameStatus.IN_PROGRESS)){
            gc.executeNextMove(game);
            System.out.println("The current boards situation is");
            gc.displayBoard(game);
            if(isBotString.equals("y") && playerTurn==0) {
                playerTurn=(playerTurn+1)%(playersSize);
                continue;
            }
            System.out.println("Do you wish to undo your move .Press y/n");
            String isUndo = sc.next();
            if (isUndo.equals("y")) {
                gc.undo(game);
                System.out.println("The current boards situation is");
                gc.displayBoard(game);
            }
            playerTurn=(playerTurn+1)%(playersSize-1);

        }
        String winner;
        if(gc.getGameStatus(game).equals(GameStatus.DRAW)){
               winner="None";
        }
        else{
            winner=gc.getWinner(game).getName();
        }
        System.out.println("The result of the Game: "+gc.getGameStatus(game)+ "-->Winner is :"+winner);
    }
}