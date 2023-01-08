package dev.tias.dragontreasure.mobs;

import dev.tias.dragontreasure.dungeon.Room;
import dev.tias.dragontreasure.items.Item;
import dev.tias.dragontreasure.mobs.humanoids.Player;

import java.util.ArrayList;

/**
 * Abstract Class Mob. Used to represent living (or undead) beings in the Dungeon. Mobs are defined by having health
 * and damage, as well as methods for damaging and healing to affect that damage.
 */
public abstract class Mob {
    //Descriptions could be split up into several different variations, such as adjective, plural etc
    protected String description;
    protected String noun;
    protected String indefiniteNoun;

    //"Globals", base values
    public static final int MOB_BASE_MAX_HEALTH = 100;
    public static final int MOB_BASE_DAMAGE = 2; //If these were set to 0, IntelliJ would complain
    public static final int MOB_BASE_ARMOR = 1; //If these were set to 0, IntelliJ would complain
    public static final int MOB_BASE_INVENTORY_SIZE = 10;

    //The actual values used by Mobs to represent their stats
    protected int maxHealth; //Maximum health of the Mob
    protected int health; //Actual health of the Mob
    protected int baseDamage; //Base damage value of the Mob
    protected int damage; //Reduces health of attacked Mob by this value minus the Mobs' armor value
    protected int baseArmor; //Base armor value of the Mob
    protected int armor; //Reduces damage received by this value
    protected int inventorySize; //How large the inventory of the Mob is
    protected boolean dead; //Used to check if a Mob has been killed

    //Inventory management
    protected ArrayList<Item> inventory; //All Items the Mob is carrying, since monsters could drop body parts etc

    //Location management
    protected Room currentRoom; //Used to track Mob location in the Dungeon and to remove dead mobs

    /**
     * This constructor is set up in a way that is easily changed in extensions of this class. Just begin by calling
     * super(), and after that change any values as needed below.
     */
    public Mob() {
        this.description = "AN ABSTRACT MOB";
        this.noun = "MOB";
        this.indefiniteNoun = "A MOB";
        this.maxHealth = MOB_BASE_MAX_HEALTH;
        this.health = this.maxHealth;
        this.baseDamage = MOB_BASE_DAMAGE;
        this.damage = this.baseDamage;
        this.baseArmor = MOB_BASE_ARMOR;
        this.armor = this.baseArmor;
        this.inventorySize = MOB_BASE_INVENTORY_SIZE;
        this.dead = false;
        this.inventory = new ArrayList<>();
    }

    /**
     * Called by a Mob to attack the target Mob mob.
     * @param mob the mob being attacked
     */
    public void attack(Mob mob) {
        System.out.println(this.noun + " attacked " + mob.noun + " for " + this.damage + " damage.");
        //Do the actual damage
        mob.damage(this.damage);
        //Check if mob was killed and if so, kill it with the kill()-method
        if (mob.dead) {
            if (this instanceof Player) ((Player) this).addKill(mob);   //for adding kills to the Player kill list
            kill(mob);
        }
    }

    /**
     * Reduces actual health by damage amount. If health is reduced to or below zero, mob is killed.
     * @param damage how much health is reduced by
     */
    public void damage(int damage) {
        this.health -= (damage - this.armor);
        if (this.health <= 0) this.dead = true;
        else System.out.println(this.getNoun() + " health: " + this.health + ".");
    }

    /**
     * Sets mob to null.
     */
    public void kill(Mob mob) {
        System.out.println("The " + mob.getNoun() + " died.");
        if (this.inventory.size() > 0) {
            //TODO Implement loot dropping
        }
        this.currentRoom.setMob(null);
    }

    /**
     * This is the function healing items and spells will use.
     * @param health how much the Mobs health is increased by
     */
    public void heal(int health) {
        if (this.health + health > this.maxHealth) {
            System.out.println("Fully healed " + this.noun + ".");
            this.health = this.maxHealth;
        } else {
            System.out.println("Healed " + this.noun + " for " + health + " health.");
            this.health += health;
        }
        System.out.println(this.noun + " health: " + this.health + ".");
    }

    /**
     * Prints a list of every Item in inventory.
     */
    public void printInventory() { //Could be called checkInventory but I call everything else printX so
        if (inventory.size() <= 0) {
            System.out.println("No items in inventory!");
            return;
        }
        System.out.println("Inventory: " + this.inventory.size() + "/" + this.inventorySize + " Items:");
        int count = 0;
        for (Item item: inventory) {
            System.out.println("[" + (count + 1) + "]: " + item.getNoun());
            count++;
        }
    }

    /**
     * Setters and Getters are self-explanatory
     */
    public String getDescription() {
        return description;
    }

    public String getNoun() {
        return noun;
    }

    public String getIndefiniteNoun() {
        return indefiniteNoun;
    }

    public int getHealth() {
        return health;
    }

    public int getBaseDamage() {
        return MOB_BASE_DAMAGE;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getBaseArmor() {
        return baseArmor;
    }

    public void setBaseArmor(int baseArmor) {
        this.baseArmor = baseArmor;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public boolean isDead() {
        return dead;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public Item getInventoryItem(int index) {
        return this.inventory.get(index);
    }

    public boolean removeInventoryItem(Item item) {
        if (this.inventory.contains(item)) {
            this.inventory.remove(item);
            return true;
        }
        System.out.println("Can't remove the" + item.getNoun() + "; not in the " + this.noun + "'s inventory!");
        return false;
    }
}
