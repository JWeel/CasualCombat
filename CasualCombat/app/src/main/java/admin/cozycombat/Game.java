package admin.cozycombat;

import java.util.ArrayList;
import java.util.Collections;

public class Game {

    ArrayList<Combatant> combatants;

    private PlayerCharacter playerCharacter;
    private ArrayList<Foe> foes;

    private String currentMessage;

    private int roundCount;

    private Move currentMove;

    Game(PlayerCharacter pc){

        playerCharacter = pc;

        // TODO base foes on pc level
        foes = new ArrayList<>();
        foes.add(new Foe(Foe.GOBLIN));
        //foes.add(new Foe(Foe.GOBLIN));
        //foes.add(new Foe(Foe.GOBLIN));
        currentMessage = "";

        roundCount = 0;
    }

    //
    // log PlayPage log Game
    //  a            a uses m on b
    //  b            m does x damage
    //  c            b dies!
    //  d            c uses m on a
    //
    //
    // maybe in log keep track of turn and event
    // so 1.1 a does b! is turn 1 event 1
    // 3.6 c dies! would be turn 3 event 6
    // two ints,
    // could also do it with timestamps
    // either using current time or by keep track of how long battle takes, and printing that
    //
    //
    // perhaps have Game have just one message always
    // using logClick gets that message (if any, if not it just lowers all messages, or hides
    // the press to continue textview) and adds it to linkedlist




    //
    // TODO alternative:
    // advance checks how game currently is and then proceeds based on that
    // so if there is a combatants list, then it does ONE move for the first combatant in the list
    // it removes a combatant once they have done their move
    // if there is no combatants list, but the playercharacter has a move,
    // then it creates the combatants, sorts, and does the first move
    // another alternative is that before the "hits for x damage", a log that says <<<<<  tho that is cool, it requires an extra boolean to be made. easier to do it immediatley
    // a attacks b! or a uses y on b! , then the next log sees the damage be dealt and the textview updated
    // so then also a check before combatants move to see if combatant dies, in which case log it and remove from list
    void advance(){
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
                    playerCharacter.lowerHealth(move.getDamage());
                    log("" + c.getName() + " hits " + playerCharacter.getName() + " for " + move.getDamage() + "!");
                }
            }
            if (!c.isFoe()) {
                if (move.getDamage() > 0){
                    // TODO if range == 0 then self
                    // TODO if range > 1 then multiple targets
                    Combatant target = foes.get(move.getTarget());
                    target.lowerHealth(move.getDamage());
                    log("" + c.getName() + " hits " + target.getName() + " for " + move.getDamage() + "!");

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
        currentMessage = s;
    }

    // a round has started if the combatants list is made
    boolean roundInProgress(){
        return combatants != null;
    }

    void handleMove(){

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
        String poppedMessage = currentMessage;
        currentMessage = "";
        return poppedMessage;
    }

    //
    PlayerCharacter getPlayerCharacter(){
        return playerCharacter;
    }

    ArrayList<Foe> getFoes(){
        return foes;
    }
}
