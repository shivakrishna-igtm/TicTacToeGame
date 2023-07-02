package Strategies.GameWinningStrategies;
import models.*;
public interface GameWinningStrategy {
    public boolean checkWinner(Board playBoard,Player player,Cell cellMove);
    public void undoTheHashMap(Board board,Player player,Cell cell);
}
