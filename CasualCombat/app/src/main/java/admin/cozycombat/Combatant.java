package admin.cozycombat;

import android.support.annotation.NonNull;

import java.util.ArrayList;

// TODO make foe and pc skills non abstract and put them here, then some identical methods can move here too
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

    abstract void modifyHealth(int damage);
    abstract void modifyMagic(int cost);

    boolean isDead() { return getHealth() == 0; }

    //
    boolean isDefending(){
        return getMove().getId() == Move.BASIC_DEFEND[Move.INDEX_ID];
    }

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
