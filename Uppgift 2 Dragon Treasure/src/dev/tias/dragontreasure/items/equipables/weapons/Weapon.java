package dev.tias.dragontreasure.items.equipables.weapons;

import dev.tias.dragontreasure.items.equipables.Equipable;
import dev.tias.dragontreasure.mobs.humanoids.Humanoid;

/**
 * Weapons are a type of Equipable which a Humanoid can equip to increase its damage output.
 */
public abstract class Weapon extends Equipable {
    protected static final int WEAPON_BASE_DAMAGE = 2; //Does 1 more damage than MOB_BASE_DAMAGE
    protected int damage;

    public Weapon() {
        super();
        this.description = "AN ABSTRACT WEAPON";
        this.noun = "WEAPON";
        this.indefiniteNoun = "A WEAPON";
        this.damage = WEAPON_BASE_DAMAGE;
    }

    /**
     * Equipping a weapon changes the damage of humanoid to the damage value of the weapon.
     */
    public void equip(Humanoid humanoid) {
        super.equip(humanoid);
        humanoid.setDamage(damage);
        System.out.println(humanoid.getNoun() + " damage: " + humanoid.getDamage() + ".");

    }

    /**
     * Resets the damage value of humanoid to its base damage and unequips the weapon.
     */
    public void unEquip(Humanoid humanoid) {
        super.unEquip(humanoid);
        humanoid.setDamage(humanoid.getBaseDamage());
        System.out.println(humanoid.getNoun() + " damage: " + humanoid.getDamage() + ".");
    }

    public int getDamage() {
        return damage;
    }
}
