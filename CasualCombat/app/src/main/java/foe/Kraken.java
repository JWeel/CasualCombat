// UNIVERSITEIT VAN AMSTERDAM - MINOR PROGRAMMEREN - PROGRAMMEERPROJECT
// CasualCombat - created by Joseph Weel, 10321624, josefweel@gmail.com

package foe;

import java.util.HashSet;

import move.Move;

// first boss, appears later as random foe
public class Kraken extends Foe {

    public Kraken(){
        this.maxHealth = 12;
        this.currentHealth = maxHealth;
        this.maxMagic = 6;
        this.currentMagic = maxMagic;
        this.strength = 5;
        this.willpower = 1;
        this.defense = 4;
        this.resistance = 1;
        this.speed = 2;
        this.name = "Kraken";
        this.money = 3;
        this.spells = new HashSet<>();
        spells.add(Move.SHOCK);

        this.willDefend = false;
        this.color = "#002288";
        this.id = Foe.KRAKEN;
    }
}
