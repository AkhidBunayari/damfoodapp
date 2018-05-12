package online.damfood.damfood.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderDetail {

    @SerializedName("id_produk")
    @Expose
    private Integer idProduk;
    @SerializedName("qty")
    @Expose
    private Integer qty;

    public Integer getIdProduk() {
        return idProduk;
    }

    public void setIdProduk(Integer idProduk) {
        this.idProduk = idProduk;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

}