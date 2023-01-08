package dev.tias.dragontreasure.mobs.humanoids;

import dev.tias.dragontreasure.DragonTreasure;
import dev.tias.dragontreasure.dungeon.containers.Container;
import dev.tias.dragontreasure.items.Item;
import dev.tias.dragontreasure.items.equipables.Equipable;
import dev.tias.dragontreasure.items.equipables.armors.chestwear.ChestArmor;
import dev.tias.dragontreasure.items.equipables.armors.cloaks.Cloak;
import dev.tias.dragontreasure.items.equipables.armors.footwear.FootWear;
import dev.tias.dragontreasure.items.equipables.armors.handwear.HandWear;
import dev.tias.dragontreasure.items.equipables.armors.headwear.HeadWear;
import dev.tias.dragontreasure.items.equipables.armors.legwear.LegWear;
import dev.tias.dragontreasure.items.equipables.necklaces.Necklace;
import dev.tias.dragontreasure.items.equipables.rings.Ring;
import dev.tias.dragontreasure.items.equipables.weapons.Weapon;
import dev.tias.dragontreasure.mobs.Mob;

import java.util.ArrayList;

//TODO implement ring and necklace handling

/**
 * Humanoids can equip armor and weapons, as well as pick up and use items.
 */
public abstract class Humanoid extends Mob {
    //Inventory management
    protected ArrayList<Equipable> equippedItems; //Equipables in the Inventory that the Humanoid has equipped

    //Wearable Slots
    protected FootWear feet;
    protected LegWear legs;
    protected ChestArmor chest;
    protected Cloak cloak;
    protected HandWear hands;
    protected HeadWear head;
    protected Necklace necklace;
    protected Ring[] rings;

    //Two weapon slots, note that shields will be implemented as weapons
    protected Weapon right; //TODO IMPLEMENT USING THIS
    protected Weapon left;

    /**
     * Let's say you want to create a humanoid enemy class, which is armed and armored.
     * All you need to do is to create a Constructor which calls super(), then add items to whichever slots
     * you wish, like so:
     *          this.feet = new LeatherBoots();
     *
     */
    public Humanoid() {
        super();
        this.description = "AN ABSTRACT HUMANOID";
        this.noun = "HUMANOID";
        this.indefiniteNoun = "A HUMANOID";
        this.equippedItems = new ArrayList<>();
        this.feet = null;
        this.left = null;
        this.chest = null;
        this.cloak = null;
        this.hands = null;
        this.head = null;
        this.necklace = null;
        this.rings = new Ring[10];
    }

    /**
     * Adds item to the inventory and returns true, unless the inventory is full, in which case returns false.
     */
    public boolean pickUpItem(Item item) {
        //Is there space in inventory?
        if (this.inventory.size() >= this.inventorySize) {
            System.out.println("Can't pick up " + item.getNoun() + "; inventory full.");
            return false;
        }
        //There is space in inventory
        this.inventory.add(item);
        System.out.println(this.noun + " picked up " + item.getIndefiniteNoun() + ".");
        return true;
    }

    /**
     * If item exists in container, and this object is capable of picking it up, returns true. Otherwise false.
     */
    public boolean retrieveItemFromContainer(Item item, Container container) {
        //There's no item in container
        if (!container.containsItem(item)) {
            System.out.println("Can't retrieve " + item.getNoun() + "; not in " + container.getNoun() + "!");
            return false;
        }
        //Could retrieve item
        return this.pickUpItem(item) && container.removeItem(item);
    }

    /**
     * Attempts to place item into container. Returns true upon success, otherwise false.
     */
    public boolean placeItemIntoContainer(Item item, Container container) {
        //Does inventory actually contain item?
        if (!this.inventory.contains(item)) {
            System.out.println("Can't place the " + item.getNoun() + " into the " + container.getNoun() + "; " +
                    " Not in inventory!");
            return false;
        }

        System.out.print(this.getNoun() + " ");
        if (!container.addItem(item))
            return false;

        if (item instanceof Equipable) {
            if (equippedItems.contains(item))
                this.unEquipItem((Equipable) item);
        }
        this.inventory.remove(item);
        return true;
    }

    /**
     * Discards item and places it in the current room. WILL OVERWRITE PREVIOUS ITEM IN CURRENT ROOM.
     */
    public void discardItem(Item item) {
        if (item instanceof Equipable) {
            //This won't happen since equipped items are moved from inventory to equippedItems
            //But the discard-menu will only print inventory items
            if (this.equippedItems.contains(item)) {
                this.unEquipItem((Equipable) item);
            }
        }
        System.out.println(this.noun + " discarded " + item.getIndefiniteNoun() + ".");
        this.getCurrentRoom().setItem(item);
        this.inventory.remove(item);
    }

    /**
     * To equip an item properly: call humanoid.equipItem(equipable).
     */
    public void equipItem(Equipable equipable) {
        if (inventory.contains(equipable)) {
            //Inventory = "backpack" of player. Equipping removes stuff from inventory and makes room for more
            if (!equipToSlot(equipable)) return; //No available slot
            System.out.print(this.noun + " equipped the " + equipable.getNoun() + ".\n");
            equipable.equip(this);
            equippedItems.add(equipable); //Add first, then remove
            inventory.remove(equipable);
        } else {
            System.out.println("Can't equip the " + equipable.getNoun() + "; not in inventory!");
        }
    }

    /**
     * These checks belong here. The items themselves don't care about whether the equipper has space to wear them or
     * not.
     */
    private boolean equipToSlot(Equipable equipable) {
        //Chest armor goes in the chest slot
        if (equipable instanceof ChestArmor) {
            if (this.chest == null) {
                this.chest = (ChestArmor) equipable;
                System.out.print(this.getNoun() + ": Chest: ");
                return true;
            }
            System.out.println("Can't equip " + equipable.getNoun()
                    + "; Chest Slot occupied by " + this.chest.getNoun() + ".");
            return false;
        }

        //Cloaks
        else if (equipable instanceof Cloak) {
            if (this.cloak == null) {
                this.cloak = (Cloak) equipable;
                System.out.print(this.getNoun() + ": Cloak: ");
                return true;
            }
            System.out.println("Can't equip " + equipable.getNoun()
                    + "; Cloak Slot occupied by " + this.cloak.getNoun() + ".");
            return false;
        }

        //FootWear
        else if (equipable instanceof FootWear) {
            if (this.feet == null) {
                this.feet = (FootWear) equipable;
                System.out.print(this.getNoun() + ": Feet: ");
                return true;
            }
            System.out.println("Can't equip " + equipable.getNoun()
                    + "; Cloak Slot occupied by " + this.feet.getNoun() + ".");
            return false;
        }

        //HandWear
        else if (equipable instanceof HandWear) {
            if (this.hands == null) {
                this.hands = (HandWear) equipable;
                System.out.print(this.getNoun() + ": Hands: ");
                return true;
            }
            System.out.println("Can't equip " + equipable.getNoun()
                    + "; Cloak Slot occupied by " + this.hands.getNoun() + ".");
            return false;
        }

        //HeadWear
        else if (equipable instanceof HeadWear) {
            if (this.head == null) {
                this.head = (HeadWear) equipable;
                System.out.print(this.getNoun() + ": Head: ");
                return true;
            }
            System.out.println("Can't equip " + equipable.getNoun()
                    + "; Cloak Slot occupied by " + this.head.getNoun() + ".");
            return false;
        }

        //LegWear
        else if (equipable instanceof LegWear) {
            if (this.legs == null) {
                this.legs = (LegWear) equipable;
                System.out.print(this.getNoun() + ": Legs: ");
                return true;
            }
            System.out.println("Can't equip " + equipable.getNoun()
                    + "; Cloak Slot occupied by " + this.legs.getNoun() + ".");
            return false;
        }

        //TODO implement two-handed
        //Weapons go in one of the two hands, right hand first
        else if (equipable instanceof Weapon) {
            if (this.right == null) {
                this.right = (Weapon) equipable;
                System.out.print(this.getNoun() + ": Right Hand: ");
                return true;
            } else if (this.left == null) {
                this.left = (Weapon) equipable;
                System.out.print(this.getNoun() + ": Left Hand: ");
                return true;
            }
            System.out.println("Can't equip " + equipable.getNoun() + ";\n " +
                    "Right Hand Slot occupied by " + this.right.getNoun() + ";\n" +
                    "Left Hand Slot occupied by " + this.left.getNoun() + ".");
            return false;
        }

        //Necklace //TODO implement

        //Rings //TODO implement

        System.out.println("CAN'T EQUIP " + equipable.getNoun() + "; BELONGS TO NO EQUIPABLE CATEGORY!");
        DragonTreasure.endGame(1);
        return false;
    }

    /**
     * To unequip an item properly: call humanoid.unEquipItem(equipable).
     */
    public void unEquipItem(Equipable equipable) {
        if (this.inventory.size() >= this.inventorySize) {
            System.out.println("Can't unequip the " + equipable.getNoun() + "; inventory full!");
            return;
        }
        if (equippedItems.contains(equipable)) {
            //Inventory = "backpack" of player. Equipping adds stuff back to inventory and there's room for less
            if (!removeFromSlot(equipable)) return;
            System.out.print(this.noun + " unequipped the " + equipable.getNoun() + ".\n");
            equipable.unEquip(this);
            inventory.add(equipable); //Add first, then remove
            equippedItems.remove(equipable);
        } else {
            System.out.println("Can't unequip the " + equipable.getNoun() + "; not equipped!");
        }
    }

    private boolean removeFromSlot(Equipable equipable) {
        //Chest
        if (this.chest == equipable) {
            this.chest = null;
            System.out.print(this.getNoun() + ": Chest: ");
            return true;
        }

        //Cloak
        else if (this.cloak == equipable) {
            this.cloak = null;
            System.out.print(this.getNoun() + ": Cloak: ");
            return true;
        }

        //FootWear
        else if (this.feet == equipable) {
            this.feet = null;
            System.out.print(this.getNoun() + ": Feet: ");
            return true;
        }

        //HandWear
        else if (this.hands == equipable) {
            this.hands = null;
            System.out.print(this.getNoun() + ": Hands: ");
            return true;
        }

        //HeadWear
        else if (this.head == equipable) {
            this.head = null;
            System.out.print(this.getNoun() + ": Head: ");
            return true;
        }

        //LegWear
        else if (this.legs == equipable) {
            this.legs = null;
            System.out.print(this.getNoun() + ": Legs: ");
            return true;
        }

        //Weapons //TODO two handed
        else if (this.right == equipable) {
            this.right = null;
            System.out.print(this.getNoun() + ": Right Hand: ");

            if (this.left == equipable) {
                this.left = null;
                System.out.print(this.getNoun() + ": Left Hand: ");
            }

            return true;
        }

        //Necklace //TODO implement

        //Rings //TODO implement

        return false;
    }

    /**
     * Prints a list of all Equipables in inventory.
     */
    public void printEquippedItems() {
        if (equippedItems.size() <= 0) {
            System.out.println("No equipped items!");
            return;
        }
        System.out.println("Equipped Items:");
        int index = 0;
        for (Equipable equipable: equippedItems) {
            System.out.println("[" + (index + 1) + "]: " + equipable.getNoun());
            index++;
        }
    }

    /**
     * Setters and Getters are self-explanatory
     */
    public ArrayList<Equipable> getEquippedItems() {
        return equippedItems;
    }

    public FootWear getFeet() {
        return feet;
    }

    public LegWear getLegs() {
        return legs;
    }

    public ChestArmor getChest() {
        return chest;
    }

    public Cloak getCloak() {
        return cloak;
    }

    public HandWear getHands() {
        return hands;
    }

    public HeadWear getHead() {
        return head;
    }

    public Weapon getRight() {
        return right;
    }

    public Weapon getLeft() {
        return left;
    }
}
