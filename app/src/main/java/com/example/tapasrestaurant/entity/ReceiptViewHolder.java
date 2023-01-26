package com.example.tapasrestaurant.entity;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tapasrestaurant.R;

public class ReceiptViewHolder extends RecyclerView.ViewHolder {
    private TextView mTotalAmountTextView;
    private double mTotalAmount;

    public ReceiptViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = getLayoutPosition();
                if (position != RecyclerView.NO_POSITION) {
                    //TODO: handle the click event here

                }

            }
        });
        mTotalAmountTextView = itemView.findViewById(R.id.text_view_totaalprijs);
    }
}


