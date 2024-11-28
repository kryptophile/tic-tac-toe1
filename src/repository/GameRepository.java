package repository;

import model.Game;

import java.util.HashMap;
import java.util.Map;

public class GameRepository {
    private Map<String, Game> gameMap = new HashMap<>();

    public void save(Game game){
        gameMap.put(game.getName(), game);
    }

    public Game getGameByName(String name){
        return gameMap.get(name);
    }
}
