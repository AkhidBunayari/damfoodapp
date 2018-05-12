package online.damfood.damfood.model;

import com.google.gson.annotations.SerializedName;

public class RestoAll {

    @SerializedName("id")
    private Integer id;

    @SerializedName("nama")

    private String nama;
    @SerializedName("username")

    private String username;
    @SerializedName("foto")

    private String foto;
    @SerializedName("nama_resto")

    private String namaResto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNamaResto() {
        return namaResto;
    }

    public void setNamaResto(String namaResto) {
        this.namaResto = namaResto;
    }

}