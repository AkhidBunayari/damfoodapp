package online.damfood.damfood.model;

import com.google.gson.annotations.SerializedName;

public class Menus {

    @SerializedName("menuByResto")

    private MenuByResto[] menuByResto = null;

    public MenuByResto[] getMenuByResto() {
        return menuByResto;
    }

    public void setMenuByResto(MenuByResto[] menuByResto) {
        this.menuByResto = menuByResto;
    }

}
