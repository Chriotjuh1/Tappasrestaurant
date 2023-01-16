package com.example.tapasrestaurant.entity;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tapasrestaurant.R;

public class ReceiptViewHolder extends RecyclerView.ViewHolder {
    private TextView mNameTextView;
    private TextView mPriceTextView;

    public ReceiptViewHolder(View itemView) {
        super(itemView);
        mNameTextView = itemView.findViewById(R.id.text_view_name);
        mPriceTextView = itemView.findViewById(R.id.text_view_price);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    //TODO: handle the click event
                }
            }
        });
    }

    public void bind(Receipt receipt) {
        mNameTextView.setText(receipt.getName());
        mPriceTextView.setText(receipt.getPrice());
    }
}
