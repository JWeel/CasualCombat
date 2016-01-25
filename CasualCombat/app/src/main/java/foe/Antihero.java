// UNIVERSITEIT VAN AMSTERDAM - MINOR PROGRAMMEREN - PROGRAMMEERPROJECT
// CasualCombat - created by Joseph Weel, 10321624, josefweel@gmail.com

package foe;

import java.util.HashSet;

import move.Move;

// final boss, though game can be continued after defeating it
public class Antihero extends Foe {

    public Antihero(){
        this.maxHealth = 100;
        this.currentHealth = maxHealth;
        this.maxMagic = 50;
        this.currentMagic = maxMagic;
        this.strength = 11;
        this.willpower = 7;
        this.defense = 8;
        this.resistance = 6;
        this.speed = 10;
        this.name = "Antihero";
        this.money = 1;
        this.spells = new HashSet<>();
        spells.add(Move.ARCANE_BLAST);
        spells.add(Move.HEAL);

        this.willDefend = true;
        this.color = "#55AAFF";
        this.id = Foe.ANTIHERO;
    }
}
