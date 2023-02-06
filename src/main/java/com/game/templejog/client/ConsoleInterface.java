package com.game.templejog.client;

import com.game.templejog.*;

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

/*                      BUSINESS METHODS                        */
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
        Integer health = game.getPlayer().getHealth();

        // Top Bar Setup
        int hoursPlayed = game.getPlayer().getSteps() * 15;
        int hours = hoursPlayed / 60;
        int minutes = hoursPlayed % 60;
        int time = 1200 + (100 * hours) + minutes;

        String status = String.format("Location: %s █ Health: %s █ TIME: %s", currentRoom, health, time > 999 ? Integer.toString(time) : "0" + time);
        String statusSpace = "%" + ((CONSOLE_WIDTH - 1 - status.length()) / 2 + status.length()) + "s";
        String endSpace = "%" + ((CONSOLE_WIDTH - status.length()) / 2) + "s"; // "%20s"
        boolean hasEncounters = !getGame().getCurrentRoom().getEncounters_to().isEmpty();

        // Inventory Bar Setup
        String inventorySpace;
        StringBuilder inventory = new StringBuilder();
        inventory.append("█  Inventory: ");
        for (Item item : getGame().getPlayer().getInventory()) {
            System.out.println("CALL TO INVENTORY"+ item.getName());
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

        // com.game.templejog.Room Display Setup
        String lineOne = getGame().getCurrentRoom().getDescription();
        String roomDescription = formatDisplay(lineOne);
        roomDescription = roomDescription.concat("█" + " ".repeat(78) + "█" + "\n");

        // com.game.templejog.Encounter Setup
        StringBuilder encounterDescription = null;
        if (hasEncounters) {
            encounterDescription = new StringBuilder();
            for (String encounter : getGame().getCurrentRoom().getEncounters_to()) {
                encounterDescription.append(formatDisplay(getGame().getEncounters().get(encounter).getDescription()));
            }
            encounterDescription.append("█").append(" ".repeat(78)).append("█").append("\n");
        }

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
        scene.append("\n".repeat(22-displayLines));
        System.out.println(scene);
        return 0;
    }

    private String formatDisplay(String description) {
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

/*                      ACCESSORS                               */
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}