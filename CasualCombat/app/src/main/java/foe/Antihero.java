package foe;

import java.util.HashSet;

import move.Move;

public class Antihero extends Foe {

    public Antihero(){
        this.maxHealth = 100;
        this.currentHealth = maxHealth;
        this.maxMagic = 50;
        this.currentMagic = maxMagic;
        this.strength = 15;
        this.willpower = 10;
        this.defense = 10;
        this.resistance = 10;
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
