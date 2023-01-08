package dev.tias.dragontreasure.mobs.humanoids;

import dev.tias.dragontreasure.mobs.Mob;

import java.util.ArrayList;

/**
 * This class represents the Player.
 * @author  Mattias Fridsén
 * @version 1.0
 * @since   2022-11-28
 */
public class Player extends Humanoid {
    private final String name;
    private boolean moved;  //Had to add this boolean in order to prevent the Monsters from Attacking
                            // the Player as he enters a Room

    private ArrayList<String> killTypes; //Saves one description of each type of Mob the Player kills
    private ArrayList<Integer> killNumbers; //How many of each type of Mob the Player has killed

    /**
     * Creates a Player.
     */
    public Player(String name) {
        super();
        this.name = name;
        this.description = "This is the Player, " + this.name;
        this.noun = "Player " + this.name;
        this.indefiniteNoun = "a Player";
        this.killTypes = new ArrayList<>();
        this.killNumbers = new ArrayList<>();
        this.damage = 10001;
    }

    /**
     * Adds a kill to the kill list(s).
     */
    public void addKill(Mob mob) {
        if (killTypes.contains(mob.getNoun())) {
            killNumbers.set(killTypes.indexOf(mob.getNoun()),
                    killNumbers.get(killTypes.indexOf(mob.getNoun())) + 1);
        } else {
            killTypes.add(mob.getNoun());
            killNumbers.add(1);
        }
    }

    /**
     * Debug function. Prints all the player stats.
     */
    public void printStats() {
        System.out.println("Player Stats:");
        System.out.println("Max health:     " + this.maxHealth);
        System.out.println("Current health: " + this.health);
        System.out.println("Current damage: " + this.damage);
        System.out.println("Current armor:  " + this.armor);
        System.out.println("Dead:           " + this.dead);
        printKills();
        System.out.println();
    }

    /**
     * Prints the player kill list.
     */
    public void printKills() {
        System.out.println("Printing kills:");
        for (String mob : killTypes) {
            System.out.println(mob + ":     " + killNumbers.get(killTypes.indexOf(mob)));
        }
    }

    /**
     * Self-explanatory.
     */
    public String getName() {
        return name;
    }

    public boolean hasMoved() {
        return moved;
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }
}
