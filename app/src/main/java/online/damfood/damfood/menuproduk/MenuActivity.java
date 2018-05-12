package online.damfood.damfood.menuproduk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import online.damfood.damfood.R;
import online.damfood.damfood.adapter.MenuAdapter;
import online.damfood.damfood.checkout.Checkout;
import online.damfood.damfood.main.MainActivity;
import online.damfood.damfood.model.MenuByResto;
import online.damfood.damfood.model.Menus;
import online.damfood.damfood.network.NetworkService;
import online.damfood.damfood.network.RestApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static online.damfood.damfood.R.id.total_harga;

public class MenuActivity extends AppCompatActivity {
    private RecyclerView recyclerViewMenu;
    private ArrayList<MenuByResto> datamenu;
    private MenuAdapter adaptermenu;
    private String newString;
    private int id_penjual, total_hargane;
    TextView tv_hargatotal;
    LinearLayout fl;
    private Animation animShow, animHide;
    List<String> qty = new ArrayList<>();
    List<String> harga = new ArrayList<>();
    List<String> nama_menu = new ArrayList<>();
    List<Integer> id_menu = new ArrayList<>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        tv_hargatotal = (TextView) findViewById(total_harga);
        fl = (LinearLayout) findViewById(R.id.fl);

        //nompo id soko Penjual Activity
        if (savedInstanceState == null) {

            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString("idne");
            }
        }
        id_penjual = Integer.parseInt(newString.toString());
        //Toast.makeText(MenuActivity.this," "+id_penjual ,Toast.LENGTH_SHORT).show();

        // nggo manggil recycle
        initViews();
        //ngge nompo data total harga seko adapter menu
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("dataterbang"));

        // gawe animasi chekout terbang
        initAnimation();
    }

    private void initAnimation()
    {
        animShow = AnimationUtils.loadAnimation( this, R.anim.show);
        animHide = AnimationUtils.loadAnimation( this, R.anim.hide);
    }
    public int getItemCount() {
        return nama_menu.size();
    }

    private int getTotal_harga() {
        total_hargane = 0;
        for(int i=0;i<getItemCount();i++) {
            total_hargane = total_hargane + (Integer.parseInt(harga.get(i))*Integer.parseInt(qty.get(i)));
        }

        return total_hargane;
    }
    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            //nampani nama menu dan qty sek dibundeli
            Bundle extra = intent.getBundleExtra("extra");
            nama_menu = (List<String>) extra.getSerializable("nama_menu");
            qty = (List<String>) extra.getSerializable("qty");
            harga = (List<String>) extra.getSerializable("harga");
            id_menu = (List<Integer>) extra.getSerializable("id_menu");

            if(getTotal_harga()==0) {
                if (fl.getVisibility() == View.GONE) {

                }else {
                    fl.startAnimation( animHide );
                    fl.setVisibility(View.GONE);
                }

            } else {
                if (fl.getVisibility() == View.VISIBLE) {
                    // Its visible
                } else {
                    fl.startAnimation( animShow );
                    fl.setVisibility(View.VISIBLE);

                }


            }
            tv_hargatotal.setText(Integer.toString(getTotal_harga()));



            //  Toast.makeText(MenuActivity.this,""+getTotal_harga() ,Toast.LENGTH_SHORT).show();


        }
    };


    private void initViews(){
        recyclerViewMenu = (RecyclerView)findViewById(R.id.recyclerViewMenu);
        recyclerViewMenu.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewMenu.setLayoutManager(layoutManager);
        loadJSON();
    }
    private void loadJSON(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetworkService.apiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RestApi service = retrofit.create(RestApi.class);
        Call<Menus> call = service.getMenu(id_penjual);
        call.enqueue(new Callback<Menus>() {
            @Override
            public void onResponse(Call<Menus> call, Response<Menus> response) {

                Menus jsonResponse = response.body();
                datamenu = new ArrayList<>(Arrays.asList(jsonResponse.getMenuByResto()));
                adaptermenu = new MenuAdapter(datamenu);
                recyclerViewMenu.setAdapter(adaptermenu);



            }

            @Override
            public void onFailure(Call<Menus> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });

        // lempar data2 menu yg dipesen ke chekout
        fl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Checkout.class);

                Bundle extra = new Bundle();
                extra.putString("idne",Integer.toString(id_penjual));
                extra.putSerializable("id_menu", (Serializable) id_menu);
                extra.putSerializable("qty", (Serializable) qty);
                extra.putSerializable("nama_menu", (Serializable) nama_menu);
                extra.putSerializable("harga", (Serializable) harga);
                intent.putExtra("extra", extra);

                startActivity(intent);
            }
        });
    }

}