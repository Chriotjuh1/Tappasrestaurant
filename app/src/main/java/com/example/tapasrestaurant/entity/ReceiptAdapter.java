package com.example.tapasrestaurant.entity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tapasrestaurant.R;

import java.util.ArrayList;

public class ReceiptAdapter extends RecyclerView.Adapter<ReceiptAdapter.ReceiptViewHolder> {

    private int totalQuantity;
    private ArrayList<Gerecht> mReceipts;

    public ReceiptAdapter(ArrayList<Gerecht> receipts) {
        this.mReceipts = receipts;
    }

    @Override
    public ReceiptViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gerecht_item, parent, false);
        return new ReceiptViewHolder(view);




    }

    @Override
    public void onBindViewHolder(ReceiptViewHolder holder, int position) {
        Gerecht receipt = mReceipts.get(position);
        holder.bind(receipt);


    }

    @Override
    public int getItemCount() {
        return mReceipts.size();

    }

    public static class ReceiptViewHolder extends RecyclerView.ViewHolder {
        public ReceiptViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {

                        //TODO: handle the click event here



                    }
                }
            });
        }
        public void bind(Gerecht receipt) {
            TextView name = itemView.findViewById(R.id.gerecht_naam);
            name.setText(receipt.getNaam());
            TextView price = itemView.findViewById(R.id.gerecht_prijs);
            price.setText(receipt.getPrijs());
        }

    }

}


