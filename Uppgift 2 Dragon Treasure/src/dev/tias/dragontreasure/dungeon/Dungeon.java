package dev.tias.dragontreasure.dungeon;

import dev.tias.dragontreasure.dungeon.containers.Chest;
import dev.tias.dragontreasure.items.Treasure;
import dev.tias.dragontreasure.items.consumables.*;
import dev.tias.dragontreasure.items.equipables.armors.chestwear.ChestPlate;
import dev.tias.dragontreasure.items.equipables.armors.cloaks.Cloak;
import dev.tias.dragontreasure.items.equipables.armors.footwear.LeatherBoots;
import dev.tias.dragontreasure.items.equipables.armors.handwear.LeatherGloves;
import dev.tias.dragontreasure.items.equipables.armors.headwear.SteelGreatHelm;
import dev.tias.dragontreasure.items.equipables.armors.legwear.PlateGreaves;
import dev.tias.dragontreasure.items.equipables.weapons.Sword;
import dev.tias.dragontreasure.items.usables.Key;
import dev.tias.dragontreasure.mobs.monsters.Dragon;
import dev.tias.dragontreasure.mobs.monsters.Monster;

/**
 * This class simulates a Dungeon. The Dungeon is made up of a series of Rooms which contains Doors that connect them.
 * @author  Mattias Fridsén
 * @version 1.0
 * @since   2022-11-28
 */
public class Dungeon {
    private final Room[] rooms; //There is only one set of Rooms in the Dungeon

    /**
     * Constructor for the Dungeon. Creates all the Rooms for the Dungeon and connects them.
     */
    public Dungeon() {
        rooms = new Room[6];
     }

    /**
     * Public method to setup the dungeon, which calls on then Class' private methods.
     */
    public void setupDungeon() {
        createRooms();
        setupRooms();
        connectRooms();
    }

    /**
     * Creates all the Rooms in the Dungeon.
     */
    private void createRooms() {
        //Room 0 is the entrance room, with connections to the north and south. The rest of the rooms are counted
        //clock-wise rom Room 0, except for the final room with the Treasure, which is the last room in the array.
        //Room 0 connects to Room 1 to the north and Room 4 to the south.
        rooms[0] = new Room(0, new Door[]
                {new Door('n'), new Door('s')});

        //Room 1 is the room north of Room 0 and is connected to Room 2 to the east and Room 0
        //to the south. Contains a Sword.
        rooms[1] = new Room(1, new Door[]
                {new Door('e'), new Door('s')});

        //Room 2 is the north-eastern room, containing the Exit door to the east and connects to
        // Room 3 to the south and  Room 1 to the west. Contains a Monster.
        rooms[2] = new Room(2, new Door[]
                {new Door('e'), new Door('s'), new Door('w')});

        //Room 3 is connected to room 2 to the north, Room 5 to the east and Room 4 to the west.
        //The door to the east is locked and requires a key. Contains a Potion.
        rooms[3] = new Room(3, new Door[]
                {new Door('n'), new Door('e'), new Door('w')});

        //Room 4 is connected to Room 0 to the north and Room 3 to the east. Contains a Key.
        rooms[4] = new Room(4, new Door[]
                {new Door('n'), new Door('e')});

        //Room 5 is the final room, containing the Treasure and a Dragon, and is only connected to Room 3 to the west
        rooms[5] = new Room(5, new Door[]{new Door('w')});
    }

    /**
     * Adds Items and Monsters, sets Exits and locks Doors etc.
     */
    private void setupRooms() {
        //To test container functionality
        rooms[0].setContainer(new Chest());
        //TODO add items in the container
        rooms[0].getContainer().addItem(new ChestPlate());
        rooms[0].getContainer().addItem(new Cloak());
        rooms[0].getContainer().addItem(new LeatherBoots());
        rooms[0].getContainer().addItem(new LeatherGloves());
        rooms[0].getContainer().addItem(new SteelGreatHelm());
        rooms[0].getContainer().addItem(new PlateGreaves());

        //Room 1 has a Sword
        rooms[1].setItem(new Sword());

        //Room 2 has the Exit door as well as a Monster
        rooms[2].getDoors()[0].setExit(true); //The Exit to the Dungeon
        rooms[2].setMob(new Monster());

        //Room 3 has the locked Door leading to Room 5, as well as a Potion
        Key key = new Key(1); //Create a key that matches the door we lock
        rooms[3].getDoors()[1].setLockNum(1);
        if(!(rooms[3].getDoors()[1].lock(key))) //Eastern door is locked using the key we created
            System.out.println("Couldn't lock door!");
        rooms[3].setItem(new Potion());

        //And now we place the key in the correct room
        rooms[4].setItem(key);

        //Room 5 is the final Room, with the Dragon and its Treasure
        rooms[5].setItem(new Treasure());
        rooms[5].setMob(new Dragon());
     }

    /**
     * Connects the Rooms in the Dungeon.
     */
    private void connectRooms() {
        //The rooms have to be instantiated before they can be connected.
        //Again, room 0 connects to 1 (n) and 4 (s)
        rooms[0].getDoors()[0].setRoomConnecting(rooms[1]);
        rooms[0].getDoors()[1].setRoomConnecting(rooms[4]);

        //Room 1 connects to 2 (e) and 0 (s)
        rooms[1].getDoors()[0].setRoomConnecting(rooms[2]);
        rooms[1].getDoors()[1].setRoomConnecting(rooms[0]);

        //Room 2 connects to 6 (e)(xit), 3 (s) and 1 (w)
        rooms[2].getDoors()[0].setRoomConnecting(null); //Connects to no Room, since it is the Exit door
        rooms[2].getDoors()[1].setRoomConnecting(rooms[3]);
        rooms[2].getDoors()[2].setRoomConnecting(rooms[1]);

        //Room 3 connects to 2 (n), 5 (e) and 4 (w)
        rooms[3].getDoors()[0].setRoomConnecting(rooms[2]);
        rooms[3].getDoors()[1].setRoomConnecting(rooms[5]);
        rooms[3].getDoors()[2].setRoomConnecting(rooms[4]);

        //Room 4 connects to 0 (n) and 3 (e)
        rooms[4].getDoors()[0].setRoomConnecting(rooms[0]);
        rooms[4].getDoors()[1].setRoomConnecting(rooms[3]);

        //Room 5 connects to 3 (w)
        rooms[5].getDoors()[0].setRoomConnecting(rooms[3]);
     }

    /**
     * Debug function to print all the rooms, their doors, the position of their doors and the connections between
     * the rooms in the dungeon.
     */
    public void printRoomsAndDoors() {
        for (int i = 0; i < rooms.length; i++) {
            System.out.println("Room " + i + ".");
            rooms[i].printDoors();
        }
    }

    public Room[] getRooms() {
        return rooms;
    }
}
