package dev.tias.dragontreasure.items.equipables.armors;

import dev.tias.dragontreasure.items.equipables.Equipable;
import dev.tias.dragontreasure.mobs.humanoids.Humanoid;

/**
 * Items which are worn on the body. Originally named "Wearable", but since the one thing all Armors have in common
 * is that they have an armor value that they add onto the players existing armor value upon equipping, "Armor" is
 * more fitting and clear as a name.
 */
public abstract class Armor extends Equipable {
    public static final int ARMOR_BASE_ARMOR = 10;
    protected int armor;

    public Armor() {
        super();
        this.description = "AN ABSTRACT ARMOR";
        this.noun = "ARMOR";
        this.indefiniteNoun = "AN ARMOR";
        this.armor = ARMOR_BASE_ARMOR;
    }

    /**
     * Adds the armor value of this to the previous armor value of the humanoid.
     */
    @Override
    public void equip(Humanoid humanoid) {
        super.equip(humanoid);
        humanoid.setArmor(humanoid.getArmor() + this.armor); //Player might more pieces of armor on
        System.out.println(humanoid.getNoun() + " armor: " + humanoid.getArmor() + ".");

    }

    /**
     * Subtracts the armor value of this from the armor value of the humanoid.
     */
    @Override
    public void unEquip(Humanoid humanoid) {
        super.unEquip(humanoid);
        humanoid.setArmor(humanoid.getArmor() - this.armor); //Player might have more pieces of armor on
        System.out.println(humanoid.getNoun() + " armor: " + humanoid.getArmor() + ".");
    }
}
