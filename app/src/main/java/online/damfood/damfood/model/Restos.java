package online.damfood.damfood.model;

import com.google.gson.annotations.SerializedName;

public class Restos {

    @SerializedName("restoAll")

    private RestoAll[] restoAll = null;

    public RestoAll[] getRestoAll() {
        return restoAll;
    }

    public void setRestoAll(RestoAll[] restoAll) {
        this.restoAll = restoAll;
    }
}