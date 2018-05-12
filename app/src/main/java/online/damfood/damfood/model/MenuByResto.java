package online.damfood.damfood.model;

import com.google.gson.annotations.SerializedName;

public class MenuByResto {

    @SerializedName("id_produk")

    private Integer idProduk;
    @SerializedName("id_penjual")

    private Integer idPenjual;
    @SerializedName("id_kategori")

    private Integer idKategori;
    @SerializedName("nama")

    private String nama;
    @SerializedName("foto")

    private String foto;
    @SerializedName("deskripsi")

    private String deskripsi;
    @SerializedName("harga")
    private String harga;

    @SerializedName("stok")
    private String stok;


    public Integer getIdProduk() {
        return idProduk;
    }

    public void setIdProduk(Integer idProduk) {
        this.idProduk = idProduk;
    }

    public Integer getIdPenjual() {
        return idPenjual;
    }

    public void setIdPenjual(Integer idPenjual) {
        this.idPenjual = idPenjual;
    }

    public Integer getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(Integer idKategori) {
        this.idKategori = idKategori;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getStok() {
        return stok;
    }

    public void setStok(String stok) {
        this.stok = stok;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}
