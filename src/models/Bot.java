package models;

import Factories.BotStrategy;
import Strategies.BotPlayingStrategy.BotPlayingStrategy;

public class Bot extends Player {
    BotDifficultyLevel botDifficultyLevel;
    BotPlayingStrategy playingStrategy;
    Bot(String name, char symbol, BotDifficultyLevel botDifficultyLevel){
        super(name,symbol, PlayerType.Bot);
        this.botDifficultyLevel=botDifficultyLevel;
        this.playingStrategy= BotStrategy.getBotPlayingStrategyObj(botDifficultyLevel);
    }
    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }
    @Override
    public Move decideMove(Board board){
        return playingStrategy.decideMove(this,board);
    }
}
