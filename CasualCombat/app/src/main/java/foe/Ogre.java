package foe;

import java.util.HashSet;

public class Ogre extends Foe {

    public Ogre(){
        this.maxHealth = 8;
        this.currentHealth = maxHealth;
        this.maxMagic = 0;
        this.currentMagic = maxMagic;
        this.strength = 5;
        this.willpower = 0;
        this.defense = 5;
        this.resistance = 2;
        this.speed = 3;
        this.name = "Ogre";
        this.money = 1;
        this.spells = new HashSet<>();

        this.willDefend = true;
        this.color = "#88AA66";
        this.id = Foe.OGRE;
    }
}
