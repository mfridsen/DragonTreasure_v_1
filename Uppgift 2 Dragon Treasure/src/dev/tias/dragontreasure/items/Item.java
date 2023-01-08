package dev.tias.dragontreasure.items;

/**
 * Abstract Class Item. Used to represent inanimate objects in the Dungeon which are not part of the Dungeon itself.
 * Items can, by definition, be picked up. In this game only the Player is capable of picking up Items.
 */
public abstract class Item {
    //Descriptions could be split up into several different variations, such as adjective, plural etc
    protected String description;
    protected String noun;
    protected String indefiniteNoun;

    public Item() {
        this.description = "AN ABSTRACT ITEM";
        this.noun = "ITEM";
        this.indefiniteNoun = "AN ITEM";
    }

    public String getDescription() {
        return description;
    }

    public String getNoun() {
        return noun;
    }

    public String getIndefiniteNoun() {
        return indefiniteNoun;
    }
}
