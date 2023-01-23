package com.example.tapasrestaurant.entity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tapasrestaurant.R;
import com.google.common.base.Optional;
import com.google.common.collect.Maps;


import java.text.BreakIterator;
import java.util.HashMap;

public class GerechtenQuantityAdapter extends RecyclerView.Adapter<GerechtenQuantityAdapter.ViewHolder> {
    private HashMap<Gerecht, Integer> gerechtenQuantity;

    public GerechtenQuantityAdapter(HashMap<Gerecht, Integer> gerechtenQuantity) {
        this.gerechtenQuantity = gerechtenQuantity;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Gerecht gerecht = (Gerecht) gerechtenQuantity.keySet().toArray()[position];
        int quantity = gerechtenQuantity.get(gerecht);
        holder.naam.setText(gerecht.getNaam() + " x" + quantity);
        holder.prijs.setText("€" + gerecht.getPrijs());
        holder.quantity.setText(" x" + quantity);

    }

    @Override
    public int getItemCount() {
        return gerechtenQuantity.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView naam;
        public TextView prijs;
        public TextView quantity;

        public ViewHolder(View itemView) {
            super(itemView);
            naam = itemView.findViewById(R.id.gerecht_naam);
            prijs = itemView.findViewById(R.id.gerecht_prijs);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gerecht_item, parent, false);
        return new ViewHolder(view);
    }

}