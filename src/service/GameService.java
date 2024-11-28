package service;

import enums.CellValue;
import model.Board;
import model.Game;
import model.User;
import repository.GameRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GameService {

    private GameRepository gameRepository;

    //dependency injection
    public GameService(GameRepository gameRepository){
        this.gameRepository = gameRepository;
    }

    public void createGame(String gameName, User player1, User player2, Integer dimensions){
        List<User> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        Game game = new Game(gameName, new Board(dimensions), players);

        //testing
//        players.clear();
//        gameName = "random name";

        gameRepository.save(game);
    }

    public void playGame(String gameName) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Game game = gameRepository.getGameByName(gameName);
        Integer turn = 0;
        String input;
        while(!hasGameEnded(game)){
            User player = getNextTurnPlayer(game.getPlayers(), turn);
            printBoard(game.getBoard());
            System.out.println("Please make move : " + player.name);
            input = br.readLine();
            String[] cellIndex = input.split(" ");
            Integer row = Integer.parseInt(cellIndex[0]);
            Integer col = Integer.parseInt(cellIndex[1]);

            CellValue value = cellIndex[2].equals("X") ? CellValue.X : CellValue.O;
            game.getBoard().cells.get(row).get(col).setValue(player, value);

            if(isThereAWinner(game.getBoard(), row, col)){
                System.out.printf("Player : %s won !!!!", player.name);
                return;
            }
            turn++;
        }

    }

    private User getNextTurnPlayer(List<User> players, Integer turn){
        turn = turn % (players.size());
        return players.get(turn);
    }

    private boolean hasGameEnded(Game game){
        Board board = game.getBoard();
        Integer size = board.cells.size();

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(board.cells.get(i).get(j).getValue() == CellValue.EMPTY)
                    return false;
            }
        }
        return true;
    }

    private boolean isValidCell(int size, int row, int col){
        return row >= 0 && col >= 0 && row < size && col < size;
    }

    private boolean isThereAWinner(Board board, int row, int col) {
        int size = board.cells.size();
        int count = 0;

        //row fix
        for(int i = 0; i < size; i++){
            if(board.cells.get(row).get(col).getValue() == board.cells.get(row).get(i).getValue())
                count++;
        }
        if(count == size)
            return true;

        //col fix
        count = 0;
        for(int i = 0; i < size; i++){
            if(board.cells.get(row).get(col).getValue() == board.cells.get(i).get(col).getValue())
                count++;
        }
        if(count == size)
            return true;


        //diagonal down
        count = 0;
        for(int i = 0; i < size; i++){
            if(board.cells.get(row).get(col).getValue() == board.cells.get(i).get(i).getValue())
                count++;
        }
        if(count == size)
            return true;


        //diagonal up
        count = 0;
        for(int i = 0; i < size; i++){
            if(board.cells.get(row).get(col).getValue() == board.cells.get(size - 1 - i).get(i).getValue())
                count++;
        }
        if(count == size)
            return true;
        return false;
    }


    private void printBoard(Board board){
        int size = board.cells.size();
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                System.out.print(board.cells.get(i).get(j).getValue() + "\t");
            }
            System.out.println();
        }
    }
}
