package admin.fastfight;

import java.util.ArrayList;

public abstract class Combatant {
    abstract int getMaxHealth();
    abstract int getMaxMagic();
    abstract int getHealth();
    abstract int getMagic();
    abstract int getStrength();
    abstract int getDefense();
    abstract int getSpeed();
    abstract int getLevel();
    abstract int getMoney();
    abstract String getName();

    abstract void setHealth(int damage);
    abstract void setMagic(int cost);

    abstract ArrayList<Integer> getSpells();
}
