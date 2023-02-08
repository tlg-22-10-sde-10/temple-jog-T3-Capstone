package com.game.templejog.client;

import com.game.templejog.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.fusesource.jansi.Ansi.ansi;

public class ConsoleInterface { // Previously TitleScreen

/*                  CONSTANTS & FIELDS                          */
    static final String titleSplash = "@|green ████████████████████████████████████████████████████████████████████████████████\n█░         █░        █░  █████    █░       ███░ ███████░        ████████████████\n█░         █░        █░   ████    █░        ██░ ███████░        ████████████████\n█░░░░  ░░░░█░ ░░░░░░░█░   ████    █░ ░░░░   ██░ ███████░ ░░░░░░░████████████████\n█████░ █████░ ████████░   ████    █░ ████░  ██░ ███████░ ███████████████████████\n█████░ █████░ ████████░   ███     █░ █████   █░ ███████░ ███████████████████████\n█████░ █████░       ██░    ██     █░ ████   ██░ ███████░       █████████████████\n█████░ █████░ ░░░░░░░█░ ░  █      █░      ░░██░ ███████░ ░░░░░░░████████████████\n█████░ █████░ ████████░ █░    ░   █░ ░░░░░████░ ███████░ ███████████████████████\n█████░ █████░ ████████░ █░    █░  █░ █████████░ ███████░ ███████████████████████\n█████░ █████░        █░ █░    █░  █░ █████████░       █░        ████████████████\n█████░░█████░░░░░░░░░█░░██░░░██░░░█░░█████████░░░░░░░░█░░░░░░░░░████████████████\n██████████████████████████████████████████░ ████████████████████████████████████\n██████████████████████████████████████████░ ███░       █████░      █████████████\n██████████████████████████████████████████░ ██░   ░░    ███░   ░░░  ████████████\n██████████████████████████████████████████░ ██░   ██░░  ███░  ███░░  ███████████\n██████████████████████████████████████████░ ██░  █████░  █░ ███████░ ███████████\n██████████████████████████████████████████░ ██░  █████░  █░ ████████████████████\n██████████████████████████████████████████░ ██░  █████░  █░ ███░   █████████████\n█████████████████████████████████████░ ███  ██░  █████   █░ ████░░   ███████████\n█████████████████████████████████████░  █   ██░   ██     █░  █████░  ███████████\n██████████████████████████████████████░    ███░░        ███░        ████████████\n███████████████████████████████████████░░░█████░░░░░░░░█████░░░░░░░█████████████|@";
    static final Integer CONSOLE_HEIGHT = 25;
    static final Integer CONSOLE_WIDTH = 80;

    // Use the below string for release
    String introFromJSON = "The year is 20XX...\n\nA major government power has learned of an alien race that wants to invade Earth\n    and enslave the human race.\nThey have discovered a secret alien ship that has been here for centuries.\nIt has been disguised as a lost hidden temple the whole time!\nTheir plan is to nuke the temple from orbit. As it's\n            \"@|cyan The only way to be sure.|@\"\nFrom intel gained, your special ops team has learned that even if\n    the ship is destroyed, a signal will still be sent to the alien home-world!\nYour mission, that you already chose to accept, is to:\n@|bold,yellow       -Infiltrate the temple and gain access to the communication device.\n      -Find a way to shut it down.\n      -Get back to the landing zone for extraction before the bomb drops!\n      -You have until sun-down at 18:00 local time.|@ \n\n\n\nPress any key to parachute into the LZ...";
//    String introFromJSON = "Press go";   // Use this when testing
    String gameOver = "████████████████████████████████████████████████████████████████████████████████\n" +
            "█                                                                              █\n" +
            "█     ████     ████ ██     ██ ███████    ███████  ██    ██ ███████ ██████      █\n" +
            "█    ██████   ██ ██ ███   ███ ███████   █████████ ██    ██ ███████ ████████    █\n" +
            "█   ██       ██  ██ ████ ████ ██        ███   ███ ██    ██ ██      ██    ██    █\n" +
            "█   ██       ██  ██ █████████ ██        ██     ██ ██    ██ ██      ██    ██    █\n" +
            "█   ██       ██  ██ ██ ███ ██ █████     ██     ██ ██    ██ █████   ████████    █\n" +
            "█   ██  ████ ██████ ██  █  ██ █████     ██     ██ ██    ██ █████   ███████     █\n" +
            "█   ██  ████ ██████ ██     ██ ██        ███   ███ ███  ███ ██      ██  ██      █\n" +
            "█   ██   ██  ██  ██ ██     ██ ███████   █████████   ████   ███████ ██   ██     █\n" +
            "█    █████   ██  ██ ██     ██ ███████    ███████     ██    ███████ ██    ██    █\n" +
            "█                                                                              █\n" +
            "████████████████████████████████████████████████████████████████████████████████";
    Game game;

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
        System.out.println(ansi().render(titleSplash));
        return 0;
    }

    private static String addDashes() {
        return "-".repeat(CONSOLE_WIDTH - 2);
    }

/*                      BUSINESS METHODS                        */
    public int displayIntro() throws InterruptedException {
        String title = ansi().render(introFromJSON).toString();
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
        Integer health = game.getPlayer().getHealth();

        // Top Bar Setup
        int hoursPlayed = game.getPlayer().getSteps() * 15;
        int hours = hoursPlayed / 60;
        int minutes = hoursPlayed % 60;
        int time = 1200 + (100 * hours) + minutes;
        String status = String.format(ansi().render("Location:@|cyan  %s|@ █ Health:@|cyan  %s|@ █ TIME:@|cyan  %s|@").toString(), currentRoom, health, time > 999 ? Integer.toString(time) : "0" + time);
        String statusSpace = "%" + ((CONSOLE_WIDTH - 1 - (status.length()-24)) / 2 + status.length()) + "s";
        String endSpace = "%" + ((CONSOLE_WIDTH - (status.length()-24)) / 2) + "s"; // "%20s"
        boolean hasEncounters = !getGame().getCurrentRoom().getEncounters_to().isEmpty();
        // Inventory Bar Setup
        String inventorySpace;
        StringBuilder inventory = new StringBuilder();
        inventory.append("█  Inventory: ");
        for (Item item : getGame().getPlayer().getInventory()) {
            if (inventory.length() + item.getName().length() + 3 > 75) {
                inventorySpace = "%" + (CONSOLE_WIDTH - inventory.length()) + "s";
                inventory.append(String.format(inventorySpace, "█"));
                inventory.append("\n█").append(" ".repeat(14));
            }
            inventory.append(String.format("[%s] ", item.getName()));
        }

        if (inventory.length() < 80) {
            inventorySpace = "%" + (CONSOLE_WIDTH - inventory.length()) + "s";
            inventory.append(String.format(inventorySpace, "█")).append("\n");
            inventory.append("█").append(" ".repeat(78)).append("█");
        } else {
            inventorySpace = "%" + (80 - (inventory.length() - CONSOLE_WIDTH - 1)) + "s";
            inventory.append(String.format(inventorySpace, "█"));
        }

        // Encounter Setup
        StringBuilder encounterDescription = null;
        if (hasEncounters) {
            encounterDescription = new StringBuilder();
            for (String encounter : getGame().getCurrentRoom().getEncounters_to()) {
                encounterDescription.append(formatDisplay(getGame().getEncounters().get(encounter).getDescription(),"encounter"));
            }
            encounterDescription.append("█").append(" ".repeat(78)).append("█").append("\n");
        }

        // Room Display Setup
        String lineOne = getGame().getCurrentRoom().getDescription();
        String roomDescription = formatDisplay(lineOne,"room");
        roomDescription = roomDescription.concat("█" + " ".repeat(78) + "█" + "\n");



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
                .append("\n");
        if (hasEncounters) {
            scene.append(encounterDescription)
                    .append("█".repeat(CONSOLE_WIDTH))
                    .append("\n");
        }
        scene.append(roomDescription)
                .append("█".repeat(CONSOLE_WIDTH))
                .append("\n");

        int displayLines = scene.length() / 80;
        // DONE: illegalArgumentException: count is negative: -8 at line 131
        scene.append("\n".repeat(Math.max(22 - displayLines, 1)));
        System.out.println(scene);
        return 0;
    }

    private String formatDisplay(String description, String type) {
        List<String> lines = new ArrayList<>();
        String roomSpaceBefore, roomSpaceAfter;
        StringBuilder sceneDescription = new StringBuilder();

        sceneDescription.append("█").append(" ".repeat(78)).append("█").append("\n");
        if (description.length() > 78) {
            while (description.length() > 78) {
                int splitIndex = description.indexOf(" ", 70);
                lines.add(description.substring(0, splitIndex));
                description = description.substring(splitIndex + 1);
            }
            lines.add(description);
        } else {
            lines.add(description);
        }
        // Add Items
        List<String> itemList = getGame().getCurrentRoom().getItems();
        if (!itemList.isEmpty() && type.equals("room")) {
            lines.add("");
            StringBuilder items = new StringBuilder();
            items.append(String.format("You see a %s",itemList.get(0)));
            int totalItems = itemList.size();
            for(int i = 1; i < totalItems; i++) {
                if(i == totalItems-1) {
                    items.append(String.format(" and a %s",itemList.get(i)));
                } else {
                    items.append(String.format(", %s",itemList.get(i)));
                }
            }
            items.append(".");
            lines.add(items.toString());
        }
        for (String line : lines) {
            roomSpaceBefore = "%" + ((CONSOLE_WIDTH - 1 - line.length()) / 2 + line.length()) + "s";
            roomSpaceAfter = "%" + ((CONSOLE_WIDTH - line.length()) / 2) + "s";
            sceneDescription.append("█")
                    .append(String.format(roomSpaceBefore, line))
                    .append(String.format(roomSpaceAfter, "█"))
                    .append("\n");
        }
        return sceneDescription.toString();
    }

    public int displayResult(String processChoice, int lineNumber) throws InterruptedException {
        // Break up into lines
        lineNumber = lineNumber > 0 ? 12 - lineNumber : 12;
        List<String> lines = new ArrayList<>();
        if (processChoice.length() > 78) {
            while (processChoice.length() > 78) {
                int splitIndex = processChoice.indexOf(" ", 70);
                lines.add(processChoice.substring(0, splitIndex));
                processChoice = processChoice.substring(splitIndex + 1);
            }
            lines.add(processChoice);
        } else {
            lines.add(processChoice);
        }

        // Format string to center on page
        StringBuilder display = new StringBuilder();
        for (String line : lines) {
            String spaceBefore = "%" + ((CONSOLE_WIDTH - 1 - line.length()) / 2 + line.length()) + "s";
            String spaceAfter = "%" + ((CONSOLE_WIDTH - line.length()) / 2) + "s";
            display.append(" ")
                    .append(String.format(spaceBefore, line))
                    .append(String.format(spaceAfter, " "))
                    .append("\n");
        }

    // Print gap above
        int displayLines = display.length() / 80;
        System.out.print("\n".repeat(lineNumber-displayLines/2));
    // Iterate through string like the intro

        char[] charArray = display.toString().toCharArray();
        int pause = 0;
        for (char c : charArray) {
            System.out.print(c);
            if (c == ' ') {
                TimeUnit.MILLISECONDS.sleep(0);
            } else {
                TimeUnit.MILLISECONDS.sleep(20);
                pause++;
            }
        }
    // Print gap below
        System.out.print("\n".repeat(lineNumber-1-displayLines/2));
    // Pause
        TimeUnit.MILLISECONDS.sleep((pause * 20L) < 1500 ? 1500 : pause * 20L);
        return 0;
    }

    public void displayEnding() throws InterruptedException {
        if(game.getCommunicatorOff()) {
            if(getGame().getPlayer().getSteps() >= 24 || getGame().getPlayer().getHealth() <= 0) {
                System.out.println(ansi().fgBrightYellow().render(gameOver).fgDefault());
                displayResult("YOU (sort of) WIN: Earth commends you and is forever in your debt! You managed to thwart the alien threat but unfortunately you did not get out in time to escape the nuke! You will be remembered...",7);
            } else {
                System.out.println(ansi().fgBrightGreen().render(gameOver).fgDefault());
                displayResult("YOU WIN: You managed to infiltrate the alien temple, disable the device and get out before the bomb dropped. As you look back from the helicopter, you see the nuke go off on the horizon. Earth has been spared!", 7);
            }
        } else {
            if(getGame().getPlayer().getSteps() >= 24) {
                System.out.println(ansi().fgBrightRed().render(gameOver).fgDefault());
                displayResult("GAME OVER: You were killed by the atomic bomb blast. The aliens were still able to send a signal home before the blast went off! Earth is DOOMED!", 7);
            } else {
                System.out.println(ansi().fgBrightRed().render(gameOver).fgDefault());
                displayResult("GAME OVER: You died trying to save the world, but failed. The aliens were still able to send a signal home before the atomic bomb dropped! Earth is DOOMED!", 7);
            }
        }
    }
    public static void clearScreen () {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

/*                      ACCESSORS                               */
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }


}