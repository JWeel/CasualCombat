// UNIVERSITEIT VAN AMSTERDAM - MINOR PROGRAMMEREN - PROGRAMMEERPROJECT
// CasualCombat - created by Joseph Weel, 10321624, josefweel@gmail.com

package nl.mprog.casualcombat;

import android.support.annotation.NonNull;

import java.util.HashSet;

import move.Move;

public abstract class Combatant implements Comparable<Combatant> {

    protected int maxHealth;
    protected int maxMagic;
    protected int currentHealth;
    protected int currentMagic;
    protected int strength;
    protected int willpower;
    protected int defense;
    protected int resistance;
    protected int speed;
    protected int money;
    protected String name;
    protected Move move;
    protected HashSet<Integer> spells;

    public int getMaxHealth() { return this.maxHealth; }
    public int getMaxMagic() { return this.maxMagic; }
    public int getHealth() { return this.currentHealth; }
    public int getMagic() { return this.currentMagic; }
    public int getStrength() { return this.strength; }
    public int getWillpower() { return this.willpower; }
    public int getDefense() { return this.defense; }
    public int getResistance() { return this.resistance; }
    public int getSpeed() { return this.speed; }
    public int getMoney() { return this.money; }
    public String getName() { return this.name; }
    public Move getMove() { return this.move; }
    public HashSet<Integer> getSpells() { return this.spells; }

    public abstract boolean isFoe();

    public void modifyHealth(int damage){
        this.currentHealth -= damage;
        if (this.currentHealth < 0) this.currentHealth = 0;
        if (this.currentHealth > this.maxHealth) this.currentHealth = this.maxHealth;
    }
    public void modifyMagic(int cost){
        this.currentMagic -= cost;
        if (this.currentMagic < 0) this.currentMagic = 0;
        if (this.currentMagic > this.maxMagic) this.currentMagic = this.maxMagic;
    }
    public void restoreHealthFully(){
        this.currentHealth = this.maxHealth;
    }
    public void restoreMagicFully(){
        this.currentMagic = this.maxMagic;
    }

    public void setName(String s){
        this.name = s;
    }
    public void setMove(Move m){ this.move = m; }

    public boolean isDead() { return this.currentHealth == 0; }
    public boolean isDefending(){ return this.move.getId() == Move.BASIC_DEFEND; }

    // sort in descending order (highest first)
    @Override
    public int compareTo(@NonNull Combatant c){
        return c.getSpeed() - this.getSpeed();
    }
}
