package dev.tias.dragontreasure.items.usables;

import dev.tias.dragontreasure.dungeon.Door;
import dev.tias.dragontreasure.mobs.humanoids.Humanoid;

//TODO each DoorKey should have a number, corresponding to a locked door
//TODO add unlocking containers

/**
 * Keys open locked doors.
 */
public class Key extends Usable {
    private final int keyNum;

    public Key(int keyNum) {
        this.keyNum = keyNum; //TODO IMPLEMENT THIS //Used to check that keys fit the correct lock
        this.description = "a key that looks like it would fit a door";
        this.noun = "Key";
        this.indefiniteNoun = "a Key";
    }

    /**
     * Uses the key to unlock any locked doors in the room where humanoid is situated.
     * @param humanoid the humanoid using the key
     */
    public void use(Humanoid humanoid) {
        System.out.println("Used Key.");
        int doorNum = 1;
        int lockedDoors = 0;
        for (Door door : humanoid.getCurrentRoom().getDoors()) {
            if (door.isLocked()) { //If a door is locked
                if (door.getLockNum() == this.keyNum) { //And has a matching lockNum
                    if(door.unlock(this)) {
                        System.out.println("Unlocked Door " + doorNum + ".");
                        continue;
                    } else {
                        System.out.println("Couldn't unlock Door " + doorNum + "!");
                        continue;
                    }
                }
                lockedDoors++;
                System.out.println("You have no key for this lock.");
            } else { //Unlocked doors
                if (door.getLockNum() == this.keyNum) { //That have a matching lockNum
                    if (door.lock(this)) {
                        System.out.println("Locked Door " + doorNum + ".");
                        lockedDoors++;
                    }
                }
            }
            doorNum++;
        }
        if (lockedDoors > 0) {
            System.out.println("No door to unlock in this room.");
        }
    }

    public int getKeyNum() {
        return keyNum;
    }
}
