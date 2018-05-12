package online.damfood.damfood.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import online.damfood.damfood.R;
import online.damfood.damfood.model.MenuByResto;


public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolderMenu> {


    int total_harga;
    private ArrayList<MenuByResto> menuByResto;
    List<String> qty = new ArrayList<>();
    List<Integer> harga = new ArrayList<>();
    TextView tv_tharga;

    private int getTotal_harga() {
        total_harga = 0;
        for(int i=0;i<getItemCount();i++) {
            total_harga = total_harga + harga.get(i);
        }

        return total_harga;
    }

    public MenuAdapter(ArrayList<MenuByResto> menuByResto) {
        this.menuByResto = menuByResto;

    }





    @Override
    public MenuAdapter.ViewHolderMenu onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_menu, viewGroup, false);

        return new ViewHolderMenu(view);
    }

    @Override
    public void onBindViewHolder(final MenuAdapter.ViewHolderMenu viewHolder, final int i) {
        final int urut = i;


        viewHolder.tv_name.setText(menuByResto.get(i).getNama());
        viewHolder.tv_harga.setText("Rp. "+ menuByResto.get(i).getHarga());
        viewHolder.tv_stok.setText("Stok : " +menuByResto.get(i).getStok());
        viewHolder.tv_qty.setText(qty.get(i));



        viewHolder.btn_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int nmr = Integer.parseInt(qty.get(urut));

                qty.set(urut, Integer.toString(nmr+1));
                harga.set(urut,Integer.parseInt(menuByResto.get(i).getHarga()) * Integer.parseInt(qty.get(i)));
                //Toast.makeText(view.getContext(), "total "+getTotal_harga(), Toast.LENGTH_SHORT).show();
                viewHolder.tv_qty.setText(qty.get(i));
                Intent intent = new Intent("dataterbang");
                intent.putExtra("total_harga",Integer.toString(getTotal_harga()));
                LocalBroadcastManager.getInstance(view.getContext()).sendBroadcast(intent);

            }
        });
        viewHolder.btn_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int nmr = Integer.parseInt(qty.get(urut));
                if(nmr>0) {
                    qty.set(urut, Integer.toString(nmr - 1));
                    harga.set(urut,Integer.parseInt(menuByResto.get(i).getHarga()) * Integer.parseInt(qty.get(i)));
                    viewHolder.tv_qty.setText(qty.get(i));
                }
                //Toast.makeText(view.getContext(), "total"+getTotal_harga(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent("dataterbang");
                intent.putExtra("total_harga",Integer.toString(getTotal_harga()));
                LocalBroadcastManager.getInstance(view.getContext()).sendBroadcast(intent);
                //Toast.makeText(view.getContext(), view.getContext()+"total"+getTotal_harga(), Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public int getItemCount() {
        return menuByResto.size();
    }



    public class ViewHolderMenu extends RecyclerView.ViewHolder {
        private TextView tv_name, tv_harga, tv_stok, tv_qty;
        private Button btn_up, btn_down;
        private final Context context;

        public ViewHolderMenu(View view) {
            super(view);

            tv_name = (TextView) view.findViewById(R.id.tv_nama_menu);
            tv_harga = (TextView) view.findViewById(R.id.tv_harga);
            tv_stok = (TextView) view.findViewById(R.id.tv_stok_menu);
            tv_qty = (TextView) view.findViewById(R.id.tv_qty);
            btn_down = (Button) view.findViewById(R.id.btn_down);
            btn_up = (Button) view.findViewById(R.id.btn_up);
            tv_tharga = (TextView) view.findViewById(R.id.textView);
            context = itemView.getContext();
            qty.add("0");
            harga.add(0);

        }


    }


}