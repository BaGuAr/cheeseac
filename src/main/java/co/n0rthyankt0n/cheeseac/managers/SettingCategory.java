package co.n0rthyankt0n.cheeseac.managers;

import org.bukkit.inventory.Inventory;

public class SettingCategory {

    private String category;
    private Inventory inv;

    public SettingCategory(Inventory inventory,String category){
        this.inv = inventory;
        this.category = category;
    }

    public String getCategory(){
        return category;
    }

    public Inventory getInv(){
        return inv;
    }
}
