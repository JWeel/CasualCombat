package admin.cozycombat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

import foe.Foe;
import move.Move;

class Game {

    private static final int AWARDED_LEVEL_POINTS = 1;

    private ArrayList<Combatant> combatants;

    private PlayerCharacter playerCharacter;
    private ArrayList<Foe> foes;

    private LinkedList<String> log;

    private int roundCount;

    Game(PlayerCharacter pc){

        playerCharacter = pc;

        foes = getEncounter();
        Foe.renameFoesByCount(foes);


        log = new LinkedList<>();
        roundCount = 0;
    }

    //
    private ArrayList<Foe> getEncounter(){

        ArrayList<ArrayList<Integer>> possibleEncounters = new ArrayList<>();
        ArrayList<Integer> encounter;

        switch(playerCharacter.getLevel()){
            case 0:
                encounter = new ArrayList<>();
                encounter.add(Foe.GOBLIN);
                possibleEncounters.add(encounter);
                break;
            case 1: case 2: case 3: case 4:
                encounter = new ArrayList<>();
                encounter.add(Foe.WARG);
                possibleEncounters.add(encounter);

                encounter = new ArrayList<>();
                encounter.add(Foe.WARG);
                encounter.add(Foe.WARG);
                possibleEncounters.add(encounter);

                encounter = new ArrayList<>();
                encounter.add(Foe.GOBLIN);
                encounter.add(Foe.GOBLIN);
                possibleEncounters.add(encounter);

                encounter = new ArrayList<>();
                encounter.add(Foe.WARG);
                encounter.add(Foe.GOBLIN);
                possibleEncounters.add(encounter);

                encounter = new ArrayList<>();
                encounter.add(Foe.WARLOCK);
                possibleEncounters.add(encounter);
                break;
            case 5: case 6: case 7: case 8:
                encounter = new ArrayList<>();
                encounter.add(Foe.WARG);
                encounter.add(Foe.WARG);
                encounter.add(Foe.WARG);
                possibleEncounters.add(encounter);

                encounter = new ArrayList<>();
                encounter.add(Foe.GOBLIN);
                encounter.add(Foe.GOBLIN);
                encounter.add(Foe.GOBLIN);
                possibleEncounters.add(encounter);

                encounter = new ArrayList<>();
                encounter.add(Foe.WARG);
                encounter.add(Foe.GOBLIN);
                encounter.add(Foe.WARG);
                possibleEncounters.add(encounter);

                encounter = new ArrayList<>();
                encounter.add(Foe.WARLOCK);
                encounter.add(Foe.WARLOCK);
                possibleEncounters.add(encounter);

                encounter = new ArrayList<>();
                encounter.add(Foe.WARG);
                encounter.add(Foe.ORC);
                encounter.add(Foe.WARG);
                possibleEncounters.add(encounter);

                encounter = new ArrayList<>();
                encounter.add(Foe.ORC);
                encounter.add(Foe.ORC);
                possibleEncounters.add(encounter);
                break;
            case 9:
                encounter = new ArrayList<>();
                encounter.add(Foe.KRAKEN);
                possibleEncounters.add(encounter);
                break;
            case 10: case 11: case 12: case 13: case 14:
                encounter = new ArrayList<>();
                encounter.add(Foe.WARG);
                encounter.add(Foe.WARG);
                encounter.add(Foe.WARG);
                encounter.add(Foe.WARG);
                encounter.add(Foe.WARG);
                encounter.add(Foe.WARG);
                possibleEncounters.add(encounter);

                encounter = new ArrayList<>();
                encounter.add(Foe.WARLOCK);
                encounter.add(Foe.WARLOCK);
                encounter.add(Foe.WARLOCK);
                encounter.add(Foe.WARLOCK);
                possibleEncounters.add(encounter);

                encounter = new ArrayList<>();
                encounter.add(Foe.WARG);
                encounter.add(Foe.ORC);
                encounter.add(Foe.ORC);
                encounter.add(Foe.ORC);
                encounter.add(Foe.WARG);
                possibleEncounters.add(encounter);

                encounter = new ArrayList<>();
                encounter.add(Foe.ORC);
                encounter.add(Foe.ORC);
                encounter.add(Foe.ORC);
                encounter.add(Foe.ORC);
                possibleEncounters.add(encounter);

                encounter = new ArrayList<>();
                encounter.add(Foe.GOBLIN);
                encounter.add(Foe.GOBLIN);
                encounter.add(Foe.OGRE);
                encounter.add(Foe.GOBLIN);
                encounter.add(Foe.GOBLIN);
                possibleEncounters.add(encounter);

                encounter = new ArrayList<>();
                encounter.add(Foe.KRAKEN);
                possibleEncounters.add(encounter);
                break;
            case 15: case 16: case 17: case 18:
                encounter = new ArrayList<>();
                encounter.add(Foe.OGRE);
                encounter.add(Foe.OGRE);
                possibleEncounters.add(encounter);

                encounter = new ArrayList<>();
                encounter.add(Foe.DARK_MAGE);
                encounter.add(Foe.DARK_MAGE);
                possibleEncounters.add(encounter);

                encounter = new ArrayList<>();
                encounter.add(Foe.SPIDERBAT);
                encounter.add(Foe.SPIDERBAT);
                possibleEncounters.add(encounter);

                encounter = new ArrayList<>();
                encounter.add(Foe.SPIDERBAT);
                encounter.add(Foe.OGRE);
                possibleEncounters.add(encounter);

                encounter = new ArrayList<>();
                encounter.add(Foe.SPIDERBAT);
                encounter.add(Foe.DARK_MAGE);
                possibleEncounters.add(encounter);
                break;
            case 19:
                encounter = new ArrayList<>();
                encounter.add(Foe.DRAGON);
                possibleEncounters.add(encounter);
                break;
            default:
                encounter = new ArrayList<>();
                encounter.add(Foe.OGRE);
                encounter.add(Foe.OGRE);
                encounter.add(Foe.OGRE);
                possibleEncounters.add(encounter);

                encounter = new ArrayList<>();
                encounter.add(Foe.ORC);
                encounter.add(Foe.ORC);
                encounter.add(Foe.ORC);
                encounter.add(Foe.ORC);
                encounter.add(Foe.ORC);
                encounter.add(Foe.ORC);
                possibleEncounters.add(encounter);

                encounter = new ArrayList<>();
                encounter.add(Foe.DARK_MAGE);
                encounter.add(Foe.DARK_MAGE);
                encounter.add(Foe.DARK_MAGE);
                encounter.add(Foe.DARK_MAGE);
                possibleEncounters.add(encounter);

                encounter = new ArrayList<>();
                encounter.add(Foe.SPIDERBAT);
                encounter.add(Foe.SPIDERBAT);
                encounter.add(Foe.SPIDERBAT);
                encounter.add(Foe.SPIDERBAT);
                possibleEncounters.add(encounter);

                encounter = new ArrayList<>();
                encounter.add(Foe.SPIDERBAT);
                encounter.add(Foe.ORC);
                encounter.add(Foe.OGRE);
                encounter.add(Foe.ORC);
                encounter.add(Foe.SPIDERBAT);
                possibleEncounters.add(encounter);

                encounter = new ArrayList<>();
                encounter.add(Foe.WARLOCK);
                encounter.add(Foe.DARK_MAGE);
                encounter.add(Foe.WARLOCK);
                encounter.add(Foe.DARK_MAGE);
                encounter.add(Foe.WARLOCK);
                possibleEncounters.add(encounter);

                encounter = new ArrayList<>();
                encounter.add(Foe.KRAKEN);
                encounter.add(Foe.KRAKEN);
                possibleEncounters.add(encounter);

                encounter = new ArrayList<>();
                encounter.add(Foe.DRAGON);
                possibleEncounters.add(encounter);
        }
        ArrayList<Integer> randomEncounter = possibleEncounters.get(TitlePage.random.nextInt(possibleEncounters.size()));
        ArrayList<Foe> encounterFoes = new ArrayList<>();
        for (int i = 0; i < randomEncounter.size(); i++) encounterFoes.add(Foe.findFoeByID(randomEncounter.get(i)));
        return encounterFoes;
    }


    //
    void advance(){
        // check if stuff in log
        if (!log.isEmpty()) return;

        // if combatants list is made then round has started
        if (roundInProgress()) {

//            // TODO maybe this is unnecessary ?
//            // check for death
//            for (int i = 0; i < combatants.size(); i++){
//                if (combatants.get(i).isDead()){
//                    log("" + combatants.get(i).getName() + " is defeated!");
//                    combatants.remove(i);
//                    return;
//                }
//            }

            // list is sorted by speed. first in list is up next
            Combatant attacker = combatants.get(0);
            if (attacker.getMove().getCost() != 0) attacker.modifyMagic(attacker.getMove().getCost());
            if (attacker.isFoe()) {
                if (!attacker.isDefending()){

                    if (attacker.getMove().getRange() == Move.RANGE_SELF){
                        // SELF
                        int healAmount = attacker.getMove().getDamage();
                        if (healAmount != 0) {
                            attacker.modifyHealth(healAmount);
                            log(attacker.getName() + " casts " + attacker.getMove().getName() + " and heals for " + Math.abs(healAmount) + "!");
                        }
                    } else {
                        // any other range will only hit playerCharacter, so further range check is unnecessary
                        damage(attacker, playerCharacter, 1.0f);
                    }
                } else {
                    log(attacker.getName() + " is defending!");
                }
            }
            // player characters have different mechanics because they can hit multiple foes
            if (!attacker.isFoe()) {
                if (!attacker.isDefending()){

                    if (attacker.getMove().isItemMove()){
                        ((PlayerCharacter) attacker).getUsableItems().remove((Integer) attacker.getMove().getId());
                    }

                    // moves can affect: self, 1 target, 3 targets (close), or 5 targets (far)
                    if (attacker.getMove().getRange() == Move.RANGE_SELF) {
                        // SELF
                        int healAmount = attacker.getMove().getDamage();
                        if (healAmount != 0) {
                            attacker.modifyHealth(healAmount);
                            log(attacker.getName() + " casts " + attacker.getMove().getName() + " and heals for " + Math.abs(healAmount) + "!");
                        }
                        // if negative cost, that means magic is being restored
                        if (attacker.getMove().getCost() < 0)
                            log(attacker.getName() + " uses " + attacker.getMove().getName() + " and restores " + Math.abs(attacker.getMove().getCost()) + " magic!");

                    } else if (attacker.getMove().getRange() == Move.RANGE_SINGLE || foes.size() == 1) {

                        Combatant target = foes.get(attacker.getMove().getTarget());
                        damage(attacker, target, 1.0f);

                    // multiple targets
                    } else {

                        int target = attacker.getMove().getTarget();
                        damage(attacker, foes.get(target), 1.0f);
                        if (target > 0){
                            if (!foes.get(target - 1).isDead()) damage(attacker, foes.get(target-1), 0.75f);
                            if (attacker.getMove().getRange() == Move.RANGE_FAR && target > 1 && !foes.get(target - 2).isDead()) damage(attacker, foes.get(target-2), 0.5f);
                        }
                        if (target < foes.size()-1) {
                            if (!foes.get(target + 1).isDead()) damage(attacker, foes.get(target+1), 0.75f);
                            if (attacker.getMove().getRange() == Move.RANGE_FAR && target < foes.size()-2 && !foes.get(target+2).isDead()) damage(attacker, foes.get(target+2), 0.5f);
                        }
                    }
                } else {
                    log(attacker.getName() + " is defending!");
                }
            }
            combatants.remove(0);
            if (combatants.isEmpty()) {
                combatants = null;
                playerCharacter.setMove(null);
            }

            //
            if (!playerCharacter.isDead() && foesDefeated()){
                int loot = 0;
                for (Foe foe : foes) loot += foe.getMoney();
                playerCharacter.addMoney(loot);
                log(playerCharacter.getName() + " found " + loot + " coins!");

                playerCharacter.addLevel();
                playerCharacter.addLevelPoints(AWARDED_LEVEL_POINTS);
                log(playerCharacter.getName() + " received " + AWARDED_LEVEL_POINTS + " level up point!");
                log(" ");
                System.err.println("LEVEL POINTS NOW " + playerCharacter.getLevelPoints());
            }

        } // otherwise check if round can be started by checking if player has a move
        else if (playerCharacter.isReady()){

            // get moves for all foes
            for (int i = 0; i < foes.size(); i++){
                if (foes.get(i).isDead()) continue;
                foes.get(i).updateUsableMoveIds();
                foes.get(i).setRandomMove();
            }
            // sort combatants by speed
            combatants = new ArrayList<>();
            combatants.add(playerCharacter);
            for (Foe foe : foes) {
                if (!foe.isDead()) combatants.add(foe);
            }
            Collections.sort(combatants);
            roundCount++;
            log("Round " + roundCount + " begins.");
        }
    }

    //
    private void damage(Combatant attacker, Combatant defender, float distanceModifier){
        int damage = attacker.getMove().getDamage();
        if (attacker.getMove().isSpell()) damage += attacker.getWillpower();
        else if (!attacker.getMove().isItemMove()) damage += attacker.getStrength();

        damage = (int) (((float) damage) * distanceModifier);

        int defense = 0;
        if (attacker.getMove().isSpell()) defense = defender.getResistance();
        else if (!attacker.getMove().isItemMove()) defense += defender.getDefense();

        if (defender.isDefending()) defense *= 2;

        damage = (damage - defense);
        if (damage <= 0) damage = 1;

        defender.modifyHealth(damage);
        if (attacker.getMove().isSpell() || attacker.getMove().isItemMove()) log(attacker.getName() + "'s " + attacker.getMove().getName() + " hits " + defender.getName() + " for " + damage + "!");
        else log(attacker.getName() + " attacks and hits " + defender.getName() + " for " + damage + "!");

        if (defender.isDead()) {
            log(defender.getName() + " is defeated!");
            combatants.remove(defender);
            if (!defender.isFoe()) log(" ");
        }
    }

    //
    void pickMove(Move m){
        playerCharacter.setMove(m);
    }

    //
    void pickTarget(int t) { playerCharacter.getMove().setTarget(t); }

    private void log(String s){
        log.add(s);
    }

    // a round has started if the combatants list is made
    boolean roundInProgress(){
        return combatants != null || !log.isEmpty();
    }

    //
    boolean foesDefeated(){
        for (Foe foe : foes) if (!foe.isDead()) return false;
        return true;
    }

    //
    boolean gameOver(){
        if (!log.isEmpty()) return false;
        if (playerCharacter.isDead()) return true;
        return foesDefeated();
    }

    // pops the current log message
    String pop() {
        if (log.isEmpty()) return "";
        else return log.removeFirst();
    }

    //
    PlayerCharacter getPlayerCharacter(){
        return playerCharacter;
    }

    ArrayList<Foe> getFoes(){
        return foes;
    }
}
