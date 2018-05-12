package online.damfood.damfood.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderBody {
    @SerializedName("nim")
    @Expose
    private String nim;
    @SerializedName("nomor_meja")
    @Expose
    private Integer nomorMeja;
    @SerializedName("id_penjual")
    @Expose
    private Integer id_penjual;
    @SerializedName("keterangan")
    @Expose
    private String keterangan;
    @SerializedName("order_detail")
    @Expose
    private List<OrderDetail> orderDetail = null;

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public Integer getNomorMeja() {
        return nomorMeja;
    }

    public void setNomorMeja(Integer nomorMeja) {
        this.nomorMeja = nomorMeja;
    }

    public Integer getId_penjual() {
        return id_penjual;
    }

    public void setId_penjual(Integer id_penjual) {
        this.id_penjual = id_penjual;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public List<OrderDetail> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderDetail> orderDetail) {
        this.orderDetail = orderDetail;
    }
}
