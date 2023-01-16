package com.example.tapasrestaurant.entity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tapasrestaurant.R;

import java.util.ArrayList;

public class ReceiptAdapter extends RecyclerView.Adapter<ReceiptAdapter.ReceiptViewHolder> {

    private ArrayList<Receipt> mReceipts;

    public ReceiptAdapter(ArrayList<Receipt> receipts) {
        this.mReceipts = receipts;
    }

    @Override
    public ReceiptViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_receipt, parent, false);
        return new ReceiptViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReceiptViewHolder holder, int position) {
        Receipt receipt = mReceipts.get(position);
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
        public void bind(Receipt receipt) {
            TextView name = itemView.findViewById(R.id.text_view_name);
            name.setText(receipt.getName());
            TextView price = itemView.findViewById(R.id.text_view_price);
            price.setText(receipt.getPrice());
        }

    }
}
