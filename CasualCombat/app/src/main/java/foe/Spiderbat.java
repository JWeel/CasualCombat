package foe;

import java.util.HashSet;

public class Spiderbat extends Foe {

    public Spiderbat(){
        this.maxHealth = 4;
        this.currentHealth = maxHealth;
        this.maxMagic = 0;
        this.currentMagic = maxMagic;
        this.strength = 5;
        this.willpower = 0;
        this.defense = 2;
        this.resistance = 2;
        this.speed = 9;
        this.name = "Spiderbat";
        this.money = 0;
        this.spells = new HashSet<>();

        this.willDefend = false;
        this.color = "#887788";
        this.id = Foe.SPIDERBAT;
    }
}
