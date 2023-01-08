package dev.tias.dragontreasure.items.consumables;

import dev.tias.dragontreasure.items.Item;
import dev.tias.dragontreasure.mobs.Mob;

/**
 * Consumables are a type of Usable which perish after use. Their effects are applied
 */
public abstract class Consumable extends Item {

    /**
     * Abstract because every consumable has a different effect.
     * @param mob the mob whose inventory contains the consumable. Doesn't necessarily mean effects are applied to mob
     */
    public abstract void consume(Mob mob);
}
