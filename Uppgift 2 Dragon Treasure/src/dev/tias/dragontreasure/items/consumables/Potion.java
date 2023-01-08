package dev.tias.dragontreasure.items.consumables;

import dev.tias.dragontreasure.mobs.Mob;

/**
 * A health potion. Heals the Mob it is used upon. Removed upon use.
 */
public class Potion extends Consumable {
    private static final int health = 50;

    public Potion() {
        this.description = "a potion that looks refreshing";
        this.noun = "Potion";
        this.indefiniteNoun = "a Potion";
    }

    /**
     * Adds the health of the potion to the health of mob.
     * @param mob the mob whose inventory contains the potion. Effects are applied to mob.
     */
    @Override
    public void consume(Mob mob) {
        System.out.println(mob.getNoun() + " drank " + this.noun + ".");
        mob.heal(health);
        mob.getInventory().remove(this);
    }
}
