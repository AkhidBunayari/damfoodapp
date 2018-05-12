package online.damfood.damfood.penjual;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import online.damfood.damfood.R;
import online.damfood.damfood.adapter.PenjualAdapter;
import online.damfood.damfood.model.RestoAll;
import online.damfood.damfood.model.Restos;
import online.damfood.damfood.network.NetworkService;
import online.damfood.damfood.network.RestApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PenjualActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private ArrayList<RestoAll> data;
    private PenjualAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penjual);
        initViews();
    }
    private void initViews(){
        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewPenjual);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        loadJSON();
    }
    private void loadJSON(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetworkService.apiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RestApi service = retrofit.create(RestApi.class);
        Call<Restos> call = service.getPenjual();
        call.enqueue(new Callback<Restos>() {
            @Override
            public void onResponse(Call<Restos> call, Response<Restos> response) {

                Restos jsonResponse = response.body();
                data = new ArrayList<>(Arrays.asList(jsonResponse.getRestoAll()));
                adapter = new PenjualAdapter(data);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Restos> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }
}