package com.example.tapasrestaurant;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class DialogWithButton extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button btnProcess = findViewById(R.id.btnBestellen);

        btnProcess.setOnClickListener(view -> {


            if (databaseList.isEmpty()){
                showAlertDialog("Selecteer een gerecht alstublieft");
            } else {
                showAlertDialog(databaseList());
            }
        });

    }
    private void showAlertDialog(String message) {
        AlertDialog dialog = new AlertDialog(MenuActivity.this)
                .setTitle("Dialog title")
                .setMessage(message)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void OnClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).create();
        dialog.show();
    }
}
