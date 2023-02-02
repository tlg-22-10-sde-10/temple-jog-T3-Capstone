import java.util.Arrays;
import java.util.Scanner;

public class Game {
    Boolean quitGame;
    String scannerString;

    public Game(){
        this.quitGame = false;
    }

    public void updateScannerString(){
        Scanner scanner = new Scanner(System.in);
        String scannerString = scanner.nextLine();
        setScannerString(scannerString);
    }

    public void processChoice(String[] choice){
        String verb = choice[0];
        if(verb.equals("quit")) {
            System.out.println("Are you sure you want to quit?");
            updateScannerString();
            String playerResponse  = getScannerString().toLowerCase().substring(0, 1);
            if( playerResponse.equals("y") ){
                setQuitGame(!getQuitGame());
            }
        }
    }

    public Boolean getQuitGame() { return quitGame; }
    public void setQuitGame(Boolean quitGame) { this.quitGame = quitGame; }
    public String getScannerString() {return scannerString;}
    public void setScannerString(String scannerString) { this.scannerString = scannerString;}
}