package com.example.rub_a_dub_grub3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import info.hoang8f.widget.FButton;

public class MainActivity extends AppCompatActivity {

    private Button btnEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(!isConnected(MainActivity.this)) buildDialog(MainActivity.this).show();
        else {

        }

        FButton fButton; // Change button color as it will not hold the change via xml https://github.com/hoang8f/android-flat-button/issues/34
        fButton=(FButton) findViewById(R.id.btnEnter);
        fButton.setButtonColor(getResources().getColor(R.color.logoGreen));

        btnEnter = (Button)findViewById(R.id.btnEnter);

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSelectionActivity();
            }
        });
    }

    public void openSelectionActivity() {
        Intent intent = new Intent(this, SelectionActivity.class);
        startActivity(intent);
    }

    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) return true;
        else return false;
        } else
        return false;
    }

    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setIcon( android.R.drawable.ic_dialog_alert );
        builder.setTitle("No Internet Connection");
        builder.setMessage("This app requires internet access. please connect to the internet. Alternatively see the signs on display explaining how to connect to our Wi-Fi");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });

        return builder;
    }
}
