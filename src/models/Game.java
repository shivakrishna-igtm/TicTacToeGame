package models;

import Exceptions.InvalidParameterException;
import Strategies.GameWinningStrategies.GameWinningStrategy;
import Strategies.GameWinningStrategies.OrderOneWinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private Board playBoard;
    private List<Player> players;
    private List<Move> moves;
    private GameWinningStrategy gameWinningStrategy;
    private int nextPlayerIndex;
    private Player winner;
    private GameStatus gameStatus;
    private int count=0;
    private int lastMovedPlayer;
    private Game(){

    }
    public void Undo(){
        int toRemove=moves.size()-1;
        Move lastMove=moves.get(toRemove);
        int row=lastMove.getCell().getRow();
        int col=lastMove.getCell().getCol();
        Player currPlayer=lastMove.getPlayer();
        gameWinningStrategy.undoTheHashMap(playBoard,currPlayer,lastMove.getCell());
        playBoard.getBoard().get(row).get(col).setCellState(CellState.Empty);
        playBoard.getBoard().get(row).get(col).setPlayer(null);
        moves.remove(toRemove);
        nextPlayerIndex=lastMovedPlayer;
        count--;
    }
    public Board getBoard() {
        return playBoard;
    }

    public void setBoard(Board playBoard) {
        this.playBoard = playBoard;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public List<Player> getPlayers() {
        return players;
    }
    public void setGameWinningStrategy(GameWinningStrategy gameWinningStrategy){
        this.gameWinningStrategy=gameWinningStrategy;
    }
    public void setNextPlayerIndex(int nextPlayerIndex){
        this.nextPlayerIndex=nextPlayerIndex;
    }
    public int getNextPlayerIndex(){
        return nextPlayerIndex;
    }
    public void setPlayers(List<Player> players){
        this.players=players;
    }

    public void setWinner(Player winner){
        this.winner=winner;
    }
    public Player getWinner(){
        return winner;
    }
    public GameStatus getGameStatus() {
        return gameStatus;
    }
    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }
    public void display(){
        this.playBoard.display();
    }
    public static Builder getBuilder(){
        return new Builder();
    }
    public void makeMove(){
        int playerToMove=nextPlayerIndex;
        Player currPlayer=players.get(playerToMove);
        System.out.println("Its "+currPlayer.getName()+"'s turn");
        Move move=currPlayer.decideMove(playBoard);
        int row=move.getCell().getRow();
        int col=move.getCell().getCol();
        System.out.println("Move happened at row :"+row+" col:"+col);
        playBoard.getBoard().get(row).get(col).setCellState(CellState.filled);
        playBoard.getBoard().get(row).get(col).setPlayer(currPlayer);
        moves.add(move);
        if(gameWinningStrategy.checkWinner(playBoard,currPlayer,move.getCell())){
            gameStatus=GameStatus.Ended;
            winner=currPlayer;
        }
        count++;
        if(count==playBoard.getBoard().get(0).size()*playBoard.getBoard().get(0).size()){
            gameStatus=GameStatus.DRAW;
            winner=null;
        }
        lastMovedPlayer=nextPlayerIndex;
        nextPlayerIndex=(nextPlayerIndex+1)%(playBoard.getBoard().get(row).size()-1);
    }
    public static class Builder {
        private int dimension;
        private List<Player> players;

        Scanner sc=new Scanner(System.in);
        public Builder setDimension(int dimension){
            this.dimension=dimension;
            return this;
        }
        public Builder setPlayers(List<Player> players){

            this.players=players;
            return this;
        }
        public boolean validate() throws InvalidParameterException {
            if(dimension<3 || dimension>10){
                throw new InvalidParameterException("parameters correct pass chey. ");
            }
            else if(players.size()!=dimension-1){
                throw  new InvalidParameterException("models.Player size sari chusko");
            }
            return true;

        }
        public Game build() throws Exception{
            Game game=new Game();
            validate();
            game.setPlayers(this.players);
            game.setNextPlayerIndex(0);
            game.setGameStatus(GameStatus.IN_PROGRESS);
            game.setGameWinningStrategy(new OrderOneWinningStrategy(this.dimension));
            game.setMoves(new ArrayList<>());
            game.setBoard(new Board(this.dimension));

            return game;
        }
    }
}
