package online.damfood.damfood.checkout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import online.damfood.damfood.R;
import online.damfood.damfood.adapter.CheckoutAdapter;
import online.damfood.damfood.model.OrderBody;
import online.damfood.damfood.model.OrderDetail;
import online.damfood.damfood.model.Orderan;
import online.damfood.damfood.network.NetworkService;
import online.damfood.damfood.network.RestApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Checkout extends AppCompatActivity implements View.OnClickListener {
    List<String> qty = new ArrayList<>();
    List<String> harga = new ArrayList<>();
    List<String> nama_menu = new ArrayList<>();
    List<String> qty_bersih = new ArrayList<>();
    List<String> harga_bersih = new ArrayList<>();
    List<String> nama_menu_bersih = new ArrayList<>();
    List<Integer> id_menu_bersih = new ArrayList<>();
    ArrayList<OrderDetail> order_detail = new ArrayList<>();
    List<Integer> id_menu = new ArrayList<>();
    String nim,newString,id_penjual;
    private int size,total_hargane;
    private CheckoutAdapter adaptermenu;
    TextView tv7,tv_total;
    EditText et_meja,et_keterangan;
    LinearLayout ll_checkout;

    RecyclerView rv_checkout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        rv_checkout = (RecyclerView) findViewById(R.id.recyclerViewCheckout);
        tv7 = (TextView) findViewById(R.id.textView7) ;
        tv_total = (TextView) findViewById(R.id.tv_total) ;
        et_meja = (EditText) findViewById(R.id.et_meja);
        et_keterangan = (EditText) findViewById(R.id.et_keterangan);
        ll_checkout = (LinearLayout) findViewById(R.id.ll_checkout);
        //njupuk nim seko shared
        SharedPreferences prefs = getSharedPreferences("login", MODE_PRIVATE);
        nim = prefs.getString("nim", "No name defined");
        if (nim != null) {
            nim = prefs.getString("nim", "No name defined");//"No name defined" is the default value.
            //Toast.makeText(Checkout.this," "+nim ,Toast.LENGTH_SHORT).show();
        }
        // rampung
        //njumuk data seko intetn menuactivity
        if (savedInstanceState == null) {

            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                newString = null;
            } else {
                Intent intent = getIntent();
                Bundle extra = intent.getBundleExtra("extra");
                nama_menu = (List<String>)
                        extra.getSerializable("nama_menu");
                qty = (List<String>)
                        extra.getSerializable("qty");
                harga = (List<String>)
                        extra.getSerializable("harga");
                id_menu = (List<Integer>)
                        extra.getSerializable("id_menu");

                id_penjual = extra.getString("idne");
                setItemCount(nama_menu.size());
            }
        }
        sortir_menu();
        tv_total.setText("Rp. "+Integer.toString(getTotal_harga()));
        //Toast.makeText(Checkout.this,""+nama_menu+""+qty+""+harga ,Toast.LENGTH_SHORT).show();

        adaptermenu = new CheckoutAdapter(nama_menu_bersih,harga_bersih,qty_bersih);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv_checkout.setHasFixedSize(true);
        rv_checkout.setLayoutManager(mLayoutManager);
       //rv_checkout.setItemAnimator(new DefaultItemAnimator());
        //rv_checkout.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rv_checkout.setAdapter(adaptermenu);
        gawe_array_order_detail();

        ll_checkout.setOnClickListener(this);
    }

    private void gawe_array_order_detail() {

        for(int i=0;i<harga_bersih.size();i++) {


                // create a object for BookBean
                OrderDetail order =new OrderDetail();
                order.setIdProduk(id_menu_bersih.get(i));
                order.setQty(Integer.parseInt(qty_bersih.get(i)));
                order_detail.add(order);

        }
    }

    private void post_menu() {

        final String get_et_meja = et_meja.getText().toString().trim();
        String get_et_keterangan = et_keterangan.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetworkService.apiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RestApi service = retrofit.create(RestApi.class);

        OrderBody order = new OrderBody();
        order.setId_penjual(Integer.parseInt(id_penjual));
        order.setNomorMeja(Integer.parseInt(get_et_meja));
        order.setKeterangan(get_et_keterangan);
        order.setNim(nim);
        order.setOrderDetail(order_detail);
        Toast.makeText(Checkout.this," "+id_penjual+"-"+get_et_meja+"-"+get_et_keterangan+"-"+nim+"-"+order_detail ,Toast.LENGTH_LONG).show();
        Call<Orderan> call = service.orderPost(order);

        call.enqueue(new Callback<Orderan>() {
            @Override
            public void onResponse(Call<Orderan> call, Response<Orderan> response) {
                if (!response.body().getError()) {
                    //set nim preference
                    ///selesai set

                    Toast.makeText(getApplicationContext(), "Order Berhasil. terima kasih sudah membeli", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Order gagal", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<Orderan> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    private void sortir_menu() {

        for(int i=0;i<getItemCount();i++) {
            if(Integer.parseInt(qty.get(i))!=0) {
                nama_menu_bersih.add(nama_menu.get(i));
                qty_bersih.add(qty.get(i));
                harga_bersih.add(harga.get(i));
                id_menu_bersih.add(id_menu.get(i));
            }
        }
    }

    //rampung
       //
    public void setItemCount(int size) {
        this.size = size;
    }
    public int getItemCount() {
       return size;
    }

    private int getTotal_harga() {
        total_hargane = 0;
        for(int i=0;i<getItemCount();i++) {
            total_hargane = total_hargane + (Integer.parseInt(harga.get(i))*Integer.parseInt(qty.get(i)));
        }

        return total_hargane;
    }

    @Override
    public void onClick(View view) {

        if( et_meja.getText().toString().length() == 0 ) {
            et_meja.setError( "Nomor meja wajib diisi" );
        } else if( et_keterangan.getText().toString().length() == 0 ) {
            et_keterangan.setError( "Keterangan wajib diisi" );
        } else {
            post_menu();
        }
    }
}

