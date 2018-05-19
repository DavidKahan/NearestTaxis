package com.kahan.david.nearesttaxis.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kahan.david.nearesttaxis.R;
import com.kahan.david.nearesttaxis.model.Taxi;

import java.util.List;

/**
 * Created by david on 18/05/2018.
 */
class TaxisAdapter extends RecyclerView.Adapter<TaxisAdapter.MyViewHolder> {

    private List<Taxi> taxiList;

    TaxisAdapter(List<Taxi> taxiList) {
        this.taxiList = taxiList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.taxi_item_card, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Taxi currentTaxi = taxiList.get(position);
        holder.stationIcon.setImageResource(currentTaxi.getStationIconUrl());
        holder.stationNameTV.setText(currentTaxi.getStationName());
        holder.etaTV.setText(currentTaxi.getEtaStr());
    }

    @Override
    public int getItemCount() {
        return taxiList.size();
    }

    public void setTaxiList(List<Taxi> taxiList) {
        this.taxiList = taxiList;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView stationNameTV, etaTV;
        ImageView stationIcon;

        public MyViewHolder(View taxiCard) {
            super(taxiCard);
            taxiCard.setClickable(false);
            stationIcon = taxiCard.findViewById(R.id.station_icon);
            stationNameTV = taxiCard.findViewById(R.id.station_name_tv);
            etaTV = taxiCard.findViewById(R.id.eta_tv);
        }
    }
}
