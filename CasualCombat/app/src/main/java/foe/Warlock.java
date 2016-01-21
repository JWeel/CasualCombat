package foe;

import java.util.HashSet;

import move.Move;

public class Warlock extends Foe {

    public Warlock(){
        this.maxHealth = 2;
        this.currentHealth = maxHealth;
        this.maxMagic = 20;
        this.currentMagic = maxMagic;
        this.strength = 1;
        this.willpower = 3;
        this.defense = 1;
        this.resistance = 3;
        this.speed = 3;
        this.name = "Warlock";
        this.money = 1;
        this.spells = new HashSet<>();
        spells.add(Move.FIREBALL);
        spells.add(Move.SHOCK);

        this.willDefend = false;
        this.color = "#771122";
        this.id = Foe.WARLOCK;
    }
}
