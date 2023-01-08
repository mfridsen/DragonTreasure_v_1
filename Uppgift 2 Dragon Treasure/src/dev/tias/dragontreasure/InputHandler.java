package dev.tias.dragontreasure;

import dev.tias.dragontreasure.dungeon.Door;
import dev.tias.dragontreasure.dungeon.containers.Container;
import dev.tias.dragontreasure.items.Item;
import dev.tias.dragontreasure.items.Treasure;
import dev.tias.dragontreasure.items.consumables.Consumable;
import dev.tias.dragontreasure.items.equipables.Equipable;
import dev.tias.dragontreasure.items.usables.Key;
import dev.tias.dragontreasure.items.usables.Usable;
import dev.tias.dragontreasure.mobs.humanoids.Player;

import java.util.Scanner;

/**
 * Handles input from the player. Used to be non-static but realized it might as well be static.
 * Could have been an interface except for the fact that it contains a Scanner object and the String objects.
 */
public class InputHandler {
    private static final Scanner scanner = new Scanner(System.in);

    //If you want to add more options, you only have to add them here
    private static final String[] helpOptions       = {"h", "H", "help", "Help", "HELP"};

    private static final String[] northOptions      = {"n", "N", "north", "North", "NORTH"};
    private static final String[] eastOptions       = {"e", "E", "east", "East", "EAST"};
    private static final String[] southOptions      = {"s", "S", "south", "South", "SOUTH"};
    private static final String[] westOptions       = {"w", "W", "west", "West", "WEST"};

    //Could potentially be useful, leaving it here
    /*
    private static final ArrayList<String> movementOptions;
    static {
        movementOptions = new ArrayList<>();
        Collections.addAll(movementOptions, northOptions);
        Collections.addAll(movementOptions, eastOptions);
        Collections.addAll(movementOptions, southOptions);
        Collections.addAll(movementOptions, westOptions);
    }
    */

    private static final String[] pickUpOptions     = {"p", "P", "pickup", "Pickup", "PICKUP",
                                                        "pick up", "Pick up", "PICK UP"};
    private static final String[] inventoryOptions  = {"i", "I", "inventory", "Inventory", "INVENTORY"};
    private static final String[] unEquipOptions    = {"u", "U", "unequip", "Unequip", "UNEQUIP"};
    private static final String[] containerOptions  = {"o", "O", "open", "Open", "OPEN"};
    private static final String[] discardOptions    = {"d", "D", "discard", "Discard", "DISCARD"};

    private static final String[] attackOptions     = {"a", "A", "attack", "Attack", "ATTACK"};
    private static final String[] exitQuitOptions   = {"x", "X", "exit", "Exit", "EXIT",
                                                        "q", "Q", "quit", "Quit", "QUIT"};
    private static final String[] debugOptions      = {"ö", "Ö", "debug", "Debug", "DEBUG"};

    /**
     * A little bit redundant but whatever, sue me. There could be special cases in the game where we want to
     * request one (or a few) specific inputs, like at the start of the game.
     */
    public static String nextLine() {
        return scanner.nextLine();
    }

    /**
     * Technically this method and the previous are the same, just with a print added. Not completely redundant,
     * since we really would like to keep this if we want to have capability for multiple players. Future proofing is
     * important.
     */
    public static String getPlayerName() {
        System.out.println("Enter player name: ");
        return scanner.nextLine();
    }

    /**
     * Handles player input for the majority of game functionalities.
     * h = help
     * n = north
     * e = east
     * s = south
     * w = west
     * i = inventory
     * u = unequip
     * p = pick up
     * o = open container
     * d = discard
     * a = attack
     * q = quit, same as
     * x = exit
     * ö = debug, non-swedes git rekt
     */
    public static void handleInput(Player player) {
        String input = scanner.nextLine();

        for (String s : helpOptions)
            if (input.equals(s)) {
                handleHelp();
                return;
            }

        //Movement commands
        for (String s : northOptions)
            if (input.equals(s)) {
                System.out.println("You chose to go North.");
                handleMovement("n", player);
                return;
            }
        for (String s : eastOptions)
            if (input.equals(s)) {
                System.out.println("You chose to go East.");
                handleMovement("e", player);
                return;
            }
        for (String s : southOptions)
            if (input.equals(s)) {
                System.out.println("You chose to go South.");
                handleMovement("s", player);
                return;
            }
        for (String s : westOptions)
            if (input.equals(s)) {
                System.out.println("You chose to go West.");
                handleMovement("w", player);
                return;
            }

        //Inventory and Item-related commands
        for (String s : inventoryOptions)
            if (input.equals(s)) {
                System.out.println("You chose to check your inventory.");
                handleInventory(player);
                return;
            }
        for (String s : pickUpOptions)
            if (input.equals(s)) {
                handlePickUp(player);
                return;
            }
        for (String s : unEquipOptions)
            if (input.equals(s)) {
                handleUnequipping(player);
                return;
            }
        for (String s : containerOptions)
            if (input.equals(s)) {
                handleContainer(player);
                return;
            }
        for (String s : discardOptions)
            if (input.equals(s)) {
                handleDiscard(player);
                return;
            }

        //Attack command
        for (String s : attackOptions)
            if (input.equals(s)) {
                handleAttack(player);
                return;
            }

        //x and q are both pretty standard controls to quit or exit a program
        for (String s : exitQuitOptions)
            if (input.equals(s)) {
                handleQuit();
                return;
            }

        //Debug command
        for (String s : debugOptions)
            if (input.equals(s)) {
                handleDebug(player);
                return;
            }

        System.out.println("Invalid input.");
    }

    //The following methods are private since they are called by handleInput. The game should only ever need to call
    //handleInput in order to, well, handle input

    /**
     * Prints the help menu, displaying all the controls for the game.
     */
    private static void handleHelp() {
        //Help
        System.out.println("********************************* HELP MENU *********************************************");
        System.out.println("This game is played by entering commands via keyboard and pressing [ENTER].\n" +
                "Some of the commands are contextual, meaning they only work in special circumstances.\n" +
                "What follows is a list of all the commands, and information about their contexts.\n");

        System.out.println("Commands that access [H]elp information:");
        for (String s : helpOptions)
            System.out.print( s + " ");
        System.out.println("\n");

        //Movements
        System.out.println("The Player moves through the Dungeon by entering a command representing the direction\n" +
                "the Player wishes to move, and pressing [ENTER].");

        System.out.println("\nThe following are the movement commands that work for the four directions:");
        System.out.println("To move North:");
        for (String s : northOptions)
            System.out.print(s + " ");
        System.out.println("\nTo move East:");
        for (String s : eastOptions)
            System.out.print(s + " ");
        System.out.println("\nTo move South:");
        for (String s : southOptions)
            System.out.print(s + " ");
        System.out.println("\nTo move West:");
        for (String s : westOptions)
            System.out.print(s + " ");

        System.out.println("\n\nTo Pick up the Item in a room:");
        for (String s : pickUpOptions)
            System.out.print(s + " ");

        System.out.println("\n\nTo access your Inventory:");
        for (String s : inventoryOptions)
            System.out.print(s + " ");

        System.out.println("\n\nTo Unequip Items:");
        for (String s : unEquipOptions)
            System.out.print(s + " ");

        System.out.println("\n\nTo Open a Container");
        for (String s : containerOptions)
            System.out.print(s + " ");

        System.out.println("\n\nTo Discard an Item:");
        for (String s : discardOptions)
            System.out.print(s + " ");

        System.out.println("\n\nTo Attack an enemy:");
        for (String s : attackOptions)
            System.out.print(s + " ");

        System.out.println("\n\nTo Exit/Quit game or a menu:");
        for (String s : exitQuitOptions)
            System.out.print(s + " ");

        System.out.println("\n\nTo view Debug info:");
        for (String s : debugOptions)
            System.out.print(s + " ");

        System.out.println("******************************* END HELP MENU ******************************************\n");
    }

    /**
     * Handles player movement. Checks if a door is locked, otherwise updates the current room according to input.
     */
    private static void handleMovement(String input, Player player) {

        //Loop through all the doors in the room
        for (int i = 0; i < player.getCurrentRoom().getDoors().length; i++) {
            //Check if Player input corresponds to the position of a door in the room
            if (input.equals(String.valueOf(player.getCurrentRoom().getDoors()[i].getPosition()))) {
                //If a door is locked
                if (player.getCurrentRoom().getDoors()[i].isLocked()) {
                    //Check if the player has key
                    for (Item item: player.getInventory()) {
                        //If player has the key
                        if (item instanceof Key) {
                            handleUnlocking(player, player.getCurrentRoom().getDoors()[i]);
                            return;
                        }
                    }   //If player is a lazy bum and didn't pick up the key
                    System.out.println("You do not have a key that fits.\n" +
                            "You peek through the keyhole and see a treasure chest full of gold.");
                    return;
                //If a door is the exit
                } else if (player.getCurrentRoom().getDoors()[i].isExit()) {
                    handleExit(player);
                    return;
                //Move to the specified Room
                } else {
                    player.setCurrentRoom(player.getCurrentRoom().getDoors()[i].getRoomConnecting());
                    player.setMoved(true);
                    return;
                }
            }
        }
        //There was no door in that direction
        System.out.println("There is no door in that direction.");
    }

    /**
     * Handles unlocking of doors, to clean up the previous method.
     */
    private static void handleUnlocking(Player player, Door door) {
        for (Item item : player.getInventory()) {
            if (item instanceof Key) {
                if (((Key) item).getKeyNum() == door.getLockNum()) {
                    System.out.println("You have a key for this door. Open? [Y]/[N]");
                    if ("y".equals(scanner.nextLine())) {
                        System.out.println("You unlocked the door.");
                        door.unlock((Key) item);
                        player.setCurrentRoom(door.getRoomConnecting());
                        player.setMoved(true);
                        return;
                    }
                    System.out.println("You did not unlock the door.");
                    return;
                }
            }
        }
        System.out.println("You have no key for this door.");
    }

    /**
     * Handles inventory management for the Player. Prints the full list of the Players inventory and interprets Player
     * input to see if an Item was selected for use.
     */
    private static void handleInventory(Player player) {
        player.printInventory();
        if (player.getInventory().size() <= 0) { //Inventory is empty, the rest is unnecessary
            return;
        }

        //input has to be declared outside for-loop or we can only equip 1st item reliably
        System.out.println("[NUM], [D]iscard, e[X]it/[Q]uit menu:");
        String input = scanner.nextLine();

        //Exit menu
        if (input.equals("x") || input.equals("q")) { //Player input x or q
            return;
        }

        //Discard menu
        if ("d".equals(input)) {
            handleDiscard(player);
            return;
        }

        //First we check to see if the player input corresponds to the index (-1 because we count from 1) of any
        //inventory item
        for (Item item : player.getInventory()) {
            //Did the player give a number corresponding to an item?
            //Convert the index to a string and compare to input. + 1 because inventory is displayed from 1...*, not 0
            if (input.equals(Integer.toString(player.getInventory().indexOf(item) + 1))) {
                //If the item is a Usable, use
                if (item instanceof Usable) {
                    ((Usable) item).use(player);
                    return;
                }
                //If the Item is a Consumable, consume
                else if (item instanceof Consumable) {
                    ((Consumable) item).consume(player);
                    return;
                }
                //If the Item is an unequipped Equipable, equip
                else if (item instanceof Equipable) {
                    handleEquipping(player, (Equipable) item);
                    return;
                }
                //If the Item is the Treasure, print the image of the Treasure
                else if (item instanceof Treasure) {
                    System.out.println(Treasure.TREASURE_IMAGE);
                    return;
                }
            }
        }
        //Player apparently did not give a number corresponding to an item

    }

    /**
     * Correct way of handling equipping is calling player.equipItem(equipable). equipItem calls the equip-method
     * in the equipable.
     */
    private static void handleEquipping(Player player, Equipable equipable) {
        if (equipable.isEquipped()) {
            player.unEquipItem(equipable);
            return;
        }
        player.equipItem(equipable);
    }

    /**
     * Handles unequipping items for the player.
     */
    private static void handleUnequipping(Player player) {
        player.printEquippedItems();
        if (player.getEquippedItems().size() == 0) //No equipables on player
            return;

        //input has to be handled outside for-loop or we can only unequip 1st item reliably
        System.out.println("[NUM], unequip [A]ll, e[X]it/[Q]uit menu:");
        String input = scanner.nextLine();

        //Exit menu
        if (input.equals("x") || input.equals("q")) { //Player input x or q
            return;
        }

        //Unequip all
        if (input.equals("a")) {
            int prevSize = player.getEquippedItems().size();
            while (player.getEquippedItems().size() > 0) {
                handleEquipping(player, player.getEquippedItems().get(0));
                //If player is unable to unequip, due to full inventory, game will loop eternally
                if (prevSize == player.getEquippedItems().size()) {
                    return;
                }
                prevSize = player.getEquippedItems().size();
            }
            return;
        }

        //Loop through to check which Item was selected
        for (int i = 1; i <= player.getEquippedItems().size(); i++) {
            if (input.equals(Integer.toString(i))) { //Player made a valid choice of item
                handleEquipping(player, player.getEquippedItems().get(i - 1));
                return;
            }
        } //Player input something invalid
        System.out.println("Invalid input for unequipping!");
    }

    /**
     * Handles the discarding of items.
     */
    private static void handleDiscard(Player player) {
        if (player.getInventory().size() <= 0) {
            System.out.println("Nothing in inventory to discard!");
            return;
        }

        System.out.println("Which item would you like to discard?");
        player.printInventory();
        System.out.println("[NUM], Discard [A]ll, e[X]it/[Q]uit menu:");

        String input = scanner.nextLine();

        switch (input) {
            case "x":
            case "q":
                break;

            case "a":
                while (player.getInventory().size() > 0)
                    player.discardItem(player.getInventoryItem(0));
                return;
        }

        if (handleDiscardItem(player, input))
            return;

        System.out.println("Invalid input for discard!");
    }

    /**
     * Compares input to the indices of all Items in the player's inventory. If input corresponds to an index, discards
     * that item.
     */
    private static boolean handleDiscardItem(Player player, String input) {
        for (Item item : player.getInventory()) {
            if (input.equals(Integer.toString(player.getInventory().indexOf(item) + 1))) {
                player.discardItem(item);
                return true;
            }
        }
        return false;
    }

    /**
     * If there is an Item in the current Room, adds the Item to inventory and removes it from currentRoom.
     */
    private static void handlePickUp(Player player) {
        //Is there an item in the room?
        if (player.getCurrentRoom().getItem() == null) {
            System.out.println("Nothing to [P]ick up here.");
            return;
        }
        //Can you pick it up? If yes, remove from room
        if(player.pickUpItem(player.getCurrentRoom().getItem())) {
            player.getCurrentRoom().setItem(null);
        }
    }

    /**
     * Handles opening containers and retrieving and placing items.
     */
    private static void handleContainer(Player player) {
        //Makes the code easier to read
        Container container;
        String input;

        //No Container in Room
        if ((container = player.getCurrentRoom().getContainer()) == null) {
            System.out.println("No Container to open in this room!");
            return;
        }

        container.open();

        //input has to be handled outside for-loop or we can only retrieve 1st item reliably
        System.out.println("[NUM], Retrieve [A]ll, [P]ut item, e[X]it/[Q]uit menu:");
        input = scanner.nextLine();

        //Exit menu
        if (input.equals("x") || input.equals("q")) //Player input x or q
            return;

        //Handle placing items into container
        if (input.equals("p")) {
            handlePlacingItemsIntoContainer(player, container);
            return;
        }

        //If container is empty, the following are invalid inputs
        if (!(container.numberOfItems() > 0)) {
            System.out.println("Can't retrieve from empty chest!");
            return;
        }

        //Retrieve all
        if (input.equals("a")) {
            handleRetrieveAllFromContainer(player, container);
            return;
        }

        //Loop through to check which Item was selected
        if(handleRetrieveItemFromContainer(player, container, input))
            return;

        //Player input something invalid
        System.out.println("Invalid input for Container!");
    }

    /**
     * Retrieves first item in container until container is empty or inventory is full.
     */
    private static void handleRetrieveAllFromContainer(Player player, Container container) {
        while (container.numberOfItems() > 0) {
            if (!player.retrieveItemFromContainer(container.getItem(0), container))
                return;
        }
    }

    /**
     * Loops through all Items in the Container in the Player's currentRoom and checks if the index of each item
     * corresponds to input. Corrected to + 1 since it is more natural for a user to enter "1" to retrieve the
     * first Item, than "0".
     * If input corresponds to index, will attempt to retrieve item and returns true. If input does not correspond
     * to an item, returns false.
     */
    private static boolean handleRetrieveItemFromContainer(Player player, Container container, String input) {
        for (int i = 0; i < container.numberOfItems(); i++) {
            if (input.equals(Integer.toString(i + 1))) { //Player made a valid choice of item
                player.retrieveItemFromContainer(container.getItem(i), container);
                return true;
            }
        } //Player input something invalid
        return false;
    }

    /**
     * Handles the choice of placing items into a container.
     */
    private static void handlePlacingItemsIntoContainer(Player player, Container container) {
        System.out.println("Choose which Item to place into the " + container.getNoun() + ":");
        player.printInventory();

        System.out.println("[NUM], [A]ll, e[X]it/[Q]uit menu:");
        String input = scanner.nextLine();

        //Exit menu
        if (input.equals("x") || input.equals("q")) { //Player input x or q
            return;
        }

        //Put all items
        if (input.equals("a")) {
            handlePlacingAllItemsIntoContainer(player, container);
            return;
        }

        //Put specific item
        if (handlePlacingItemIntoContainer(player, container, input))
            return;

        //Player input something invalid
        System.out.println("Invalid input for placing into container!");
    }

    /**
     * Places all Items in Player's inventory into container, if possible.
     */
    private static void handlePlacingAllItemsIntoContainer(Player player, Container container) {
        while (player.getInventory().size() > 0) {
            //Player couldn't place the item, so let's not be stuck in a loop for eternity
            if (!player.placeItemIntoContainer(player.getInventoryItem(0), container)) {
                return;
            }
        }
    }

    /**
     * Loops through all Items in the Player's inventory and checks if the index of each item
     * corresponds to input. Corrected to + 1 since it is more natural for a user to enter "1" to retrieve the
     * first Item, than "0".
     * If input corresponds to index, places the Item into the Container and if successful, returns true.
     * Else returns false.
     */
    private static boolean handlePlacingItemIntoContainer(Player player, Container container, String input) {
        for (Item item : player.getInventory()) {
            if (input.equals(Integer.toString(player.getInventory().indexOf(item) + 1))) {
                player.placeItemIntoContainer(item, container);
                return true;
            }
        }
        return false;
    }

    /**
     * Handles the choice of whether to leave the Dungeon or not.
     */
    private static void handleExit(Player player) { //TODO handle win condition
        System.out.println("Are you sure you wish to exit? [Y]/[N]");
        if ("y".equals(scanner.nextLine())) {
            for (Item item : player.getInventory()) {
                if (item instanceof Treasure) {
                    System.out.println("You escaped with your life and sanity intact, and wealth beyond your wildest " +
                            "dreams!\n" +
                            "Congratulations, you won!\n" +
                            Treasure.TREASURE_IMAGE);
                    DragonTreasure.endGame(0);
                }
            }
            System.out.println("You leave the dungeon with your life and sanity intact.\n" +
                    "You survived, but you didn't win...");
            DragonTreasure.endGame(0);
        }
        System.out.println("You stay in this dark, sanity-destroying place.");
    }

    /**
     * Handles the player's choice of quitting the dungeon. Quitting means an automatic loss.
     */
    private static void handleQuit() {
        System.out.println("Are you sure you wish to quit? [Y]/[N]");
        if ("y".equals(scanner.nextLine())) {
            System.out.println("The Darkness and Horrors of this place have have broken your mind.\n" +
                    "You are now lost in the dark, forever...");
            DragonTreasure.endGame(0);
        }
        System.out.println("You stay in this dark, sanity-destroying place.");
    }

    /**
     * Handles attacking for the player.
     */
    private static void handleAttack(Player player) {
        if (player.getCurrentRoom().getMob() == null) {
            System.out.println("Nothing to [A]ttack here.");
            return;
        }
        System.out.println("You chose to attack the "
                + player.getCurrentRoom().getMob().getNoun() + ".");
        player.attack(player.getCurrentRoom().getMob());
    }

    /**
     * Prints debug info for the game and player.
     */
    private static void handleDebug(Player player) {
        DragonTreasure.debug();
        player.printStats();
    }
}