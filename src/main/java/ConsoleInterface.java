import java.util.concurrent.TimeUnit;

public class ConsoleInterface { // Previously TitleScreen

    /*                  CONSTANTS & FIELDS                          */
    static final String titleSplash = "\033[0;32m████████████████████████████████████████████████████████████████████████████████\n█░         █░        █░  █████    █░       ███░ ███████░        ████████████████\n█░         █░        █░   ████    █░        ██░ ███████░        ████████████████\n█░░░░  ░░░░█░ ░░░░░░░█░   ████    █░ ░░░░   ██░ ███████░ ░░░░░░░████████████████\n█████░ █████░ ████████░   ████    █░ ████░  ██░ ███████░ ███████████████████████\n█████░ █████░ ████████░   ███     █░ █████   █░ ███████░ ███████████████████████\n█████░ █████░       ██░    ██     █░ ████   ██░ ███████░       █████████████████\n█████░ █████░ ░░░░░░░█░ ░  █      █░      ░░██░ ███████░ ░░░░░░░████████████████\n█████░ █████░ ████████░ █░    ░   █░ ░░░░░████░ ███████░ ███████████████████████\n█████░ █████░ ████████░ █░    █░  █░ █████████░ ███████░ ███████████████████████\n█████░ █████░        █░ █░    █░  █░ █████████░       █░        ████████████████\n█████░░█████░░░░░░░░░█░░██░░░██░░░█░░█████████░░░░░░░░█░░░░░░░░░████████████████\n██████████████████████████████████████████░ ████████████████████████████████████\n██████████████████████████████████████████░ ███░       █████░      █████████████\n██████████████████████████████████████████░ ██░   ░░    ███░   ░░░  ████████████\n██████████████████████████████████████████░ ██░   ██░░  ███░  ███░░  ███████████\n██████████████████████████████████████████░ ██░  █████░  █░ ███████░ ███████████\n██████████████████████████████████████████░ ██░  █████░  █░ ████████████████████\n██████████████████████████████████████████░ ██░  █████░  █░ ███░   █████████████\n█████████████████████████████████████░ ███  ██░  █████   █░ ████░░   ███████████\n█████████████████████████████████████░  █   ██░   ██     █░  █████░  ███████████\n██████████████████████████████████████░    ███░░        ███░        ████████████\n███████████████████████████████████████░░░█████░░░░░░░░█████░░░░░░░█████████████\033[0m";
    static final Integer CONSOLE_HEIGHT = 25;
    static final Integer CONSOLE_WIDTH = 80;

    // Use the below string for release
    String introFromJSON = "The year is 20XX...\n\nA major government power has learned of an alien race that wants to invade Earth\n    and enslave the human race.\nThey have discovered a secret alien ship that has been here for centuries.\nIt has been disguised as a lost hidden temple the whole time!\nTheir plan is to nuke the temple from orbit. As it's\n            \"\033[1;37mThe only way to be to sure.\033[0m\"\nFrom intel gained, your special ops team has learned that even if\n    the ship is destroyed, a signal will still be sent to the alien home-world!\nYour mission, that you already chose to accept, is to:\n\033[0;33m      -Infiltrate the temple and gain access to the communication device.\n      -Find a way to shut it down.\n      -Get back to the landing zone for extraction before the bomb drops!\n      -You have until sun-down at 18:00 local time.\033[0m\n\n\n\nPress any key to parachute into the LZ...";
//    String introFromJSON = "Press go";   // Use this when testing
    Game game;

    /*                      CONSTRUCTORS                            */
    public ConsoleInterface() {

    }
    public ConsoleInterface(Game game) {
        this.game = game;
    }

    /*                      STATIC METHODS                          */
    public static int displaySetup() {
        String midLines = "\n|%" + (CONSOLE_WIDTH - 1) + "s";
        System.out.println('┌' + addDashes() + '┐' +
                String.valueOf(String.format(midLines, "|")).repeat(CONSOLE_HEIGHT-5)  +
                '\n' + '└' + addDashes() + '┘' +
                "Adjust your console to create a box that runs along the top and each side\n" +
                "Then hit <Enter> to continue...");
        return 0;
    }

    public static int displayTitle() {
        System.out.println(titleSplash);
        return 0;
    }

    private static String addDashes() {
        return "-".repeat(CONSOLE_WIDTH-2);
    }

    // OBJECT METHODS
    public int displayIntro() throws InterruptedException {
        String title = introFromJSON;
        char[] charArray = title.toCharArray();
        for (char c : charArray) {
            System.out.print(c);
            if(c == '\n') {
                TimeUnit.MILLISECONDS.sleep(250);
            } else {
                TimeUnit.MILLISECONDS.sleep(20);
            }
        }
        return 0;
    }

    public int displayScene() {
        StringBuilder scene = new StringBuilder();
        String currentRoom = game.getCurrentRoom().getName();
        Integer health = game.getPlayer().health;
        Integer time = 1200 + (game.getPlayer().steps * 15);
        if(time % 100 >= 60) {
            time = (time - (time % 100)) + (time % 100 / 60 * 100) + (time % 100 % 60);
        }
        String status = String.format("Location: %s █ Health: %s █ TIME: %s",currentRoom,health,time > 999 ? time.toString() : "0" + time);
        String statusSpace = "%" + ((CONSOLE_WIDTH - 1 - status.length())/2 + status.length()) + "s";
        String endSpace = "%" + ((CONSOLE_WIDTH - status.length())/2) + "s"; // "%20s"

        scene.append("█".repeat(CONSOLE_WIDTH))
                .append("\n█")
                .append(String.format(statusSpace,status))
                .append(String.format(endSpace,"█"))
                .append("\n")
                .append("█".repeat(CONSOLE_WIDTH));
        System.out.println(scene);
        return 0;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
