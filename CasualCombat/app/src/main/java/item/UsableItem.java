package item;

import move.Move;

public abstract class UsableItem extends Item {

    protected Move itemMove;
    protected String info;

    public Move getItemMove() { return this.itemMove; }

    @Override
    public String getInfo() { return this.info; }

    @Override
    public boolean isEquippable() { return false; }
}
