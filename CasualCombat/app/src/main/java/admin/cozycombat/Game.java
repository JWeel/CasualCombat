package admin.cozycombat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Game {

    ArrayList<Combatant> combatants;

    private PlayerCharacter playerCharacter;
    private ArrayList<Foe> foes;
    private LinkedList<String> log;

    private int playerTarget;
    private Move currentMove;

    Game(PlayerCharacter pc){

        playerCharacter = pc;

        // TODO base foes on pc level
        foes = new ArrayList<>();
        foes.add(new Foe(Foe.GOBLIN));
        foes.add(new Foe(Foe.GOBLIN));
        //foes.add(new Foe(Foe.GOBLIN));

        log = new LinkedList<>();
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
    // whatever the way, the log in game should not be popped unless size is over some X (8?)
    // PlayPage pops also whenever it is over 8
    // but PlayPage should be pressable whenever, removing former messages.
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
    // another alternative is that before the "hits for x damage", a log that says
    // a attacks b! or a uses y on b! , then the next log sees the damage be dealt and the textview updated
    // so then also a check before combatants move to see if combatant dies, in which case log it and remove from list
    void advance(){

        // TODO make way of checking if can advance prettier
        if (playerCharacter.getMove() == null) return;

        // get moves for all foes
        for (int i = 0; i < foes.size(); i++){
            if (foes.get(i).isDead()) continue;
            // TODO get move from ai
            foes.get(i).setMove(new Move(Move.BASIC_ATTACK));
            foes.get(i).getMove().setTarget(-1);
        }
        // sort combatants by speed
        ArrayList<Combatant> combatants = new ArrayList<>();
        combatants.add(playerCharacter);
        for (Foe foe : foes) {
            if (!foe.isDead()) combatants.add(foe);
        }
        System.out.println(combatants);
        Collections.sort(combatants);
        System.out.println(combatants);

        // performs moves
        for (int i = 0; i < combatants.size(); i++){

            // TODO if already dead cancel
            if (combatants.get(i).isDead()) continue;

            Move move = combatants.get(i).getMove();
            if (combatants.get(i).isFoe()) {
                if (move.getDamage() > 0){
                    playerCharacter.lowerHealth(move.getDamage());
                    log.add("" + combatants.get(i).getName() + " hits " + playerCharacter.getName() + " for " + move.getDamage() + "!");
                }
            }
            if (!combatants.get(i).isFoe()) {
                if (move.getDamage() > 0){
                    // TODO if range == 0 then self
                    // TODO if range > 1 then multiple targets
                    Combatant target = foes.get(move.getTarget());
                    target.lowerHealth(move.getDamage());
                    log.add("" + combatants.get(i).getName() + " hits " + target.getName() + " for " + move.getDamage() + "!");

                    if (target.isDead()){
                        log.add("" + target.getName() + " is defeated!");
                    }
                }
            }

            combatants.get(i).setMove(null);
        }
    }

    void handleMove(){

    }

    //
    boolean gameOver(){
        // TODO also requires no messages left to toggle from log
        if (playerCharacter.isDead()) return true;
        for (Foe foe : foes) {
            if (!foe.isDead()) return false;
        }
        return true;
    }

    //
    String pop() {
        System.err.println(log);
        if (!log.isEmpty()) {
            return log.removeFirst();
        } else return "";
    }

    //
    void setPlayerTarget(int target){
        playerTarget = target;
        log.add("target " + playerTarget);
    }

    //
    PlayerCharacter getPlayerCharacter(){
        return playerCharacter;
    }

    ArrayList<Foe> getFoes(){
        return foes;
    }
}
