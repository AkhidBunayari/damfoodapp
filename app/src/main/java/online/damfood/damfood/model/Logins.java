package online.damfood.damfood.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Logins {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("Mahasiswa")
    @Expose
    private Logins mahasiswa;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public Logins getMahasiswa() {
        return mahasiswa;
    }

    public void setMahasiswa(Logins mahasiswa) {
        this.mahasiswa = mahasiswa;
    }

}