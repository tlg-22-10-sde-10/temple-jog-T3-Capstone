import java.util.Scanner;

/**
 * Created by dev0 on 2/1/23.
 */
public class Main {

    public static void main(String[] args) {
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
            Game game = new Game();
            System.out.println("game loads");
        }
        else System.out.println("Good Bye");
        clearScreen();
    }

    private static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}