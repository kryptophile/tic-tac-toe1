package model;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private String name;
    private Board board;
    private List<User> players;

    public Game(String name, Board board, List<User> players){
        this.name = name;
        this.board = board;
        this.players = players;
//        this.players.addAll(players);
    }

    public String getName(){
        return this.name;
    }

    public List<User> getPlayers(){
        return this.players;
    }

    public Board getBoard(){
        return this.board;
    }
}
