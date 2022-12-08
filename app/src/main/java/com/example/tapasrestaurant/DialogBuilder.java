package com.example.tapasrestaurant;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class DialogBuilder extends AppCompatActivity {
    private AlertDialog.Builder dialogbuilder;
    private AlertDialog dialog;
    private EditText orderListPopup;
    private Button newOrderPopup_cancel, newOrderPopup_save;

    public void CreateNewPopupDialog(){
        dialogbuilder = new AlertDialog.Builder(this);
        final View orderPopup = getLayoutInflater().inflate(R.layout.activity_menu, null);
        orderListPopup = orderListPopup.findViewById(R.id.btnBestellen);

        dialogbuilder.setView(orderPopup);
        dialog = dialogbuilder.create();
        dialog.show();

        newOrderPopup_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // de save button aanwijzen gebeurt hier
            }
        });

        newOrderPopup_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // de cancel button aanwijzen gebeurt hier
                dialog.dismiss();
            }
        });

    }




}
