package admin.cozycombat;

import android.support.annotation.NonNull;

import java.util.ArrayList;

public abstract class Combatant implements Comparable<Combatant> {
    abstract int getMaxHealth();
    abstract int getMaxMagic();
    abstract int getHealth();
    abstract int getMagic();
    abstract int getStrength();
    abstract int getDefense();
    abstract int getSpeed();
    abstract int getWillpower();
    abstract int getResistance();
    abstract int getLevel();
    abstract int getMoney();
    abstract String getName();

    abstract void lowerHealth(int damage);
    abstract void lowerMagic(int cost);

    boolean isDead() { return getHealth() == 0; }

    abstract Move getMove();
    abstract void setMove(Move move);

    abstract boolean isFoe();

    abstract ArrayList<Integer> getSpells();

    // sort in descending order (highest first)
    @Override
    public int compareTo(@NonNull Combatant c){
        return c.getSpeed() - getSpeed();
    }
}
