package com.example.tpo1.ui.contratos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tpo1.R;
import com.example.tpo1.modelo.Contrato;
import com.example.tpo1.modelo.Inmueble;
import com.example.tpo1.modelo.Pago;

import java.util.List;

public class PagosAdapter extends RecyclerView.Adapter<PagosAdapter.ViewHolder>{
    private Context context;
    private List<Pago> pagos;
    private LayoutInflater inflater;

    public PagosAdapter(Context context, List<Pago> pagos, LayoutInflater inflater) {
        this.context = context;
        this.pagos = pagos;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = inflater.inflate(R.layout.item_pago, parent, false);
        return new PagosAdapter.ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull PagosAdapter.ViewHolder holder, int position) {
        holder.numeroPago.setText(String.valueOf(pagos.get(position).getNumeroPago()));
        holder.codigoContrato.setText(String.valueOf(pagos.get(position).getContratoId()));
        holder.importe.setText(String.valueOf(pagos.get(position).getContrato().getMontoAlquiler()));
        holder.fechaPago.setText(pagos.get(position).getFecha());
    }

    @Override
    public int getItemCount() {
        return pagos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView codigoPago, numeroPago, codigoContrato, importe, fechaPago;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            codigoPago = itemView.findViewById(R.id.tvCodigoPago);
            numeroPago = itemView.findViewById(R.id.tvNumeroPago);
            codigoContrato = itemView.findViewById(R.id.tvCodigoContratoPago);
            importe = itemView.findViewById(R.id.tvImportePago);
            fechaPago = itemView.findViewById(R.id.tvFechaPago);
        }
    }
}
