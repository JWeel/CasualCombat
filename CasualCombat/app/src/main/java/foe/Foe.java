package foe;

import java.util.ArrayList;

import admin.cozycombat.Combatant;

public abstract class Foe extends Combatant {

    protected String color; // TODO color of button

    public static final int GOBLIN = 0;

    public String getColor(){ return this.color; }

    @Override
    public boolean isFoe(){ return true; }
}
