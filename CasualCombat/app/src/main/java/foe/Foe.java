// UNIVERSITEIT VAN AMSTERDAM - MINOR PROGRAMMEREN - PROGRAMMEERPROJECT
// CasualCombat - created by Joseph Weel, 10321624, josefweel@gmail.com

package foe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;

import nl.mprog.casualcombat.Combatant;
import nl.mprog.casualcombat.TitlePage;
import move.Move;

// foes are the enemies of the player in combat. predefined subclasses have their own stats and moves
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

    // if this is flagged true, the foe will sometimes use the basic defend move in combat
    protected boolean willDefend;

    // returns an instance of the corresponding foe. when adding new foes, also add them here
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
                foe = new Spiderbat();
                break;
            case DRAGON:
                foe = new Dragon();
                break;
            case ANTIHERO:
                foe = new Antihero();
                break;
            default: return null;
        }
        // setup the foe's usable moves
        foe.prepareUsableMoveIds();
        return foe;
    }

    // creates a list of usable moves, based on variables in the subclass of foe
    protected void prepareUsableMoveIds(){
        this.usableMoveids = new ArrayList<>();
        this.usableMoveids.add(Move.BASIC_ATTACK);
        if (this.willDefend) this.usableMoveids.add(Move.BASIC_DEFEND);

        // spells defined in the subclass are added if the foe has enough magic
        for (int spellId : this.spells){
            Move spell = Move.findMoveById(spellId);
            if (this.currentMagic >= spell.getCost()) this.usableMoveids.add(spellId);
        }
    }

    // removes moves from the foe's usable move list that cost more magic than they have left
    public void updateUsableMoveIds(){
        ListIterator<Integer> iter = this.usableMoveids.listIterator();
        while(iter.hasNext()){
            if(Move.findMoveById(iter.next()).getCost() > this.currentMagic) {
                iter.remove();
            }
        }
    }

    // picks a random move from the usable move id list and sets it
    public void setRandomMove(){
        int id = this.usableMoveids.get(TitlePage.random.nextInt(this.usableMoveids.size()));
        this.move = Move.findMoveById(id);
        if (this.move.getRange() == Move.RANGE_SELF) this.move.setTarget(Move.TARGET_SELF);
        // for non-self, as long as target is not TARGET_SELF or TARGET_NONE, any int will work because there is only one possible target
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
