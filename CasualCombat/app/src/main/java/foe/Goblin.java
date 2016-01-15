package foe;

import java.util.ArrayList;

import admin.cozycombat.Combatant;

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
        this.speed = 6;
        this.name = "GOBLIN";
        this.money = 1;
        this.spells = new ArrayList<Integer>();

        this.color = "#125634";
        this.id = Foe.GOBLIN;
    }
}
