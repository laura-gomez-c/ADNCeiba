package com.ceiba.adnparquedero.presentation.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ceiba.adnparquedero.R;
import com.ceiba.adnparquedero.databinding.MotoListItemBinding;
import com.ceiba.adnparquedero.domain.model.Moto;

import java.util.List;

public class MotoListAdapter extends RecyclerView.Adapter<MotoListAdapter.ViewHolder> {

    private Context context;

    private List<Moto> postList;


    public MotoListAdapter(Context context, List<Moto> postList) {
        this.context = context;
        this.postList = postList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        MotoListItemBinding binding = MotoListItemBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.textLicensePlate.setText(postList.get(position).getLicensePlate());
        viewHolder.textArrivingTime.setText(context.getString(R.string.text_arriving_date).concat(postList.get(position).getArrivingTime()));
        viewHolder.textCylinderCapacity.setText(context.getString(R.string.text_cylinder_capacity).concat(String.valueOf(postList.get(position).getCylinderCapacity())));
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textLicensePlate;

        TextView textArrivingTime;

        TextView textCylinderCapacity;


        ViewHolder(final MotoListItemBinding itemView) {
            super(itemView.getRoot());
            this.textLicensePlate = itemView.textLicensePlate;
            this.textArrivingTime = itemView.textArrivingTime;
            this.textCylinderCapacity = itemView.textCylinderCapacity;
        }
    }
}
