package com.example.tpo1.ui.inquilinos;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.tpo1.R;
import com.example.tpo1.modelo.Inmueble;
import com.example.tpo1.ui.inmuebles.InmueblesAdapter;

import java.util.List;

public class InquilinosAdapter extends RecyclerView.Adapter<InquilinosAdapter.ViewHolder> {
    private Context context;
    private List<Inmueble> inmuebles;
    private LayoutInflater inflater;

    public InquilinosAdapter(Context context, List<Inmueble> inmuebles, LayoutInflater inflater) {
        this.context = context;
        this.inmuebles = inmuebles;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = inflater.inflate(R.layout.item_inmueble_inquilino, parent, false);
        return new InquilinosAdapter.ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context)
                .load(inmuebles.get(position).getImagen())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.fotoInmueble);

        holder.direccion.setText(inmuebles.get(position).getDireccion());

        holder.btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                Inmueble inmueble = inmuebles.get(position);
                bundle.putSerializable("inmueble", inmueble);
                Navigation.findNavController(view).navigate(R.id.inquilinoFragment, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return inmuebles.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView fotoInmueble;
        TextView direccion;
        Button btnVer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fotoInmueble = itemView.findViewById(R.id.ivInmueble);
            direccion = itemView.findViewById(R.id.tvDireccionInmueble);
            btnVer = itemView.findViewById(R.id.btnVerInquilino);
        }
    }
}