package online.damfood.damfood.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Orderan {
    @SerializedName("error")
    @Expose
    private Boolean error;


    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }





}