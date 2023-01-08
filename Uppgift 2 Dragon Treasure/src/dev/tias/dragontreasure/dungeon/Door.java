package dev.tias.dragontreasure.dungeon;

import dev.tias.dragontreasure.items.usables.Key;

/**
 * This class simulates a Door. Has a location relative to the Room the Door is in, and can be locked or unlocked.
 * @author  Mattias Fridsén
 * @version 1.0
 * @since   2022-11-28
 */
public class Door {
    private final char position; //North, East, South, West
    private Room roomConnecting;
    //The Room "behind" the door. Since the Room isn't created by to Door, I don't view as a Door "having" a Room,
    //but as a Door "knowing of" a Room. Should be final but can't be, since we have to create doors to create rooms,
    //and if this is final, we need to create rooms without creating doors,
    //so it became a hen-and-egg problem

    private boolean isLocked;
    private int lockNum; //To be able to match Keys to "Locks" (there are no locks)
    private boolean isExit;

    /**
     * Creates a Door with position p, connecting to Room r.
     */
    public Door(char p) {
        this.position = p;
        this.isLocked = false; //All doors are unlocked by default
        this.lockNum = 0; //All doors start with lockNum 0, meaning they can't be locked
        this.isExit = false; //All doors are not the exit by default
    }

    /**
     * Setters and getters are self-explanatory.
     */
    public boolean isLocked() {
        return isLocked;
    }

    /**
     * Unlocks the door, if the door has a lockNum greater than 0. This is to prevent accidental locking of
     * doors that shouldn't be locked. You can still lock a door without creating a key for it, but at least you can't
     * do it without actively assigning a lockNum to the door first.
     * @return true if the door could be locked, otherwise false
     */
    public boolean lock(Key key) {
        if (this.lockNum == 0) {
            System.out.println("Can't lock door; no key for it.");
            return false;
        }
        if (this.lockNum == key.getKeyNum()) {
            if (!this.isLocked) {
                this.isLocked = true;
                return true;
            }
            System.out.println("Can't lock this door; already locked.");
            return false;
        }
        System.out.println("Can't lock this door; wrong key.");
        return false;
    }

    /**
     * Unlocks the door.
     * @return true if door was unlocked, otherwise false
     */
    public boolean unlock(Key key) {
        if (this.lockNum == key.getKeyNum()) {
            if (this.isLocked) {
                this.isLocked = false;
                return true;
            }
            System.out.println("Can't unlock this door; already unlocked.");
            return false;
        }
        System.out.println("Couldn't unlock; not the correct key.");
        return false;
    }

    /**
     * Getters and setters
     */
    public char getPosition() {
        return position;
    }

    public Room getRoomConnecting() {
        return roomConnecting;
    }

    public void setRoomConnecting(Room room) {
        this.roomConnecting = room;
    }

    public boolean isExit() {
        return isExit;
    }

    public void setExit(boolean exit) {
        isExit = exit;
    }

    public int getLockNum() {
        return lockNum;
    }

    public void setLockNum(int lockNum) {
        this.lockNum = lockNum;
    }
}
