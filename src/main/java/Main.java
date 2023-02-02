import java.util.Scanner;

// ENUM OF QUESTIONS
public class Main {

    public static void main(String[] args) throws InterruptedException {
        clearScreen();
        Scanner scanner = new Scanner(System.in);
        System.out.println(TitleScreen.displaySetup());
        scanner.nextLine();
        clearScreen();
        System.out.println(TitleScreen.displayTitle());
        System.out.println("Start Game? y/n");
        String playerInput = scanner.nextLine();
        playerInput = playerInput.toLowerCase().substring(0,1);

        if( playerInput.equals("y") ){
        // LOAD GAME
            Game game = new Game();
            clearScreen();
            TitleScreen.displayIntro();
            scanner.nextLine();
            clearScreen();
        // WELCOME
            do{
                System.out.println("What do you want to do");
                game.updateScannerString();
                String[] choice = TextParser.parseText(game.getScannerString());
                game.processChoice(choice);
            }while(!game.quitGame);
        }
        else System.out.println("Good Bye");
        System.out.println("Game End");
    }

    private static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}