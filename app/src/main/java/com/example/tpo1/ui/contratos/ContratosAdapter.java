package com.example.tpo1.ui.contratos;

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
import com.example.tpo1.modelo.Contrato;
import com.example.tpo1.modelo.Inmueble;

import java.util.List;

public class ContratosAdapter extends RecyclerView.Adapter<ContratosAdapter.ViewHolder> {

    private Context context;
    private List<Contrato> contratos;
    private LayoutInflater inflater;

    public ContratosAdapter(Context context, List<Contrato> contratos, LayoutInflater inflater) {
        this.context = context;
        this.contratos = contratos;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = inflater.inflate(R.layout.item_inmueble_contrato, parent, false);
        return new ContratosAdapter.ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context)
                .load(contratos.get(position).getInmueble().getImagen())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.fotoInmueble);

        holder.direccion.setText(contratos.get(position).getInmueble().getDireccion());

        holder.btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                Contrato contrato = contratos.get(position);
                bundle.putSerializable("contrato", contrato);
                Navigation.findNavController(view).navigate(R.id.contratoFragment, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contratos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView fotoInmueble;
        TextView direccion;
        Button btnVer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            fotoInmueble = itemView.findViewById(R.id.ivInmueble);
            direccion = itemView.findViewById(R.id.tvDireccionInmueble);
            btnVer = itemView.findViewById(R.id.btnVerContrato);
        }
    }
}
