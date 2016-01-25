package foe;

import java.util.HashSet;

import move.Move;

public class Antihero extends Foe {

    public Antihero(){
        this.maxHealth = 100;
        this.currentHealth = maxHealth;
        this.maxMagic = 50;
        this.currentMagic = maxMagic;
        this.strength = 11;
        this.willpower = 7;
        this.defense = 6;
        this.resistance = 5;
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
