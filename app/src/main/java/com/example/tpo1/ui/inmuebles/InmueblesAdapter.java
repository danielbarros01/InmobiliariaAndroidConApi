package com.example.tpo1.ui.inmuebles;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.tpo1.Config;
import com.example.tpo1.R;
import com.example.tpo1.modelo.Inmueble;

import java.util.List;

public class InmueblesAdapter extends RecyclerView.Adapter<InmueblesAdapter.ViewHolder> {

    private Context context;
    private List<Inmueble> inmuebles;
    private LayoutInflater inflater;

    public InmueblesAdapter(Context context, List<Inmueble> inmuebles, LayoutInflater inflater) {
        this.context = context;
        this.inmuebles = inmuebles;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = inflater.inflate(R.layout.item_inmueble, parent, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context)
                .load(Config.API_URL + inmuebles.get(position).getImagen())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.fotoInmueble);

        holder.direccion.setText(inmuebles.get(position).getDireccion());
        holder.precio.setText("$" + inmuebles.get(position).getPrecio());
    }

    @Override
    public int getItemCount() {
        return inmuebles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView fotoInmueble;
        TextView direccion, precio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fotoInmueble = itemView.findViewById(R.id.ivInmueble);
            direccion = itemView.findViewById(R.id.tvDireccionInmueble);
            precio = itemView.findViewById(R.id.tvPrecioInmueble);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    Inmueble inmueble = inmuebles.get(getAdapterPosition());//acceder al elemento en inmuebles en la posición obtenida del getAdapterPosition(). Esto devuelve el objeto Inmueble en la posición actual del adaptador.
                    bundle.putSerializable("inmueble", inmueble);
                    Navigation.findNavController(view).navigate(R.id.inmuebleFragment, bundle);
                }
            });
        }
    }
}
