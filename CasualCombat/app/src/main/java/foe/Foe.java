package foe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import admin.cozycombat.Combatant;

public abstract class Foe extends Combatant {

    protected String color; // TODO color of button

    public static final int GOBLIN = 0;

    protected int id;

    public static Foe findFoeByID(int id){
        switch(id){
            case GOBLIN: return new Goblin();
            default: return null;
        }
    }
    public String getColor(){ return this.color; }

    public int getId(){ return this.id; }

    // changes names of foes of which there are more than one
    public static void renameFoesByCount(ArrayList<Foe> foes){
        Map<String, Integer> foeTypes = new HashMap<>();
        for (Foe foe : foes) {
            int count;
            if (foeTypes.containsKey(foe.getName())) {
                count = foeTypes.get(foe.getName()) + 1;
                foeTypes.put(foe.getName(), count);
            } else {
                count = 1;
                foeTypes.put(foe.getName(), count);
            }
            foe.setName(foe.getName() + " " + count);
        }
        // foe types that occur only once don't need a number
        for (Foe foe : foes) {
            String originalName = foe.getName().substring(0, foe.getName().length()-2);
            if (foeTypes.get(originalName) == 1) foe.setName(originalName);
        }
    }

    @Override
    public boolean isFoe(){ return true; }
}
