package mx.mobilestudio.promohunters.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import mx.mobilestudio.promohunters.R;
import mx.mobilestudio.promohunters.model.Promo;

public class PromoHuntersAdaptador extends RecyclerView.Adapter {

    private ArrayList<Promo> promos;

    public PromoHuntersAdaptador(ArrayList<Promo> promos) {
        this.promos = promos;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, null);

            MyViewHolder viewHolder = new MyViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((MyViewHolder)holder).vhdesc.setText(promos.get(position).getDescription());
        ((MyViewHolder)holder).vhtitle.setText(promos.get(position).getTitle());

        String imageLink = promos.get(position).getImageLink();

        if(imageLink!=null && !imageLink.isEmpty()){


            Picasso.with(((MyViewHolder) holder).vhImage.getContext()).load(imageLink).into(((MyViewHolder) holder).vhImage);


        }else{
            ((MyViewHolder) holder).vhImage.setImageResource(android.R.drawable.ic_menu_camera);
        }


    }

    @Override
    public int getItemCount() {
        return promos.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView vhtitle;
        public TextView vhdesc;
        public ImageView vhImage;

        public MyViewHolder(View itemView){
            super(itemView);
            vhtitle=itemView.findViewById(R.id.ctitle);
            vhdesc=itemView.findViewById(R.id.cdescription);
            vhImage = itemView.findViewById(R.id.imageViewPromo);
        }
    }
}
