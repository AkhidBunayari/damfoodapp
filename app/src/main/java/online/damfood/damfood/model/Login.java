package online.damfood.damfood.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login {

    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("nim")
    @Expose
    private Integer nim;
    @SerializedName("foto")
    @Expose
    private String foto;
    @SerializedName("kelas")
    @Expose
    private String kelas;
    @SerializedName("api_key")
    @Expose
    private Object apiKey;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Integer getNim() {
        return nim;
    }

    public void setNim(Integer nim) {
        this.nim = nim;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public Object getApiKey() {
        return apiKey;
    }

    public void setApiKey(Object apiKey) {
        this.apiKey = apiKey;
    }

}