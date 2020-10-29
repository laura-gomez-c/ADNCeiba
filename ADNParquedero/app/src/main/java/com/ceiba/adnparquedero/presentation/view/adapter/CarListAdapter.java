package com.ceiba.adnparquedero.presentation.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ceiba.adnparquedero.R;
import com.ceiba.adnparquedero.databinding.CarListItemBinding;
import com.ceiba.adnparquedero.domain.model.Car;

import java.util.List;

public class CarListAdapter extends RecyclerView.Adapter<CarListAdapter.ViewHolder> {

    private final Context context;

    private final OnClickListenerList onClickListenerList;

    private List<Car> carList;


    public CarListAdapter(Context context, List<Car> carList, OnClickListenerList onClickListenerList) {
        this.context = context;
        this.carList = carList;
        this.onClickListenerList = onClickListenerList;
    }

    public void filterList(List<Car> filteredList) {
        this.carList = filteredList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        CarListItemBinding binding = CarListItemBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.textLicensePlate.setText(carList.get(position).getLicensePlate());
        viewHolder.textArrivingTime.setText(context.getString(R.string.text_arriving_date).concat(carList.get(position).getArrivingTime()));
        viewHolder.itemView.setOnClickListener(v -> onClickListenerList.listItemClickListener(carList.get(position)));
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textLicensePlate;

        public TextView textArrivingTime;

        ViewHolder(final CarListItemBinding itemView) {
            super(itemView.getRoot());
            this.textLicensePlate = itemView.textLicensePlate;
            this.textArrivingTime = itemView.textArrivingTime;
        }
    }
}
