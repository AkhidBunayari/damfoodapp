package online.damfood.damfood.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import online.damfood.damfood.R;
import online.damfood.damfood.menuproduk.MenuActivity;
import online.damfood.damfood.model.MenuByResto;
import online.damfood.damfood.model.RestoAll;

import static android.content.ContentValues.TAG;

public class PenjualAdapter extends RecyclerView.Adapter<PenjualAdapter.ViewHolder> {
    private ArrayList<online.damfood.damfood.model.RestoAll> RestoAll;

    public PenjualAdapter(ArrayList<online.damfood.damfood.model.RestoAll> android) {
        this.RestoAll = android;
    }

    @Override
    public PenjualAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_penjual, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PenjualAdapter.ViewHolder viewHolder, final int i) {
        final int urut = i;
        viewHolder.tv_name.setText(RestoAll.get(i).getNama());
        viewHolder.tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.i(TAG, "MyClass.getView() — get item number "+RestoAll.get(urut).getId());

                Bundle bundle = new Bundle();
                bundle.putString("idne", ""+ RestoAll.get(i).getId());

                Intent intent = new Intent(view.getContext(), MenuActivity.class);
                intent.putExtras(bundle);
                view.getContext().startActivity(intent);

            }
        });
    }


    @Override
    public int getItemCount() {
        return RestoAll.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_name;
        private final Context context;
        public ViewHolder(View view) {
            super(view);

            tv_name = (TextView)view.findViewById(R.id.tv_nama_penjual);
            context = itemView.getContext();


        }
    }
}