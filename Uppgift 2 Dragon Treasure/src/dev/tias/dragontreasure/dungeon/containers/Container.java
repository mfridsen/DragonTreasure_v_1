package dev.tias.dragontreasure.dungeon.containers;

import dev.tias.dragontreasure.items.Item;

import java.util.ArrayList;

/**
 * A Container contains (I know, mind blown) Items in an ArrayList.
 */
public abstract class Container {
    public static final int CONTAINER_DEFAULT_SIZE = 1;
    protected int size;
    protected ArrayList<Item> items;

    protected String description;
    protected String noun;
    protected String indefiniteNoun;

    public Container() {
        this.size = CONTAINER_DEFAULT_SIZE;
        this.items = new ArrayList<>();
    }

    /**
     * Displays contents of the Container.
     */
    public void open() {
        if (items.size() == 0) { //Empty container
            System.out.println("The " + this.noun + " is empty.");
            return;
        }
        //So, not empty
        System.out.println(this.noun + " " + this.items.size() + "/" + this.size + " Items:");
        int count = 1;
        for (Item item : items) {
            System.out.println("[" + count + "]: " + item.getNoun());
            count++;
        }
    }

    /**
     * Adds an Item into the Container.
     */
    public boolean addItem(Item item) {
        if (this.items.size() >= this.size) {
            System.out.print("can't put the " + item.getNoun() + " to the " + this.noun + "; full!");
            return false;
        }
        this.items.add(item);
        System.out.print("put the " + item.getNoun() + " in the " + this.noun + ".\n");
        return true;
    }

    /**
     * Removes an Item from the Container.
     */
    public boolean removeItem(Item item) {
        if (this.items.contains(item)) {
            this.items.remove(item);
            return true;
        }
        System.out.println("There is no " + item.getNoun() + " in the " + this.noun + ".");
        return false;
    }

    /**
     * Checks if this Container contains item.
     */
    public boolean containsItem(Item item) {
        return this.items.contains(item);
    }

    /**
     * Returns the number of Items in this Container.
     */
    public int numberOfItems() {
        return this.items.size();
    }

    /**
     * Get a specific Item at index from a Container.
     */
    public Item getItem(int index) {
        if (index >= this.size) {
            System.out.println("INDEX OF ITEM OUT OF BOUNDS OF CONTAINER!");
            return null;
        }
        return this.items.get(index);
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

    public int getSize() {
        return size;
    }
}
