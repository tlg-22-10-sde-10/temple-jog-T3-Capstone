import java.util.Scanner;

// ENUM OF QUESTIONS
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Start Game? y/n");
        String playerInput = scanner.nextLine();
        playerInput = playerInput.toLowerCase().substring(0,1);

        if( playerInput.equals("y") ){
        // LOAD GAME
            Game game = new Game();
        // WELCOME
            do{
                System.out.println("What do you want to do");
                game.updateScannerString();
                String[] choice = TextParser.parseText(game.getScannerString());
                game.processChoice(choice);
            }while( game.quitGame != true );
        }
        else System.out.println("Good Bye");
        System.out.println("Game End");
    }

}