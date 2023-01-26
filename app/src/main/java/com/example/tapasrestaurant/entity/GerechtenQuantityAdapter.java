package com.example.tapasrestaurant.entity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tapasrestaurant.R;

import java.text.BreakIterator;
import java.util.HashMap;
import java.util.List;

public class GerechtenQuantityAdapter extends RecyclerView.Adapter<GerechtenQuantityAdapter.ViewHolder> {
    private HashMap<Gerecht, Integer> gerechtenQuantity;
    private Context mContext;
    private List<Gerecht> gerechten;


    public GerechtenQuantityAdapter(List<Gerecht> gerechten) {
        gerechtenQuantity = new HashMap<>();
        for (Gerecht gerecht : gerechten) {
            gerechtenQuantity.put(gerecht, 1);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Gerecht gerecht = gerechten.get(position);
        holder.naam.setText(gerecht.getNaam());
        holder.prijs.setText("€" + gerecht.getPrijs());
        holder.quantity.setText("x" + gerechtenQuantity.get(gerecht));
    }


    @Override
    public int getItemCount() {
        return gerechtenQuantity.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView naam;
        public TextView prijs;

        public TextView quantity;
        public BreakIterator gerechtTextView;


        public ViewHolder(View itemView) {
            super(itemView);
            naam = itemView.findViewById(R.id.gerecht_naam);
            prijs = itemView.findViewById(R.id.gerecht_prijs);
            quantity = itemView.findViewById(R.id.gerecht_quantity);

        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gerecht_item, parent, false);
        return new ViewHolder(view);
    }

    public void updateQuantity(Gerecht gerecht, int newQuantity) {
        gerechtenQuantity.put(gerecht, newQuantity);
        notifyDataSetChanged();
    }
}
