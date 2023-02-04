import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ConsoleInterface { // Previously TitleScreen

    /*                  CONSTANTS & FIELDS                          */
    static final String titleSplash = "\033[0;32m████████████████████████████████████████████████████████████████████████████████\n█░         █░        █░  █████    █░       ███░ ███████░        ████████████████\n█░         █░        █░   ████    █░        ██░ ███████░        ████████████████\n█░░░░  ░░░░█░ ░░░░░░░█░   ████    █░ ░░░░   ██░ ███████░ ░░░░░░░████████████████\n█████░ █████░ ████████░   ████    █░ ████░  ██░ ███████░ ███████████████████████\n█████░ █████░ ████████░   ███     █░ █████   █░ ███████░ ███████████████████████\n█████░ █████░       ██░    ██     █░ ████   ██░ ███████░       █████████████████\n█████░ █████░ ░░░░░░░█░ ░  █      █░      ░░██░ ███████░ ░░░░░░░████████████████\n█████░ █████░ ████████░ █░    ░   █░ ░░░░░████░ ███████░ ███████████████████████\n█████░ █████░ ████████░ █░    █░  █░ █████████░ ███████░ ███████████████████████\n█████░ █████░        █░ █░    █░  █░ █████████░       █░        ████████████████\n█████░░█████░░░░░░░░░█░░██░░░██░░░█░░█████████░░░░░░░░█░░░░░░░░░████████████████\n██████████████████████████████████████████░ ████████████████████████████████████\n██████████████████████████████████████████░ ███░       █████░      █████████████\n██████████████████████████████████████████░ ██░   ░░    ███░   ░░░  ████████████\n██████████████████████████████████████████░ ██░   ██░░  ███░  ███░░  ███████████\n██████████████████████████████████████████░ ██░  █████░  █░ ███████░ ███████████\n██████████████████████████████████████████░ ██░  █████░  █░ ████████████████████\n██████████████████████████████████████████░ ██░  █████░  █░ ███░   █████████████\n█████████████████████████████████████░ ███  ██░  █████   █░ ████░░   ███████████\n█████████████████████████████████████░  █   ██░   ██     █░  █████░  ███████████\n██████████████████████████████████████░    ███░░        ███░        ████████████\n███████████████████████████████████████░░░█████░░░░░░░░█████░░░░░░░█████████████\033[0m";
    static final Integer CONSOLE_HEIGHT = 25;
    static final Integer CONSOLE_WIDTH = 80;

    // Use the below string for release
    String introFromJSON = "The year is 20XX...\n\nA major government power has learned of an alien race that wants to invade Earth\n    and enslave the human race.\nThey have discovered a secret alien ship that has been here for centuries.\nIt has been disguised as a lost hidden temple the whole time!\nTheir plan is to nuke the temple from orbit. As it's\n            \"\033[1;37mThe only way to be sure.\033[0m\"\nFrom intel gained, your special ops team has learned that even if\n    the ship is destroyed, a signal will still be sent to the alien home-world!\nYour mission, that you already chose to accept, is to:\n\033[0;33m      -Infiltrate the temple and gain access to the communication device.\n      -Find a way to shut it down.\n      -Get back to the landing zone for extraction before the bomb drops!\n      -You have until sun-down at 18:00 local time.\033[0m\n\n\n\nPress any key to parachute into the LZ...";
//  String introFromJSON = "Press go";   // Use this when testing
    Game game;

    /*                      CONSTRUCTORS                            */
    public ConsoleInterface() {

    }


    /*                      STATIC METHODS                          */
    public static int displaySetup() {
        String midLines = "\n|%" + (CONSOLE_WIDTH - 1) + "s";
        System.out.println('┌' + addDashes() + '┐' +
                String.valueOf(String.format(midLines, "|")).repeat(CONSOLE_HEIGHT - 5) +
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
        return "-".repeat(CONSOLE_WIDTH - 2);
    }

    // OBJECT METHODS
    public int displayIntro() throws InterruptedException {
        String title = introFromJSON;
        char[] charArray = title.toCharArray();
        for (char c : charArray) {
            System.out.print(c);
            if (c == '\n') {
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

        // Top Bar Setup
        int time = 1200 + (game.getPlayer().steps * 15);
        if (time % 100 >= 60) {
            time = (time - (time % 100)) + (time % 100 / 60 * 100) + (time % 100 % 60);
        }
        String status = String.format("Location: %s █ Health: %s █ TIME: %s", currentRoom, health, time > 999 ? Integer.toString(time) : "0" + time);
        String statusSpace = "%" + ((CONSOLE_WIDTH - 1 - status.length()) / 2 + status.length()) + "s";
        String endSpace = "%" + ((CONSOLE_WIDTH - status.length()) / 2) + "s"; // "%20s"


        // Inventory Bar Setup
        String inventorySpace;
        StringBuilder inventory = new StringBuilder();
        inventory.append("█  Inventory: ");
        for (String item : getGame().getPlayer().listInventoryNames()) {
            if (inventory.length() + item.length() + 3 > 75) {
                inventorySpace = "%" + (CONSOLE_WIDTH - inventory.length()) + "s";
                inventory.append(String.format(inventorySpace, "█"));
                inventory.append("\n█").append(" ".repeat(14));
            }
            inventory.append(String.format("[%s] ", item));
        }

        if (inventory.length() < 80) {
            inventorySpace = "%" + (CONSOLE_WIDTH - inventory.length()) + "s";
            inventory.append(String.format(inventorySpace, "█")).append("\n");
            inventory.append("█").append(" ".repeat(78)).append("█");
        } else {
            inventorySpace = "%" + (80 - (inventory.length() - CONSOLE_WIDTH - 1)) + "s";
            inventory.append(String.format(inventorySpace, "█"));
        }

        // Room Display Setup
        String lineOne = getGame().getCurrentRoom().getDescription();
        String roomDescription = formatDisplay(lineOne);
        roomDescription = roomDescription.concat("█" + " ".repeat(78) + "█" + "\n");
//        List<String> lines = new ArrayList<>();
//        String roomSpaceBefore, roomSpaceAfter;
//        StringBuilder roomDescription = new StringBuilder();
//        roomDescription.append("█" + " ".repeat(78) + "█" + "\n");
//
//        if (lineOne.length() > 78) {
//            while(lineOne.length() > 78) {
//                int splitIndex = lineOne.indexOf(" ", 70);
//                lines.add(lineOne.substring(0,splitIndex));
//                lineOne = lineOne.substring(splitIndex+1);
//            }
//            lines.add(lineOne);
//        } else {
//            lines.add(lineOne);
//        }
//        for(String line : lines) {
//            roomSpaceBefore = "%" + ((CONSOLE_WIDTH - 1 - line.length()) / 2 + line.length()) + "s";
//            roomSpaceAfter = "%" + ((CONSOLE_WIDTH - line.length()) / 2) + "s";
//            roomDescription.append("█" + String.format(roomSpaceBefore,line) + (String.format(roomSpaceAfter,"█") + "\n"));
//        }
//        roomDescription.append("█" + " ".repeat(78) + "█" + "\n");

        // Encounter Setup
        StringBuilder encounterDescription = new StringBuilder();
        for(String encounter : getGame().getCurrentRoom().getEncounters_to()) {
            encounterDescription.append(formatDisplay(getGame().encounters.get(encounter).getDescription()));
        }
        encounterDescription.append("█").append(" ".repeat(78)).append("█").append("\n");
        // Scene Builder
        scene.append("█".repeat(CONSOLE_WIDTH))
                 .append("\n█")
                 .append(String.format(statusSpace, status))
                 .append(String.format(endSpace, "█"))
                 .append("\n")
                 .append("█".repeat(CONSOLE_WIDTH))
                 .append("\n")
                 .append(inventory)
                 .append("\n")
                 .append("█".repeat(CONSOLE_WIDTH))
                 .append("\n")
                 .append(roomDescription)
                 .append("█".repeat(CONSOLE_WIDTH))
                 .append("\n")
                 .append(encounterDescription)
                 .append("█".repeat(CONSOLE_WIDTH))
                 .append("\n");
        System.out.println(scene);
        return 0;
    }

    private String formatDisplay(String description) {
        List<String> lines = new ArrayList<>();
        String roomSpaceBefore, roomSpaceAfter;
        StringBuilder roomDescription = new StringBuilder();

        roomDescription.append("█").append(" ".repeat(78)).append("█").append("\n");
        if (description.length() > 78) {
            while(description.length() > 78) {
                int splitIndex = description.indexOf(" ", 70);
                lines.add(description.substring(0,splitIndex));
                description = description.substring(splitIndex+1);
            }
            lines.add(description);
        } else {
            lines.add(description);
        }
        for(String line : lines) {
            roomSpaceBefore = "%" + ((CONSOLE_WIDTH - 1 - line.length()) / 2 + line.length()) + "s";
            roomSpaceAfter = "%" + ((CONSOLE_WIDTH - line.length()) / 2) + "s";
            roomDescription.append("█")
                    .append(String.format(roomSpaceBefore, line))
                    .append(String.format(roomSpaceAfter, "█"))
                    .append("\n");
        }
        return roomDescription.toString();
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}