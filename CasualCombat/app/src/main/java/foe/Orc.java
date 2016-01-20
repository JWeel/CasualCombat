package foe;

import java.util.HashSet;

public class Orc extends Foe {

    public Orc(){
        this.maxHealth = 4;
        this.currentHealth = maxHealth;
        this.maxMagic = 0;
        this.currentMagic = maxMagic;
        this.strength = 3;
        this.willpower = 0;
        this.defense = 2;
        this.resistance = 1;
        this.speed = 3;
        this.name = "Orc";
        this.money = 1;
        this.spells = new HashSet<>();

        this.willDefend = true;
        this.color = "#AA9977";
        this.id = Foe.ORC;
    }
}
