// UNIVERSITEIT VAN AMSTERDAM - MINOR PROGRAMMEREN - PROGRAMMEERPROJECT
// CasualCombat - created by Joseph Weel, 10321624, josefweel@gmail.com

package foe;

import java.util.HashSet;

// basic enemy
public class Goblin extends Foe {

    public Goblin(){
        this.maxHealth = 3;
        this.currentHealth = maxHealth;
        this.maxMagic = 0;
        this.currentMagic = maxMagic;
        this.strength = 2;
        this.willpower = 0;
        this.defense = 2;
        this.resistance = 0;
        this.speed = 4;
        this.name = "Goblin";
        this.money = 1;
        this.spells = new HashSet<>();

        this.willDefend = true;
        this.color = "#125634";
        this.id = Foe.GOBLIN;
    }
}
