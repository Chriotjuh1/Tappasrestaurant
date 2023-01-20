package com.example.tapasrestaurant.entity;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tapasrestaurant.R;

import java.util.ArrayList;

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
//    public void bind(Gerecht receipt, ArrayList<Gerecht> receipts) {
//        TextView name = itemView.findViewById(R.id.gerecht_naam);
//        name.setText(receipt.getNaam());
//        TextView price = itemView.findViewById(R.id.gerecht_prijs);
//        price.setText(receipt.getPrijs());
//        mTotalAmount += receipt.getPrice();
//        if (getLayoutPosition() == receipts.size() - 1) {
//            mTotalAmountTextView.setText(String.format("Totaal: € %.2f", mTotalAmount));
//            mTotalAmountTextView.setVisibility(View.VISIBLE);
//        }
//
//    }
//}


