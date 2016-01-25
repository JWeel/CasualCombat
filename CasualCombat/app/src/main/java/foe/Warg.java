// UNIVERSITEIT VAN AMSTERDAM - MINOR PROGRAMMEREN - PROGRAMMEERPROJECT
// CasualCombat - created by Joseph Weel, 10321624, josefweel@gmail.com

package foe;

import java.util.HashSet;

// fast foe that does not drop gold
public class Warg extends Foe {

    public Warg(){
        this.maxHealth = 2;
        this.currentHealth = maxHealth;
        this.maxMagic = 0;
        this.currentMagic = maxMagic;
        this.strength = 3;
        this.willpower = 0;
        this.defense = 0;
        this.resistance = 0;
        this.speed = 6;
        this.name = "Warg";
        this.money = 0;
        this.spells = new HashSet<>();

        this.willDefend = false;
        this.color = "#777711";
        this.id = Foe.WARG;
    }
}
