package dev.tias.dragontreasure.items.usables;

import dev.tias.dragontreasure.items.Item;
import dev.tias.dragontreasure.mobs.humanoids.Humanoid;

/**
 * Usables are a type of Item which are used in a specific context.
 */
public abstract class Usable extends Item {

    /**
     * Every usable has a specific context in which they are used, and therefore the use-method has to be different
     * for each.
     */
    public abstract void use(Humanoid humanoid);
}
