import model.User;
import repository.GameRepository;
import service.GameService;

import java.io.IOException;

public class Main {
    public static void main(String[] args)throws IOException {
        System.out.println("Hello world!");

        GameRepository gameRepository = new GameRepository();
        GameService gameService = new GameService(gameRepository);
        User player1 = new User("Kannu");
        User player2 = new User("Anliee");
        gameService.createGame("Game1", player1, player2, 3);
        gameService.playGame("Game1");
    }
}


/*

X O O
O X X
O O X

Entities/model

Matrix/Board

Cell

User

 */