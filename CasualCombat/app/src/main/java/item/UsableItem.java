// UNIVERSITEIT VAN AMSTERDAM - MINOR PROGRAMMEREN - PROGRAMMEERPROJECT
// CasualCombat - created by Joseph Weel, 10321624, josefweel@gmail.com

package item;

import move.Move;

// usable items can be used by player in combat, at which point they will perform a Move
public abstract class UsableItem extends Item {

    protected Move itemMove;
    protected String info;

    public Move getItemMove() { return this.itemMove; }

    @Override
    public String getInfo() { return this.info; }
}
