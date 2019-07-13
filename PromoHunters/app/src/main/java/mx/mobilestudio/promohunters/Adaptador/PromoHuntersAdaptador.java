package mx.mobilestudio.promohunters.Adaptador;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

import mx.mobilestudio.promohunters.R;
import mx.mobilestudio.promohunters.model.Promo;

public class PromoHuntersAdaptador extends RecyclerView.Adapter  {

    private ArrayList<Promo> promos;
    public PromoHuntersAdaptador(ArrayList<Promo> promos){
        this.promos=promos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,null);
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder)holder).vhtitle.setText(promos.get(position).getTitle());
        ((MyViewHolder)holder).vhlink.setText(promos.get(position).getLink());
        String imageLink = promos.get(position).getImageLink();

        if(imageLink!=null && !imageLink.isEmpty()){
            Picasso.with( ((MyViewHolder) holder).vhImage.getContext()).load(imageLink)
                    .into(((MyViewHolder) holder).vhImage);
        }else{
            ((MyViewHolder) holder).vhImage.setImageResource(android.R.drawable.ic_menu_camera);
        }
    }

    @Override
    public int getItemCount() {
        return promos.size();
        //esta linea dice cuantas te va a mostrar.
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView vhtitle;
        public TextView vhlink;
        public ImageView vhImage;

        public MyViewHolder(View itemView){
            super(itemView);
            vhtitle=itemView.findViewById(R.id.ctitle);
            vhlink=itemView.findViewById(R.id.clink);
            vhImage=itemView.findViewById(R.id.imageViewPromo);
        }
    }
}
