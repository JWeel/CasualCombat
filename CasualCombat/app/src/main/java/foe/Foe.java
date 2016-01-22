package foe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;

import admin.cozycombat.Combatant;
import admin.cozycombat.TitlePage;
import move.Move;

public abstract class Foe extends Combatant {


    public static final int GOBLIN = 0;
    public static final int WARG = 1;
    public static final int ORC = 2;
    public static final int KRAKEN = 3;
    public static final int WARLOCK = 4;
    public static final int DARK_MAGE = 5;
    public static final int OGRE = 6;
    public static final int SPIDERBAT = 7;
    public static final int DRAGON = 8;
    public static final int ANTIHERO = 9;

    protected ArrayList<Integer> usableMoveids;

    protected int id;
    protected String color;

    protected boolean willDefend;
    // TODO and maybe a prefersSpells for the ai to first cast spells until magic is out before resorting to attack/defend

    public static Foe findFoeByID(int id){
        Foe foe;
        switch(id){
            case GOBLIN:
                foe = new Goblin();
                break;
            case WARG:
                foe = new Warg();
                break;
            case ORC:
                foe = new Orc();
                break;
            case KRAKEN:
                foe = new Kraken();
                break;
            case WARLOCK:
                foe = new Warlock();
                break;
            case DARK_MAGE:
                foe = new DarkMage();
                break;
            case OGRE:
                foe = new Ogre();
                break;
            case SPIDERBAT:
                foe = new Ogre();
                break;
            case DRAGON:
                foe = new Dragon();
                break;
            case ANTIHERO:
                foe = new Antihero();
                break;
            default: return null;
        }
        foe.prepareUsableMoveIds();
        return foe;
    }

    protected void prepareUsableMoveIds(){
        this.usableMoveids = new ArrayList<>();
        this.usableMoveids.add(Move.BASIC_ATTACK);
        if (this.willDefend) this.usableMoveids.add(Move.BASIC_DEFEND);
        for (int spellId : this.spells){
            Move spell = Move.findMoveById(spellId);
            if (this.currentMagic >= spell.getCost()) this.usableMoveids.add(spellId);
        }
    }

    public void updateUsableMoveIds(){
        ListIterator<Integer> iter = this.usableMoveids.listIterator();
        while(iter.hasNext()){
            if(Move.findMoveById(iter.next()).getCost() > this.currentMagic) {
                iter.remove();
            }
        }
    }

    public void setRandomMove(){
        int id = this.usableMoveids.get(TitlePage.random.nextInt(this.usableMoveids.size()));
        this.move = Move.findMoveById(id);
        if (this.move.getRange() == Move.RANGE_SELF) this.move.setTarget(Move.TARGET_SELF);
        else this.move.setTarget(0);
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
