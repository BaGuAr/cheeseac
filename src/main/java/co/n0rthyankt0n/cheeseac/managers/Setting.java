package co.n0rthyankt0n.cheeseac.managers;

import org.bukkit.inventory.Inventory;

public class Setting {

    private Inventory inv;
    private Check check;

    public Setting(Inventory inventory,Check check){
        this.inv = inventory;
        this.check = check;
    }

    public Inventory getInv(){
        return inv;
    }

    public Check getCheck(){
        return check;
    }

}
