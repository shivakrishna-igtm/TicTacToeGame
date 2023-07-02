package Factories;

import Strategies.BotPlayingStrategy.BotPlayingStrategy;
import Strategies.BotPlayingStrategy.EasyBotPlayingStrategy;
import Strategies.BotPlayingStrategy.RandomStrategy;
import models.BotDifficultyLevel;

public class BotStrategy {
    public static BotPlayingStrategy getBotPlayingStrategyObj(BotDifficultyLevel difficultyLevel){
        if(difficultyLevel.equals(BotDifficultyLevel.EASY)){
            return new EasyBotPlayingStrategy();
        }
        else {
            return new RandomStrategy();
        }
    }
}
