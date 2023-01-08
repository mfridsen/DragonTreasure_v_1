package dev.tias.dragontreasure;

import dev.tias.dragontreasure.dungeon.Dungeon;
import dev.tias.dragontreasure.mobs.humanoids.Player;
import dev.tias.dragontreasure.mobs.monsters.Dragon;

//TODO Create one of every type of equipment
//TODO rings and necklaces
//TODO implement two-handed weapons
//TODO implement shields
//TODO finish equipping to slots

/**
 * A basic text-based adventure game.
 * The player is asked to enter their name, and can then navigate through the Dungeon, slaying monsters and
 * picking up items.
 * using a keyboard.
 * @author  Mattias Fridsén
 * @version 1.0
 * @since   2022-11-28
 */
public class DragonTreasure {
    private static final String welcomeMessage =
            "Dragon Treasure!\n" +
            "Welcome to the darkest, most evil dungeon in the world! \n" +
            "You won't survive, and if you do, you won't stay sane...\n" +
            "You are standing outside a cave entrance facing east. Sulphuric smoke emanates from the entrance.\n" +
            "Ask for [H]elp if you need it, once you have entered...\n" +
            "Type [E] and press [Enter] to enter the cave.";

    private static Dungeon dungeon;
    private static Player player;
    private static boolean running;

    public static void main(String[] args) {
        setupGame();
        //And this is the game loop
        while (running)
            playGame();
        endGame(0);
    }

    /**
     * Initializes the components necessary for the game. Handles the intro part.
     */
    public static void setupGame() {
        dungeon = new Dungeon();
        dungeon.setupDungeon();
        player = new Player(InputHandler.getPlayerName());
        System.out.println("Welcome " + player.getName() + "!");
        running = true;

        //Introductory part, before the game loop begins
        System.out.println(welcomeMessage);
        String input = InputHandler.nextLine();
        while (!input.equals("e")) {
            System.out.println("Invalid input. Type [E] and press [Enter] to enter the cave.");
            input = InputHandler.nextLine();
        }
        System.out.println("When you enter the cave the entrance collapses behind you.");
        player.setCurrentRoom(dungeon.getRooms()[0]);
    }

    /**
     * Is the game loop. Prints the welcome message.
     */
    public static void playGame() {
        //Prints the image of the dragon when entering its room
        if (player.getCurrentRoom().getMob() instanceof Dragon && player.hasMoved())
            System.out.println(Dragon.DRAGON_IMAGE);

        //Makes sure that the monsters don't attack immediately upon the player entering a room
        player.setMoved(false);
        player.getCurrentRoom().doNarrative();
        InputHandler.handleInput(player);

        //Otherwise the monster will attack the player as soon as he enters a room
        if (player.hasMoved())
            return;

        if (player.getCurrentRoom().getMob() != null)
            player.getCurrentRoom().getMob().attack(player);

        if (player.isDead()) {
            System.out.println("Your body has been broken, torn to pieces and left to rot and be eaten by worms...");
        }
    }

    /**
     * Exits the game. Prints kills. If status is anything other than 0, prints debug info for extra problem-finding.
     */
    public static void endGame(int status) {
        running = false;
        player.printKills();
        if (status > 0) {
            debug(); //Something went very, game-breakingly wrong
        }
        System.exit(status);
    }

    /**
     * Prints debug info for the game.
     */
    public static void debug() {
        dungeon.printRoomsAndDoors();
        //TODO print dungeon monster stats
    }
}
