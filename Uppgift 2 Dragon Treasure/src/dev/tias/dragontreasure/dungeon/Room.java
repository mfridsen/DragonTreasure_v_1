package dev.tias.dragontreasure.dungeon;

import dev.tias.dragontreasure.DragonTreasure;
import dev.tias.dragontreasure.dungeon.containers.Container;
import dev.tias.dragontreasure.mobs.Mob;
import dev.tias.dragontreasure.items.Item;

/**
 * This class simulates a Room in a Dungeon. A Room can have 1-4 Doors, stored in the Door[] doors.
 * A Room can also contain one (1) and only one Item, one and only one (1) Monster,
 * or one and only one (1) of each. These can be assigned with the different constructor methods, or set with the
 * setters.
 * @author  Mattias Fridsén
 * @version 1.0
 * @since   2022-11-28
 */
public class Room {
    private final String roomDesc;
    private final int roomNum;
    private final Door[] doors;

    private Container container;
    private Item item; //There can be one Item on the floor of the Room
    private Mob mob;

    private static final String[] descList = {
            "The room is lit by some candles on a table in front of you.",
            "You see a dead body on the floor.",
            "You see a burning torch in one of the rooms' corners and feel a repulsive stench.",
            "You enter a damp room with water oozing down the western wall.",
            "You enter a spacious mountain room with a streak of light coming through a crack in the eastern wall."
    };

    /**
     * Since all the Rooms are hard-coded, this was the most practical solution for a Constructor method.
     * Doors should be assigned clock-wise, starting from the North.
     */
    public Room (int roomNum, Door[] doors) {
        this.roomNum    = roomNum;
        this.doors      = doors;
        this.roomDesc   = descList[roomNum%descList.length]; //Rotates through the descList
        this.container  = null;     //Just to make sure
        this.item       = null;     //Just to make sure
        this.mob        = null;     //Just to make sure
    }

    /**
     * Prints all the information about the Room which is relevant to the Player.
     */
    public void doNarrative() {
        //System.out.println("This is Room " + roomNum); //debug print
        System.out.println(roomDesc);
        printDoors();
        printContainer();
        printItem();
        printMob();
    }

    /**
     * Print functions are self-explanatory.
     */
    public void printDoors() {
        if (doors.length > 1)
            System.out.println("This room contains " + doors.length + " doors.");
        else
            System.out.println("This room contains one door.");

        for (int i = 0; i < doors.length; i++) {
            System.out.print("Door ");

            switch (i) {
                case 0:
                    System.out.print("One leads to the ");
                    break;
                case 1:
                    System.out.print("Two leads to the ");
                    break;
                case 2:
                    System.out.print("Three leads to the ");
                    break;
                case 3:
                    System.out.print("Four leads to the ");
                    break;
                default:
                    System.out.println("TOO MANY DOORS IN THIS ROOM!");
                    DragonTreasure.endGame(1);
            }

            switch (doors[i].getPosition()) {
                case 'n':
                    System.out.print("[N]orth");
                    break;
                case 'e':
                    System.out.print("[E]ast");
                    break;
                case 's':
                    System.out.print("[S]outh");
                    break;
                case 'w':
                    System.out.print("[W]est");
                    break;
                default:
                    throw new IllegalStateException("Unexpected value for position: " + doors[i].getPosition());
            }

            if (doors[i].getRoomConnecting() == null) {
                if (doors[i].isExit()) {
                    System.out.print(" to the Exit.\n");
                } else {
                    System.out.print(" the empty void.\n");
                    System.out.println("THIS SHOULDN'T HAPPEN; ONLY EXIT DOORS SHOULD HAVE NULL BEHIND THEM!");
                    DragonTreasure.endGame(1);
                }
            } else {
                //System.out.print(" to Room " + doors[i].getRoomConnecting().getRoomNum() + ".\n"); //Debug print
                System.out.print(".\n");
            }

            //Since doors are unlocked by default, it is only relevant to the player to know when a door is locked
            if (doors[i].isLocked()) {
                System.out.println("This door is locked.");
            }
        }
    }

    private void printContainer() {
        if (!(container == null))
            System.out.println("There is " + container.getIndefiniteNoun() + " here. [O]pen?");
    }

    private void printMob() {
        if (!(mob == null))
            System.out.println("There is " + mob.getIndefiniteNoun() + " here. [A]ttack?");
    }

    private void printItem() {
        if (!(item == null))
            System.out.println("There is " + item.getIndefiniteNoun() + " on the floor. [P]ick up?");
    }

    /**
     * Setters and Getters are self-explanatory. S&G that are not used have been removed.
     */
    public int getRoomNum() {
        return roomNum;
    }

    public Door[] getDoors() {
        return doors;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Mob getMob() {
        return mob;
    }

    public void setMob(Mob mob) {
        this.mob = mob;
        if (!(mob == null)) mob.setCurrentRoom(this);
    }

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }
}
