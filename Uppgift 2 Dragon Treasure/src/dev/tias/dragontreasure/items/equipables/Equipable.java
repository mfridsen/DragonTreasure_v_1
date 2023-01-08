package dev.tias.dragontreasure.items.equipables;

import dev.tias.dragontreasure.items.Item;
import dev.tias.dragontreasure.mobs.humanoids.Humanoid;

/**
 * Equipables are a type of Item which can be equipped to give some sort of passive effect.
 */
public abstract class Equipable extends Item {
    protected boolean equipped; //Is the equipable currently equipped?

    public Equipable() {
        super();
        this.description = "AN ABSTRACT EQUIPABLE";
        this.noun = "EQUIPABLE";
        this.indefiniteNoun = "AN EQUIPABLE";
        this.equipped = false; //Equipables aren't equipped by default
    }

    /**
     * The effects of each sub-category of equipables will be different, but the boolean is always changed and a
     * relevant message is always printed. Sub-classes implement this method by first calling super.equip(humanoid).
     *
     * The prints in these methods go hand-in-hand with the prints in Humanoid. They are split into two parts to
     * show that both methods are being called properly.
     * @param humanoid the Humanoid equipping or unequipping this Equipable.
     */
    public void equip(Humanoid humanoid) {
        this.equipped = true;
    }

    public void unEquip(Humanoid humanoid) {
        this.equipped = false;
    }

    public boolean isEquipped() {
        return equipped;
    }
}
