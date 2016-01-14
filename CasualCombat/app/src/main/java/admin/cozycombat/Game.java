package admin.cozycombat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Game {

    ArrayList<Combatant> combatants;

    private PlayerCharacter playerCharacter;
    private ArrayList<Foe> foes;

    private LinkedList<String> log;

    private int roundCount;

    Game(PlayerCharacter pc){

        playerCharacter = pc;

        // TODO base foes on pc level
        foes = new ArrayList<>();
        foes.add(new Foe(Foe.GOBLIN));
        foes.add(new Foe(Foe.GOBLIN));
        foes.add(new Foe(Foe.GOBLIN));
        log = new LinkedList<>();

        roundCount = 0;
    }


    //
    void advance(){

        // check if stuff in log
        if (!log.isEmpty()) return;

        // if combatants list is made then round has started
        if (roundInProgress()) {

            // check for death
            boolean someoneDied = false;
            int i;
            for (i = 0; i < combatants.size(); i++){
                if (combatants.get(i).isDead()){
                    log("" + combatants.get(i).getName() + " is defeated!");
                    someoneDied = true;
                    break;
                }
            }
            if (someoneDied) {
                combatants.remove(i);
                return;
            }

            // list is sorted by speed. first in list is up next
            Combatant c = combatants.get(0);
            Move move = c.getMove();
            if (c.isFoe()) {
                if (move.getDamage() > 0){

                    int damage = move.getDamage();
                    if (move.isSpell()) damage += c.getWillpower();
                    else damage += c.getStrength();

                    int defense;
                    if (move.isSpell()) defense = playerCharacter.getResistance();
                    else defense = playerCharacter.getDefense();

                    damage = (damage - defense);
                    if (damage <= 0) damage = 1;

                    playerCharacter.lowerHealth(damage);
                    log("" + c.getName() + " hits " + playerCharacter.getName() + " for " + damage + "!");
                }
            }
            if (!c.isFoe()) {
                if (move.getDamage() > 0){
                    // TODO if range == 0 then self
                    // TODO if range > 1 then multiple targets
                    Combatant target = foes.get(move.getTarget());

                    int damage = move.getDamage();
                    if (move.isSpell()) damage += c.getWillpower();
                    else damage += c.getStrength();

                    int defense;
                    if (move.isSpell()) defense = target.getResistance();
                    else defense = target.getDefense();

                    damage = (damage - defense);
                    if (damage <= 0) damage = 1;

                    target.lowerHealth(damage);
                    log("" + c.getName() + " hits " + target.getName() + " for " + damage + "!");

                    if (target.isDead()){
                        log("" + target.getName() + " is defeated!");
                    }
                }
            }
            c.setMove(null);
            combatants.remove(0);
            if (combatants.isEmpty()) combatants = null;

        } // otherwise check if round can be started by checking if player has a move
        else if (playerCharacter.isReady()){

            // get moves for all foes
            for (int i = 0; i < foes.size(); i++){
                if (foes.get(i).isDead()) continue;
                // TODO get move from ai
                foes.get(i).setMove(new Move(Move.BASIC_ATTACK));
                foes.get(i).getMove().setTarget(-1);
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

    private void log(String s){
        log.add(s);
    }

    // a round has started if the combatants list is made
    boolean roundInProgress(){
        return combatants != null;
    }

    //
    boolean gameOver(){
        if (playerCharacter.isDead()) return true;
        for (Foe foe : foes) {
            if (!foe.isDead()) return false;
        }
        return true;
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
